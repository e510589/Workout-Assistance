package com.lupo.lupo_trainer.utils.data.cardioTrain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CardioTrainList")
data class CardioTrainSet(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val uid:Int = 0,

    @NonNull
    @ColumnInfo(name = "trainType")
    val trainType:Int = 0,

    @NonNull
    @ColumnInfo(name = "target")
    var target:String,


    @ColumnInfo(name = "listName")
    var listName:String?,

    @NonNull
    @ColumnInfo(name = "dateTime")
    var dataTime:String,

    @NonNull
    @ColumnInfo(name = "movesName")
    var movesName:String,

    @NonNull
    @ColumnInfo(name = "times")
    var times:Int,

    @ColumnInfo(name ="equip")
    var equipment:String?



)
