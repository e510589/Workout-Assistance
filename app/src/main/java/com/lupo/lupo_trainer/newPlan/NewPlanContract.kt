package com.lupo.lupo_trainer.newPlan

import com.lupo.lupo_trainer.BasePresenter
import com.lupo.lupo_trainer.BaseView

interface NewPlanContract {

    interface NewView : BaseView<Presenter>{

    }

    interface Presenter : BasePresenter{

        fun verifyNameDate(name:String, date:String, callback:OnVerifyCompleteCallback)

    }

    interface OnVerifyCompleteCallback{
        fun onSuccess()
        fun onNameVerifiedFailed()
        fun onDateVerifiedFailed()

    }
}

