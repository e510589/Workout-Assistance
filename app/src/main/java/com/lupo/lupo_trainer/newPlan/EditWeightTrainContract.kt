package com.lupo.lupo_trainer.newPlan

import com.lupo.lupo_trainer.BasePresenter
import com.lupo.lupo_trainer.BaseView

interface EditWeightTrainContract {

    interface View : BaseView<Presenter>{

    }


    interface Presenter : BasePresenter{

        fun saveWeightChange(planName:String,dateTime:String,muscles:String,movesName:String,times:String,breakTime:String,equip:String,weightSet:String,callBack: OnSaveTrainSetCallBack)

    }


}

interface OnSaveTrainSetCallBack {

    fun onSaved()

    fun onSetNotAvailable(msg:String)
}