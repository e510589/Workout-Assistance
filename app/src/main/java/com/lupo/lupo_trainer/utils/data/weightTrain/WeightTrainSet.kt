package com.lupo.lupo_trainer.utils.data.weightTrain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lupo.lupo_trainer.utils.data.TrainBase

@Entity(tableName = "WeightTrainList")
data class WeightTrainSet(

    @NonNull
    @ColumnInfo(name = "trainType")
    val trainType:Int,

    @ColumnInfo(name = "listName")
    var listName:String?,

    @NonNull
    @ColumnInfo(name = "date")
    var date:String,

    @ColumnInfo(name = "muscles")
    var muscles:String?,

    @NonNull
    @ColumnInfo(name = "movesName")
    var movesName:String,

    @NonNull
    @ColumnInfo(name = "times")
    var times:Int,


    @ColumnInfo(name = "weightSets")
    var weightSet:String?,

    @ColumnInfo(name = "breakTime")
    var breakTime:Int?,

    @NonNull
    @ColumnInfo(name ="equip")
    var equipment:String?,

    @NonNull
    @ColumnInfo(name = "isDone")
    var isDone:Boolean = false

):TrainBase{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int? = 0
}
