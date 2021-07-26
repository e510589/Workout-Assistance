package com.lupo.lupo_trainer.utils.data.weightTrain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(WeightTrainSet::class), version = 1)
abstract class WeightTrainDatabase : RoomDatabase() {

    abstract fun weightTrain() : WeightTrainDao


    companion object {
        @Volatile
        private var INSTANCE : WeightTrainDatabase? = null

        val database_name = "weight_trainning_database"

        fun getDataBase(context: Context, scope: CoroutineScope):WeightTrainDatabase {

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeightTrainDatabase::class.java,
                    database_name).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}