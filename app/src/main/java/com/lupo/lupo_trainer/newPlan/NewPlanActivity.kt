package com.lupo.lupo_trainer.newPlan

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.main.MainFragment

class NewPlanActivity : AppCompatActivity()  {


    private val onNameDateComplete = object : OnNameDateComplete {
        override fun onComplete() {
            TODO("Not yet implemented")
        }

        override fun onCancel() {
            TODO("Not yet implemented")
        }
    }

    private var onEditComplete = object :OnEditComplete{
        override fun onComplete() {
            TODO("Not yet implemented")
        }

        override fun onCancel() {
            TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NewFragment>(R.id.fragment_container_new)
            }
        }
    }
}

interface OnNameDateComplete{
    fun onComplete()
    fun onCancel()
}

interface OnEditComplete{
    fun onComplete()
    fun onCancel()
}