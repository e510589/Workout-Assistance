package com.lupo.lupo_trainer.utils.data.Train

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "TrainList")
data class TrainSet(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,

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
    @ColumnInfo(name = "cycles")
    var cycles:Int,

    @ColumnInfo(name = "weightSets")
    var weightSet:String?,

    @ColumnInfo(name = "timesSets")
    var timesSets:String?,

    @ColumnInfo(name = "breakTime")
    var breakTime:Int?,

    @NonNull
    @ColumnInfo(name ="equip")
    var equipment:String?,

    @NonNull
    @ColumnInfo(name = "isDone")
    var isDone:Boolean = false,

)

