package com.example.trabajofinalproyectomarcelojara.model

import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesDetailEntity
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesEntity
import com.example.trabajofinalproyectomarcelojara.model.remote.fromInternet.Shoes
import com.example.trabajofinalproyectomarcelojara.model.remote.fromInternet.ShoesDetail

fun fromInternetShoesEntity(shoeList:List<Shoes>): List<ShoesEntity>{
    return shoeList.map{
        ShoesEntity(
            id = it.id,
            nombre = it.nombre,
            origen = it.origen,
            imagenLink = it.imagenLink,
            marca = it.marca,
            numero = it.numero
        )
    }
}

fun fromInternetToShoesDetailEntity(shoe: ShoesDetail): ShoesDetailEntity{
    return ShoesDetailEntity(
        id = shoe.id,
        nombre = shoe.nombre,
        origen = shoe.origen,
        imagenLink = shoe.imagenLink,
        marca = shoe.marca,
        numero = shoe.numero,
        precio = shoe.precio,
        entrega = shoe.entrega,
        Colores = shoe.Colores
    )
}