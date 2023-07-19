package com.example.trabajofinalproyectomarcelojara.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shoes_table")
data class ShoesEntity (
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val marca: String,
    val numero: Int
)