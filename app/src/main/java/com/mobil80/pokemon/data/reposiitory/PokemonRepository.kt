package com.mobil80.pokemon.data.reposiitory

import com.mobil80.pokemon.data.model.responces.CharacterDetail
import com.mobil80.pokemon.data.model.responces.CharacterResponse
import com.mobil80.pokemon.data.network.ApiCalls
import com.mobil80.pokemon.presentation.utils.Resource

class PokemonRepository (
   private val api: ApiCalls
) {
    suspend fun getPokemonList(page: Int, limit: Int): Resource<CharacterResponse> {
        val response = try {
            api.getCharacterList(page, limit)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getCharacterDetail(characterId: Int): Resource<CharacterDetail> {
        val response = try {
            api.getCharacterDetail(characterId)
        } catch(e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}