package com.mobil80.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mobil80.pokemon.data.model.responces.CharacterDetail
import com.mobil80.pokemon.data.reposiitory.PokemonRepository
import com.mobil80.pokemon.presentation.utils.Resource

class PokemonDetailViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(characterId: Int): Resource<CharacterDetail> {
        return repository.getCharacterDetail(characterId)
    }
}