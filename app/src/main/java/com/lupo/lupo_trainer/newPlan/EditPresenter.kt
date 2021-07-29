package com.lupo.lupo_trainer.newPlan

import android.util.Log

class EditPresenter(mView:EditConcract.EditView):EditConcract.Presenter {


    companion object{
        const val TAG = "EditPresenter"
    }

    init {
        mView.setPresenter(this)
    }

    override fun start() {
        Log.d(TAG, "start: presenter call fun start()")
    }
}