package com.lupo.lupo_trainer.utils.data.Train

import android.content.Context
import com.lupo.lupo_trainer.utils.AppExcutors

class TrainDataSourceFactory{
    companion object{
        fun creatDataSource(context: Context):TrainDataSource{
            val trainDatabase = TrainDatabase.getDataBase(context)
            val appExcutors = AppExcutors()
            return TrainDataSource.getInstance(appExcutors,trainDatabase.trainDao())
        }
    }
}