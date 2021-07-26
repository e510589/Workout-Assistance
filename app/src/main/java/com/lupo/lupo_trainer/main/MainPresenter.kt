package com.lupo.lupo_trainer.main

import androidx.annotation.NonNull

class MainPresenter(@NonNull view:MainContract.View) : MainContract.Presenter {

    private val mView:MainContract.View = view

    init {
        mView.setPresenter(this)
    }

    override fun getDailyWorkout(onPlanLoadedCallBack: MainContract.OnPlanLoadedCallBack) {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }
}