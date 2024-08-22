package com.mobil80.pokemon.data.network

import com.mobil80.pokemon.data.model.responces.CharacterDetail
import com.mobil80.pokemon.data.model.responces.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCalls {

    @GET("character")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int
    ): CharacterDetail
}