package com.example.trabajofinalproyectomarcelojara.model

import android.util.Log
import com.example.trabajofinalproyectomarcelojara.model.local.ShoesDao
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesDetailEntity
import com.example.trabajofinalproyectomarcelojara.model.remote.RetrofitClient

class Repository(private val shoeDao: ShoesDao) {

    private val networkService = RetrofitClient.retrofitInstance()

    val shoesListLiveData = shoeDao.getAllShoes()

    suspend fun fetchShoes(){
        val service = kotlin.runCatching { networkService.fetchShoesList() }

        service.onSuccess {
            when(it.code()){
                in 200 .. 299-> it.body()?.let {
                    shoeDao.insertAllShoes(fromInternetShoesEntity(it))
                }else -> Log.d("REPO","${it.code()} - ${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
    }

    suspend fun fetchShoeDetail(id:Int):ShoesDetailEntity?{
        val service = kotlin.runCatching { networkService.fetchShoesDetail(id) }
        return service.getOrNull()?.body()?.let { shoesDetail ->
            val shoesDetailEntity = fromInternetToShoesDetailEntity(shoesDetail)
            shoeDao.insertShoesDetail(shoesDetailEntity)
            shoesDetailEntity
        }
    }
}