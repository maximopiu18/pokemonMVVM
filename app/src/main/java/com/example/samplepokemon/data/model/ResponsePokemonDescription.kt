package com.example.samplepokemon.data.model

import com.google.gson.annotations.SerializedName

data class ResponsePokemonDescription(

    @SerializedName("sprites")
    val sprites : Sprites?,
    @SerializedName("types")
    val types : ArrayList<Types>?

)

data class Sprites(

    @SerializedName("back_default")
    val back_default : String?,
    @SerializedName("front_shiny")
    val front_shiny : String?,
)

data class Types(

    @SerializedName("slot")
    val slot : String?,
    @SerializedName("type")
    val type : Type?,
)

data class Type(

    @SerializedName("name")
    val name : String?,
    @SerializedName("url")
    val url : String?,
)