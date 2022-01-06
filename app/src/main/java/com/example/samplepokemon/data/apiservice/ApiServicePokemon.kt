package com.example.samplepokemon.data.apiservice

import com.example.samplepokemon.data.model.ResponsePokemon
import com.example.samplepokemon.data.model.ResponsePokemonDescription
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface ApiServicePokemon {


    @Headers("Content-Type: application/json")
    @GET("api/v2/pokemon/")
    fun getPokemons(): Single<ResponsePokemon>


    @Headers("Content-Type: application/json")
    @GET
    fun getPokemonsDescription(@Url url: String): Single<ResponsePokemonDescription>
}