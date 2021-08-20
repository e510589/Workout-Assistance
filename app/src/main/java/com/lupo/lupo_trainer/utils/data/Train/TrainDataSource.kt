package com.lupo.lupo_trainer.utils.data.Train

import com.lupo.lupo_trainer.utils.AppExcutors

class TrainDataSource (appExcutors: AppExcutors, trainDao: TrainDao){

    private var appExcutors = appExcutors
    private var trainDao = trainDao

    interface OnDataLoadedCallBack{
        fun onTrainSetLoaded(trainSet:TrainSet)
        fun onTrainSetNotAvailable()
    }

    interface OnListDataLoadedCallBack{
        fun onTrainListLoaded(trainList:List<TrainSet>)
        fun onTrainListNotAvailable()
    }

    interface OnDataSavedCallBack{
        fun onTrainSetSaved()
        fun onFailed()
    }

    interface OnListDataSavedCallBack{
        fun onTrainListSaved()
        fun  onFailed()
    }

    companion object{
        private const val TAG = "TrainDataSource"
        var instance:TrainDataSource?= null
        fun getInstance(appExcutors: AppExcutors, trainDao: TrainDao):TrainDataSource{
            if (instance == null){
                instance = TrainDataSource(appExcutors,trainDao)
            }
            return instance!!
        }
    }

    fun saveTrainSet(trainSet:TrainSet, callBack: OnDataSavedCallBack){
        appExcutors.diskIO.execute(object:Runnable{
            override fun run() {
                var rowId = trainDao.insert(trainSet)
                appExcutors.mainThread.execute{
                    if(rowId != 0L){
                        callBack.onTrainSetSaved()
                    }else{
                        callBack.onFailed()
                    }
                }
            }
        })
    }

    fun loadedTranSetByPlan(name:String,date:String,callBack: OnListDataLoadedCallBack){
        appExcutors.diskIO.execute(object:Runnable{
            override fun run() {
                val trainSets = trainDao.loadAllByListNameAndDate(name,date)
                appExcutors.mainThread.execute {
                    if (trainSets != null){
                        callBack.onTrainListLoaded(trainSets)
                    }else{
                        callBack.onTrainListNotAvailable()
                    }
                }
            }
        })


    }


}