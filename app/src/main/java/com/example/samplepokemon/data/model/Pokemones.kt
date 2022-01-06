package com.example.samplepokemon.data.model

import com.google.gson.annotations.SerializedName

data class Pokemones(

    @SerializedName("name")
    val name : String?,
    @SerializedName("url")
    val url : String?,
)