package com.example.trabajofinalproyectomarcelojara.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabajofinalproyectomarcelojara.databinding.ItemGridBinding
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesEntity

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listShoes = listOf<ShoesEntity>()
    private val selectedShoe = MutableLiveData<ShoesEntity>()

    fun update(list: List<ShoesEntity>){
        listShoes = list
        notifyDataSetChanged()
    }

    fun selectedShoe():
            LiveData<ShoesEntity> = selectedShoe

    inner class ViewHolder(private val binding: ItemGridBinding):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind (shoe: ShoesEntity){
            Glide.with(binding.img).load(shoe.imagenLink).centerCrop().into(binding.img)
            binding.txtName.text = shoe.nombre
            binding.txtBrand.text = shoe.marca
            binding.txtSize.text = shoe.numero.toString()
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            selectedShoe.value = listShoes[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        return ViewHolder(ItemGridBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val shoe = listShoes[position]
        holder.bind(shoe)

    }

    override fun getItemCount(): Int = listShoes.size
}