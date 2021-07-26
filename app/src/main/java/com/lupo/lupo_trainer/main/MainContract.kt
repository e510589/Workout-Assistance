package com.lupo.lupo_trainer.main

import com.lupo.lupo_trainer.BasePresenter
import com.lupo.lupo_trainer.BaseView

interface MainContract {

    interface View : BaseView<Presenter>{

    }

    interface Presenter: BasePresenter{
        fun getDailyWorkout(onPlanLoadedCallBack:OnPlanLoadedCallBack)
    }

    interface OnPlanLoadedCallBack{
        fun onPlanLoaded(listName:String, setsNumber:Int, muscles:String)
        fun onPlanNotAvailable()
    }

}