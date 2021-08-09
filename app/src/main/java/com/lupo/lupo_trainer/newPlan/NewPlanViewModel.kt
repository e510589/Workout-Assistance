package com.lupo.lupo_trainer.newPlan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewPlanViewModel : ViewModel() {

    private var planName: MutableLiveData<String> = MutableLiveData()

    private var planDate: MutableLiveData<String> = MutableLiveData()

    init {
        planName.value = ""
        planDate.value = ""
    }

    fun getName(): String {
        if (planName.value != null) {
            return planName.value!!
        }

        return ""
    }

    fun getDate(): String {
        if (planDate.value != null) {
            return planDate.value!!
        }

        return ""
    }

    fun setName(name: String) {
        this.planName.value = name
    }

    fun setDate(date:String){
        this.planDate.value = date
    }

}

