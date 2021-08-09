package com.lupo.lupo_trainer.newPlan

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.lupo.lupo_trainer.R

class NewPlanActivity : AppCompatActivity(),IFragmentCallBack {

    private var mNewPresenter:NewPlanPresenter?=null
    private var mTrainListPresenter:TrainListPresenter?= null
    private var mEditeWeightTrainPresenter:EditeWeightTrainPresenter? = null

    companion object{
        const val TAG = "NewPlanActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        val newFragment = NewFragment()
        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view_new_plan,newFragment)
            }
            mNewPresenter = NewPlanPresenter(newFragment)
        }
    }

    override fun onNameDateConfirm(planName: String, planDate: String) {
//        var map = HashMap<String,String>()
//        map.put("N",planName)
//        map.put("D",planDate)
//        val bundle = bundleOf("MAP" to map)
        val trainListFragment = TrainListFragment()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view_new_plan,trainListFragment)
        }
        mTrainListPresenter = TrainListPresenter(trainListFragment)
    }

    override fun onNameDateSetCancel() {
        supportFragmentManager.popBackStack()
        finish()
    }

    override fun onNewPlanFinished() {
        supportFragmentManager.popBackStack()
        finish()
    }

    override fun onAddTrainSet(name:String,date:String) {
//        var map = HashMap<String,String>()
//        map.put("N",name)
//        map.put("D",date)
//        val bundle = bundleOf("MAP" to map)
        val weightSetFragment = EditWeightTrainFragment()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view_new_plan,weightSetFragment)
            addToBackStack(null)
        }
        mEditeWeightTrainPresenter = EditeWeightTrainPresenter(weightSetFragment)
    }

    override fun onTrainSetEditConfirm() {

    }

    override fun onTrainSetEditCancel() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        val fragmentSize =supportFragmentManager.fragments.size
        when {
            supportFragmentManager.fragments[fragmentSize-1] is NewFragment -> {
                Log.d(TAG,"Back key pressed on NewFragment")
                onTrainSetEditCancel()
            }
            supportFragmentManager.fragments[fragmentSize-1] is TrainListFragment -> {
                Log.d(TAG,"Back key pressed on TrainListFragment")
                onNewPlanFinished()
            }
            supportFragmentManager.fragments[fragmentSize-1] is EditWeightTrainFragment -> {
                Log.d(TAG,"Back key pressed on EditWeightTrainFragment")
                onTrainSetEditCancel()
            }
        }
    }
}

//private class MyFragmentFactory() : FragmentFactory() {
//
//    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        return when (loadFragmentClass(classLoader, className)) {
//            NewFragment::class.java -> {
//                NewFragment.getInstance()
//            }
//            TrainListFragment::class.java ->{
//                TrainListFragment.getInstance()
//            }
//            EditWeightTrainFragment::class.java ->{
//                EditWeightTrainFragment.getInstance()
//            }
//            else -> {
//                super.instantiate(classLoader, className)
//            }
//        }
//    }
//}

interface IFragmentCallBack{
    /**
     * Call by NewPlanFragment.
     */
    fun onNameDateConfirm(planName:String,planDate:String)
    fun onNameDateSetCancel()

    /**
     * Call by TrainlistFragment.
     */
    fun onNewPlanFinished()
    fun onAddTrainSet(name:String,date:String)

    /**
     * Call by EditeTrainFragment
     */
    fun onTrainSetEditConfirm()
    fun onTrainSetEditCancel()
}