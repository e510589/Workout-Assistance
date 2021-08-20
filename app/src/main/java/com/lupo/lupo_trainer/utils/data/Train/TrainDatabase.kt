package com.lupo.lupo_trainer.utils.data.Train

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(TrainSet::class), version = 1)
abstract class TrainDatabase : RoomDatabase() {

    abstract fun trainDao() : TrainDao

    companion object {
        @Volatile
        private var INSTANCE : TrainDatabase? = null

        val database_name = "TrainList.db"

        private val lock = Any()

        fun getDataBase(context: Context):TrainDatabase {
            synchronized(lock){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    TrainDatabase::class.java, database_name).build()
                }
                return INSTANCE!!
            }
        }
    }
}