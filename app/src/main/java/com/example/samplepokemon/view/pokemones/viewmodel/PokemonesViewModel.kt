package com.example.samplepokemon.view.pokemones.viewmodel
import androidx.lifecycle.ViewModel
import android.util.Log
import com.example.samplepokemon.data.model.ResponsePokemon
import com.example.samplepokemon.data.model.ResponsePokemonDescription
import com.example.samplepokemon.data.repository.PokemonesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PokemonesViewModel(private val repository: PokemonesRepository?,
                         private val  callback: PokemonesCallback?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun initPokemonesService() {
        compositeDisposable.add(
            repository!!.getPokemonesService()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    callback!!.onSuccess(response)
                }, { throwable ->
                    Log.e("error", "error: $throwable")
                    callback!!.onError(throwable.toString())
                })
        )
    }
    fun initPokemonesServiceDescription(url : String) {
        compositeDisposable.add(
            repository!!.getPokemonesDescription(url)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.e("response", "response description")
                    callback!!.onSuccessDescription(response)
                }, { throwable ->
                    Log.e("error", "error: $throwable")
                    callback!!.onError(throwable.toString())
                })
        )
    }

    interface PokemonesCallback {
        fun onSuccess(response : ResponsePokemon)
        fun onError(messageError :String)
        fun onSuccessDescription(response : ResponsePokemonDescription)
    }

}
