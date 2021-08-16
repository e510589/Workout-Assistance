package com.lupo.lupo_trainer.newPlan

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.main.MainFragment

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

    override fun onNameDateConfirm() {
        val trainListFragment = TrainListFragment()
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out)
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

    override fun onAddTrainSet() {
        val weightSetFragment = EditWeightTrainFragment()
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out)
            setReorderingAllowed(true)
            add(R.id.fragment_container_view_new_plan,weightSetFragment)
            addToBackStack(null)
        }
        mEditeWeightTrainPresenter = EditeWeightTrainPresenter(weightSetFragment)
    }

    override fun onTrainSetEditDone() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        val fragmentSize =supportFragmentManager.fragments.size
        when {
            supportFragmentManager.fragments[fragmentSize-1] is NewFragment -> {
                Log.d(TAG,"Back key pressed on NewFragment")
                onNameDateSetCancel()
            }
            supportFragmentManager.fragments[fragmentSize-1] is TrainListFragment -> {
                Log.d(TAG,"Back key pressed on TrainListFragment")
                onNewPlanFinished()
            }
            supportFragmentManager.fragments[fragmentSize-1] is EditWeightTrainFragment -> {
                Log.d(TAG,"Back key pressed on EditWeightTrainFragment")
                onTrainSetEditDone()
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
    fun onNameDateConfirm()
    fun onNameDateSetCancel()

    /**
     * Call by TrainlistFragment.
     */
    fun onNewPlanFinished()
    fun onAddTrainSet()

    /**
     * Call by EditeTrainFragment
     */
    fun onTrainSetEditDone()
}