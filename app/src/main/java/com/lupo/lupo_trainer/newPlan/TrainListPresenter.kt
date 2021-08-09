package com.lupo.lupo_trainer.newPlan

import android.util.Log
import com.lupo.lupo_trainer.utils.data.TrainBase
import com.lupo.lupo_trainer.utils.data.weightTrain.WeightTrainSet

class TrainListPresenter(mView:TrainListConcract.EditView):TrainListConcract.Presenter {

    private var trainSetList = ArrayList<TrainBase>()


    companion object{
        const val TAG = "EditPresenter"
    }

    init {
        mView.setPresenter(this)
    }

    override fun start() {
        Log.d(TAG, "start: presenter call fun start()")
    }

    override fun getTrainCount(): Int {
        return trainSetList.size
    }
}