package com.lupo.lupo_trainer.newPlan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.lupo.lupo_trainer.R

class NewPlanActivity : AppCompatActivity(),OnNameDateComplete, OnTrainSetEditComplete {

    private var mNewPresenter:NewPlanPresenter?=null
    private var mEditPresenter:EditPresenter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        if(savedInstanceState == null) {
            supportFragmentManager.fragmentFactory = MyFragmentFactory()
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NewFragment>(R.id.fragment_container_view_new_plan)
            }
            mNewPresenter = NewPlanPresenter(NewFragment.getInstance())
        }
    }


    override fun onPlanInitialComplete(planName: String, planDate: String) {
        var map = HashMap<String,String>()
        map.put("N",planName)
        map.put("D",planDate)
        val bundle = bundleOf("MAP" to map)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<EditPlanFragment>(R.id.fragment_container_view_new_plan,args = bundle)
        }
        mEditPresenter = EditPresenter(EditPlanFragment.getInstance())

    }

    override fun onTrainSetComplete() {
        TODO("Not yet implemented")
    }

    override fun onCancel() {
        TODO("Not yet implemented")
    }
}

private class MyFragmentFactory() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (loadFragmentClass(classLoader, className)) {
            NewFragment::class.java -> {
                NewFragment.getInstance()
            }
            EditPlanFragment::class.java ->{
                EditPlanFragment.getInstance()
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}

interface OnNameDateComplete{
    fun onPlanInitialComplete(planName:String,planDate:String)
}

interface OnTrainSetEditComplete{
    fun onTrainSetComplete()
    fun onCancel()
}