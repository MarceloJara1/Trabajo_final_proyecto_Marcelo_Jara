package com.example.trabajofinalproyectomarcelojara.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesDetailEntity
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesEntity

@Dao
interface ShoesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllShoes(listShoes: List<ShoesEntity>)

    @Query("SELECT * FROM shoes_table")
    fun getAllShoes(): LiveData<List<ShoesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoesDetail(shoe: ShoesDetailEntity)
}