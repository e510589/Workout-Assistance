package com.lupo.lupo_trainer.newPlan

import com.lupo.lupo_trainer.BasePresenter
import com.lupo.lupo_trainer.BaseView

interface TrainListConcract {



    interface EditView:BaseView<Presenter> {

    }

    interface Presenter :BasePresenter{
        fun getTrainCount() : Int

    }
}