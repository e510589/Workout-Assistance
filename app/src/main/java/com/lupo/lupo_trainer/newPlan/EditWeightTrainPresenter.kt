package com.lupo.lupo_trainer.newPlan

import com.lupo.lupo_trainer.utils.data.Train.TrainDataSource
import com.lupo.lupo_trainer.utils.data.Train.TrainSet

class EditeWeightTrainPresenter(val editeView:EditWeightTrainContract.View,val trainDataSource: TrainDataSource):EditWeightTrainContract.Presenter {


    companion object{
        private const val TAG = "EditWeightTrainPresenter"
    }

    init {
        editeView.setPresenter(this)
    }

    override fun start() {

    }

    override fun verifyWeightSet(
        planName: String,
        dateTime: String,
        muscles: String,
        movesName: String,
        cycles: String,
        breakTime: String,
        equip: String,
        weightSet: String,
        timesSet: String,
        callBack: OnSaveTrainSetCallBack
    ) {
        if(muscles == "" || movesName =="" || cycles == "" ||breakTime =="" || equip == "" || weightSet == ""||timesSet == ""){
            callBack.onSetNotAvailable("Please filled all the data page.")
            return
        }

        val cycle = cycles.toInt()

        if (isSetsAvailable(cycle,weightSet) && isSetsAvailable(cycle,timesSet)){

            trainDataSource.saveTrainSet(TrainSet(null,0,planName,dateTime,muscles,movesName,cycle,weightSet,timesSet,breakTime.toInt(),equip),
                object : TrainDataSource.OnDataSavedCallBack {
                    override fun onTrainSetSaved() {
                        callBack.onSaved()
                    }

                    override fun onFailed() {
                        callBack.onSetNotAvailable("Error while save to Room DB.")
                    }
                })
        }else{
            callBack.onSetNotAvailable("Weightsets or Timesets format error!")
            return
        }
    }

    override fun verifyCardioSet(
        planName: String,
        dateTime: String,
        movesName: String,
        times: String,
        equip: String,
        callBack: OnSaveTrainSetCallBack
    ) {
        if(movesName =="" || times == "" || equip == ""){
            callBack.onSetNotAvailable("Please filled all the data page.")
            return
        }else{
            val time = times.toInt()
            callBack.onSaved()
        }

    }

    fun isSetsAvailable(cycles:Int, sets:String):Boolean{

        var dashCount = 0;
        for ( c in sets){
            if (c.isLetter() || c.equals('.') || c.equals(' ') || c.equals(',')){
                return false
            }
            if (c.equals('.')) return false
            if (c.equals('-')){
                dashCount++
            }
        }
        if (dashCount != cycles-1) return false

        val sets = sets.split('-')

        if (sets.size != cycles) return false

        return true
    }


}

fun main(){
}