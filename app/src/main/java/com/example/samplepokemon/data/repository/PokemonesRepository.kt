package com.example.samplepokemon.data.repository

import com.example.samplepokemon.data.apiservice.ApiServicePokemon
import com.example.samplepokemon.data.model.ResponsePokemon
import com.example.samplepokemon.data.model.ResponsePokemonDescription
import io.reactivex.Single

class PokemonesRepository(
    private val service: ApiServicePokemon) {
    fun getPokemonesService():Single<ResponsePokemon> =
        service.getPokemons()
    fun getPokemonesDescription(url : String):Single<ResponsePokemonDescription> =
        service.getPokemonsDescription(url)


}