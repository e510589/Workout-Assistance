package com.lupo.lupo_trainer.newPlan

import android.widget.Toast

class NewPlanPresenter(newView:NewPlanContract.NewView) : NewPlanContract.Presenter {


    init {
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