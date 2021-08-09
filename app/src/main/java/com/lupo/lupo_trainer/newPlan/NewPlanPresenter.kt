package com.lupo.lupo_trainer.newPlan

import android.util.Log
import android.widget.Toast

class NewPlanPresenter(newView:NewPlanContract.NewView) : NewPlanContract.Presenter {

    companion object{
        private val TAG = "NewPlanPresenter"
    }

    init {
        Log.d(TAG,"New instance of NewPlanPresenter created!")
        newView.setPresenter(this)
    }

    override fun verifyNameDate(name: String, date: String, callback: NewPlanContract.OnVerifyCompleteCallback) {
        when {
            name == "" -> {
                callback.onNameVerifiedFailed()
            }
            date == "" -> {
                callback.onDateVerifiedFailed()
            }
            else -> {
                callback.onSuccess()
            }
        }
    }

    override fun start() {
        TODO("Not yet implemented")
    }


}