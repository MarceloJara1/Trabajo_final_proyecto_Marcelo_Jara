package com.example.trabajofinalproyectomarcelojara.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trabajofinalproyectomarcelojara.model.Repository
import com.example.trabajofinalproyectomarcelojara.model.local.database.ShoeDatabase
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesDetailEntity
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesEntity
import kotlinx.coroutines.launch

class ViewModel (application: Application): AndroidViewModel(application){

    private val repository: Repository

    private val shoesDetailLiveData = MutableLiveData<ShoesDetailEntity>()

    init {
        val bd = ShoeDatabase.getDatabase(application)
        val dao = bd.getShoesDao()

        repository = Repository(dao)
        viewModelScope.launch {
            repository.fetchShoes()
        }
    }

    fun getShoesList(): LiveData<List<ShoesEntity>> = repository.shoesListLiveData

    fun getShoesDetail(): LiveData<ShoesDetailEntity> = shoesDetailLiveData

    fun getShoeDetailByIdFromInternet(id:Int) = viewModelScope.launch {
        val shoeDetail = repository.fetchShoeDetail(id)
        shoeDetail?.let {
            shoesDetailLiveData.postValue(it)
        }
    }
}