package com.example.samplepokemon.view.pokemones.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplepokemon.R
import com.example.samplepokemon.data.retrofit.RetrofitClient
import com.example.samplepokemon.view.pokemones.factory.PokemonesViewModelFactory
import com.example.samplepokemon.data.model.Pokemones
import com.example.samplepokemon.data.model.ResponsePokemon
import com.example.samplepokemon.data.model.ResponsePokemonDescription
import com.example.samplepokemon.data.repository.PokemonesRepository
import com.example.samplepokemon.view.pokemones.adapter.AdapterPokemones
import com.example.samplepokemon.view.pokemones.viewmodel.PokemonesViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), PokemonesViewModel.PokemonesCallback,
    AdapterPokemones.AdapterListenerClick {

    lateinit var viewModel: PokemonesViewModel
    var retrofitClient = RetrofitClient.getRetrofitServicePokemon()
    var listaPokemones = ArrayList<Pokemones>()
    private var adapterSites: AdapterPokemones? = null
    lateinit var rv: RecyclerView
    lateinit var nombrePokemon: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rvPokemones)
        initViewModel()
    }

    fun initViewModel() {

        viewModel = ViewModelProvider(this,
            PokemonesViewModelFactory(PokemonesRepository(retrofitClient), this))
            .get(PokemonesViewModel::class.java)
        viewModel.initPokemonesService()
    }


    override fun onSuccess(response: ResponsePokemon) {

        uploadRvData(response.listPokemones!!)
        listaPokemones = response.listPokemones!!
    }

    override fun onError(messageError: String) {
        Log.e("error", "error al consumir servicio  $messageError")
    }

    override fun onSuccessDescription(response: ResponsePokemonDescription) {
        Log.e("rodo correcto", "todo correcto $$response")
        alertDialogDetallesPokemon(response)
    }

    private fun uploadRvData(list: ArrayList<Pokemones>) {
        adapterSites = AdapterPokemones(list, this)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = mLayoutManager
        rv.setHasFixedSize(true)
        rv.adapter = adapterSites
        rv.visibility = View.VISIBLE
    }


    private fun alertDialogDetallesPokemon(response: ResponsePokemonDescription) {
        val alertDialog: AlertDialog?
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.alert_dialog_pokemon_descripcion, null)
        dialogBuilder.setView(dialogView)
        val btnCerrar = dialogView.findViewById<View>(R.id.btnCerrar) as Button
        var tvNombrePokemon = dialogView.findViewById<View>(R.id.tvNombrePokemon) as TextView
        var imgPokemon = dialogView.findViewById<View>(R.id.imgPokemon) as ImageView
        tvNombrePokemon.setText(""+nombrePokemon)

        var tvTypes = dialogView.findViewById<View>(R.id.tvTypes) as TextView

        var a =0;
        var typos = ""
        while(a<response.types!!.size){
            typos = typos+response.types!!.get(a).type!!.name+"\n"
            a++
        }
        tvTypes.setText(""+typos)


        var url =response.sprites!!.front_shiny
        url = url!!.replace("http://","https://")
        Picasso
            .with(this)
            .load(url)
            .resize(500, 500) // resizes the image to these dimensions (in pixel)
            .centerCrop()
            .into(imgPokemon)


        alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        alertDialog.setCancelable(false)
        btnCerrar.setOnClickListener {
            alertDialog.cancel()
        }

    }

    override fun listenerButtonOnClick(url: String, nombre: String) {
        nombrePokemon = nombre
        viewModel.initPokemonesServiceDescription(url)
    }


}