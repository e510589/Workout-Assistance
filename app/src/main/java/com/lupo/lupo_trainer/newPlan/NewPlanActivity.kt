package com.lupo.lupo_trainer.newPlan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.lupo.lupo_trainer.R

class NewPlanActivity : AppCompatActivity(),OnNameDateComplete, OnTrainSetEditComplete {

    private var mPresenter:NewPlanContract.Presenter?=null
    private val TAG_FRAGMENT = "NEWFRAG"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NewFragment>(R.id.fragment_container_view_new_plan, "tag")
            }
        }
        val newFragment:NewFragment = supportFragmentManager.findFragmentByTag("tag") as NewFragment
        mPresenter = NewPlanPresenter(newFragment)
    }

    override fun onPlanInitialComplete() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<EditPlanFragment>(R.id.fragment_container_view_new_plan)
        }
    }

    override fun onTrainSetComplete() {
        TODO("Not yet implemented")
    }

    override fun onCancel() {
        TODO("Not yet implemented")
    }
}

interface OnNameDateComplete{
    fun onPlanInitialComplete()
}

interface OnTrainSetEditComplete{
    fun onTrainSetComplete()
    fun onCancel()
}