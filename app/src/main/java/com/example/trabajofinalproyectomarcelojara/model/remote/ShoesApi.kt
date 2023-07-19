package com.example.trabajofinalproyectomarcelojara.model.remote

import com.example.trabajofinalproyectomarcelojara.model.remote.fromInternet.Shoes
import com.example.trabajofinalproyectomarcelojara.model.remote.fromInternet.ShoesDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoesApi {

    @GET("shoes")
    suspend fun fetchShoesList(): Response<List<Shoes>>

    @GET("shoes/{id}")
    suspend fun fetchShoesDetail(@Path("id")id:Int):Response<ShoesDetail>
}