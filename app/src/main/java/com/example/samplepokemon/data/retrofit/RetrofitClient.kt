package com.example.samplepokemon.data.retrofit

import com.example.samplepokemon.data.apiservice.ApiServicePokemon
import com.example.samplepokemon.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {


    companion object {

        fun getRetrofitServicePokemon(): ApiServicePokemon {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_POKEMON)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(ApiServicePokemon::class.java)
        }
    }

}