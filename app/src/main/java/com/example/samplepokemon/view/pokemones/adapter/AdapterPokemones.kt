package com.example.samplepokemon.view.pokemones.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplepokemon.R
import com.example.samplepokemon.data.model.Pokemones
import java.util.*

class AdapterPokemones(private var listPokemones: ArrayList<Pokemones>, private  var listener: AdapterListenerClick) : RecyclerView.Adapter<AdapterPokemones.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_pokemon_list, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNamePokemon.text = listPokemones[position].name

        holder.tvNamePokemon.setOnClickListener {
        listener.listenerButtonOnClick(listPokemones[position].url.toString(), listPokemones[position].name.toString())
        }

    }

    override fun getItemCount(): Int {
        return listPokemones.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamePokemon: TextView = itemView.findViewById<View>(R.id.tvNamePokemon) as TextView

    }

     interface AdapterListenerClick {
        fun listenerButtonOnClick(url:String, nombre : String)
    }
}