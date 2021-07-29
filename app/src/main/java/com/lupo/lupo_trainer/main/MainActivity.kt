package com.lupo.lupo_trainer.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.lupo.lupo_trainer.R

class MainActivity : AppCompatActivity() {

    var mPresenter:MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.fragmentFactory = MyFragmentFactory()
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.fragment_container_main)
            }
        }

        mPresenter = MainPresenter(MainFragment.getInstance())
    }
}

private class MyFragmentFactory :FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (loadFragmentClass(classLoader, className)) {
            MainFragment::class.java->{
                MainFragment.getInstance()
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}