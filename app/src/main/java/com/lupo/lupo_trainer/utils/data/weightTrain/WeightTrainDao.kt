package com.lupo.lupo_trainer.utils.data.weightTrain

import androidx.room.*

@Dao
interface WeightTrainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertALL(vararg trainSets: WeightTrainSet)

    @Query("SELECT * FROM WeightTrainList")
    fun getALL() : List<WeightTrainSet>

    @Query("SELECT * FROM WeightTrainList WHERE target IN (:target)")
    fun loadAllByTarget(target:String) :List<WeightTrainSet>

    @Query("SELECT * FROM WeightTrainList WHERE date IN (:date)")
    fun loadAllByDate(date:String) : List<WeightTrainSet>

    @Query("SELECT * FROM WeightTrainList WHERE muscles IN (:muscles)")
    fun loadALLByMuscles(muscles:String) : List<WeightTrainSet>

    @Query("SELECT * FROM WeightTrainList WHERE movesName IN (:movesName)")
    fun loadALLByMovesName(movesName:String) : List<WeightTrainSet>

    @Query("SELECT * FROM WeightTrainList WHERE equip IN (:equipment)")
    fun loadALLByEquipment(equipment:String) : List<WeightTrainSet>

    @Update
    fun update(trainSet:WeightTrainSet);

    @Delete
    fun delete(trainSet:WeightTrainSet);
}