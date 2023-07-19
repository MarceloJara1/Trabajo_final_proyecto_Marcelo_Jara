package com.example.trabajofinalproyectomarcelojara.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoe_detail")
data class ShoesDetailEntity (
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val marca: String,
    val numero: Int,
    val precio: Int,
    val entrega: Boolean,
    val Colores: String?
)