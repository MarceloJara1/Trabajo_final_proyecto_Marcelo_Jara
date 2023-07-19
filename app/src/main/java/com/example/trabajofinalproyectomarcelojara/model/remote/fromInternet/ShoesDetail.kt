package com.example.trabajofinalproyectomarcelojara.model.remote.fromInternet

data class ShoesDetail (
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