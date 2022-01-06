package com.example.samplepokemon.data.model

import com.google.gson.annotations.SerializedName

data class ResponsePokemon(

    @SerializedName("count")
    val count : String?,
    @SerializedName("next")
    val next : String?,
    @SerializedName("previous")
    val previous : String?,
    @SerializedName("results")
    val listPokemones : ArrayList<Pokemones>?

)