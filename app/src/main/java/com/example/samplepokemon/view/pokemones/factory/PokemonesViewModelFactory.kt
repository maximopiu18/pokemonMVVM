package com.example.samplepokemon.view.pokemones.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samplepokemon.data.repository.PokemonesRepository
import com.example.samplepokemon.view.pokemones.viewmodel.PokemonesViewModel

@Suppress("UNCHECKED_CAST")
class PokemonesViewModelFactory(private val repository: PokemonesRepository, private val callback : PokemonesViewModel.PokemonesCallback) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PokemonesViewModel::class.java)) {
            PokemonesViewModel(this.repository, callback) as T
        } else throw IllegalArgumentException("No se encontró la clase")
    }
}
