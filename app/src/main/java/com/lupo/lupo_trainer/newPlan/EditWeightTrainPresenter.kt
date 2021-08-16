package com.lupo.lupo_trainer.newPlan

class EditeWeightTrainPresenter(editeView:EditWeightTrainContract.View):EditWeightTrainContract.Presenter {


    companion object{
        private const val TAG = "EditWeightTrainPresenter"
    }

    init {
        editeView.setPresenter(this)
    }

    override fun start() {

    }

    override fun verifyWeightSet(
        planName: String,
        dateTime: String,
        muscles: String,
        movesName: String,
        times: String,
        breakTime: String,
        equip: String,
        weightSet: String,
        callBack: OnSaveTrainSetCallBack
    ) {
        if(muscles == "" || movesName =="" || times == "" ||breakTime =="" || equip == "" || weightSet == ""){
            callBack.onSetNotAvailable("Please filled all the data page.")
            return
        }

        val time = times.toInt()

        if (checkWeightSet(time,weightSet)){
            callBack.onSaved()
        }else{
            callBack.onSetNotAvailable("Weightset format error!")
            return
        }

    }

    override fun verifyCardioSet(
        planName: String,
        dateTime: String,
        movesName: String,
        times: String,
        equip: String,
        callBack: OnSaveTrainSetCallBack
    ) {
        if(movesName =="" || times == "" || equip == ""){
            callBack.onSetNotAvailable("Please filled all the data page.")
            return
        }else{
            val time = times.toInt()
            callBack.onSaved()
        }

    }

    fun checkWeightSet(times:Int, weightSet:String):Boolean{

        var dashCount = 0;
        for ( c in weightSet){
            if (c.isLetter() || c.equals('.') || c.equals(' ') || c.equals(',')){
                return false
            }
            if (c.equals('.')) return false
            if (c.equals('-')){
                dashCount++
            }
        }
        if (dashCount != times-1) return false

        val sets = weightSet.split('-')

        if (sets.size != times) return false

        return true
    }
}

fun main(){

    val s = "20-78-20-36-66"

    val o = object:EditWeightTrainContract.View{
        override fun setPresenter(presenter: EditWeightTrainContract.Presenter) {
        }
    }

    val e = EditeWeightTrainPresenter(o)

    val res = e.checkWeightSet(4,s)
    print(res)

}