package com.mobil80.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobil80.pokemon.core.Constants.PAGE_LIMIT
import com.mobil80.pokemon.data.model.request.PokedexListEntry
import com.mobil80.pokemon.data.reposiitory.PokemonRepository
import com.mobil80.pokemon.presentation.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class PokemonListViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    // State variables for the UI
    var curPage = 0

    // StateFlow for the UI
    val _pokemonList = MutableStateFlow<List<PokedexListEntry>>(listOf())
    val pokemonList: StateFlow<List<PokedexListEntry>> = _pokemonList

    val _loadError = MutableStateFlow("")
    val loadError: StateFlow<String> = _loadError

    val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val _endReached = MutableStateFlow(false)
    val endReached: StateFlow<Boolean> = _endReached

    val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    // Caches
    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if (isSearchStarting) {
            _pokemonList.value
        } else {
            cachedPokemonList
        }

        viewModelScope.launch {
            if (query.isEmpty()) {
                _pokemonList.value = cachedPokemonList
                _isSearching.value = false
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }

            if (isSearchStarting) {
                cachedPokemonList = _pokemonList.value
                isSearchStarting = false
            }

            _pokemonList.value = results
            _isSearching.value = true
        }
    }

    fun loadPokemonPaginated(isNewData: Boolean = false) {
        viewModelScope.launch {
            if (isNewData) {
                curPage = 0
                _pokemonList.value = emptyList()
                _endReached.value = false
            }

            _isLoading.value = true

            when (val result = repository.getPokemonList(PAGE_LIMIT, curPage * PAGE_LIMIT)) {
                is Resource.Success -> {
                    result.data?.let { data ->
                        _endReached.value = curPage * PAGE_LIMIT >= data.results.count()
                        val pokedexEntries = data.results.map { entry ->
                            val number = entry.url.extractPokemonNumber()
                            val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
                            PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                        }
                        _pokemonList.value += pokedexEntries
                        curPage++
                    } ?: run {
                        _loadError.value = "Data is null"
                    }
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    _loadError.value = result.message ?: "Unknown error"
                    _isLoading.value = false
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    private fun String.extractPokemonNumber(): String {
        return if (endsWith("/")) {
            dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            takeLastWhile { it.isDigit() }
        }
    }
}