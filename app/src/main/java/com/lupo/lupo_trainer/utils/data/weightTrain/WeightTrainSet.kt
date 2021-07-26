package com.lupo.lupo_trainer.utils.data.weightTrain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeightTrainList")
data class WeightTrainSet(

    @NonNull
    @ColumnInfo(name = "target")
    var target:String,

    @ColumnInfo(name = "listName")
    var listName:String?,

    @NonNull
    @ColumnInfo(name = "date")
    var date:String,

    @NonNull
    @ColumnInfo(name = "muscles")
    var muscles:String,

    @NonNull
    @ColumnInfo(name = "movesName")
    var movesName:String,

    @NonNull
    @ColumnInfo(name = "times")
    var times:Int,

    @NonNull
    @ColumnInfo(name = "weightSets")
    var weightSet:String,

    @NonNull
    @ColumnInfo(name = "breakTime")
    var breakTime:Int,

    @ColumnInfo(name ="equip")
    var equipment:String?


){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int? = 0
}
