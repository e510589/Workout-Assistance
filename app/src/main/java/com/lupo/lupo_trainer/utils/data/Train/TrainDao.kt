package com.lupo.lupo_trainer.utils.data.Train

import androidx.room.*

@Dao
interface TrainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trainSet: TrainSet):Long?

    @Query("SELECT * FROM TrainList")
    fun getALL() : List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE listName IN (:listName)")
    fun loadAllByTarget(listName:String) :List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE trainType IN (:trainType)")
    fun loadAllByTarget(trainType:Int) :List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE date IN (:date)")
    fun loadAllByDate(date:String) : List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE listName IN (:listName)")
    fun loadAllByListName(listName:String) : List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE listName IN (:listName) AND date IN (:date)")
    fun loadAllByListNameAndDate(listName:String, date:String) : List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE muscles IN (:muscles)")
    fun loadALLByMuscles(muscles:String) : List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE movesName IN (:movesName)")
    fun loadALLByMovesName(movesName:String) : List<TrainSet>

    @Query("SELECT * FROM TrainList WHERE equip IN (:equipment)")
    fun loadALLByEquipment(equipment:String) : List<TrainSet>

    @Update
    fun update(trainSet:TrainSet);

    @Delete
    fun delete(trainSet:TrainSet);
}