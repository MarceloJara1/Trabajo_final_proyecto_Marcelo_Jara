package com.example.trabajofinalproyectomarcelojara.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trabajofinalproyectomarcelojara.model.local.ShoesDao
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesDetailEntity
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesEntity

@Database(entities = [ShoesEntity::class, ShoesDetailEntity::class], version = 1,
exportSchema = false)
abstract class ShoeDatabase: RoomDatabase() {

    abstract fun getShoesDao(): ShoesDao

    companion object{
        @Volatile
        private var INSTANCE: ShoeDatabase?=null

        fun getDatabase(context: Context):ShoeDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoeDatabase::class.java,"shoeDb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}