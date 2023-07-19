package com.example.trabajofinalproyectomarcelojara

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.trabajofinalproyectomarcelojara.model.local.ShoesDao
import com.example.trabajofinalproyectomarcelojara.model.local.database.ShoeDatabase
import com.example.trabajofinalproyectomarcelojara.model.local.entities.ShoesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


// En este test se testea la insercion de datos mediante el dao y la comprobacion de los datos insertados en la bd.
@RunWith(AndroidJUnit4::class)
@Config(sdk=[Build.VERSION_CODES.Q], manifest = Config.NONE)
class DaoTest {
    private lateinit var daoTest: ShoesDao
    private lateinit var db: ShoeDatabase

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ShoeDatabase::class.java).build()
        daoTest = db.getShoesDao()
    }

    @After
    fun shutDown(){
        db.close()
    }

    @Test
    fun insertShoesList()= runBlocking {
        val shoesEntity = listOf(
            ShoesEntity(13,"Nike Tempo", "Chile", "NoUrl", "Nike",41),
            ShoesEntity(14,"Converse Chuck Taylor", "Chile", "NoUrl", "Converse",40)
        )
        daoTest.insertAllShoes(shoesEntity)
        val shoeLiveData = daoTest.getAllShoes()
        val ShoeList: List<ShoesEntity> = shoeLiveData.value?: emptyList()

        MatcherAssert.assertThat(ShoeList, CoreMatchers.not(emptyList()))
        MatcherAssert.assertThat(ShoeList.size, CoreMatchers.equalTo(2))
    }
}