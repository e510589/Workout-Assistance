package com.lupo.lupo_trainer.main


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.databinding.FragMainBinding
import com.lupo.lupo_trainer.main.serviceItem.ServiceItem
import com.lupo.lupo_trainer.newPlan.NewPlanActivity
import kotlin.math.abs

class MainFragment : Fragment(),MainContract.View{

    /**
     * This property is only valid between onCreateView and onDestroyView.
     * If you don't want to do null checking everytime when you call binding.
     * You can user another object to store the _binding object and assert it
     * as an non-null object by "!!" operator
     */
    private var _binding: FragMainBinding? = null
    private val binding get() = _binding!!
    private var mPresenter: MainContract.Presenter? = null

    companion object{
        const val TAG = "MainFragment"
        const val SERVICE_QUICK_START = 0
        const val SERVICE_NEW_MOVE = 1
        const val SERVICE_EDIT_PLAN = 2
        const val PLAN_TYPE_WEIGHT = "WEIGHT"
        const val PLAN_TYPE_CARDIO = "CARDIO"
        val TRAIN_ARRAY= arrayOf("Weight Train","Cardio Train")

        private var instance:MainFragment? = null

        fun getInstance():MainFragment{
            if (instance == null){
                instance = MainFragment()
            }
            return instance!!
        }
    }

    private var serviceList:ArrayList<ServiceItem> = ArrayList()
    private lateinit var viewPager:ViewPager2

    private var activityLauncher = registerForActivityResult(MainActivityResultContract()){
        //TODO
    }

    private var onServiceClicked:OnServiceClicked = object : OnServiceClicked {
        override fun onClicked(pos: Int) {
            when(pos){
                SERVICE_QUICK_START ->{
                    Log.d(TAG,getString(R.string.service_name_quick_start) +"clicked")
                }
                SERVICE_EDIT_PLAN->{
                    Log.d(TAG,getString(R.string.service_name_edit_plan) +"clicked")
                }
                SERVICE_NEW_MOVE->{
                    Log.d(TAG,getString(R.string.service_name_new_move) +"clicked")

                    val builder = AlertDialog.Builder(activity).setSingleChoiceItems(TRAIN_ARRAY,0
                    ) { dialog, which ->
                        when (which) {
                            0 -> {
                                dialog.dismiss()
                                activityLauncher.launch(PLAN_TYPE_WEIGHT)
                            }
                            1 -> {
                                dialog.dismiss()
                                activityLauncher.launch(PLAN_TYPE_CARDIO)
                            }
                        }
                    }
                    builder.show()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceList.add(ServiceItem(getString(R.string.service_name_quick_start),R.drawable.quick_start))
        serviceList.add(ServiceItem(getString(R.string.service_name_new_move),R.drawable.new_workout))
        serviceList.add(ServiceItem(getString(R.string.service_name_edit_plan),R.drawable.edit_plan))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragMainBinding.inflate(inflater,container,false)
        val rootView = binding.root
        setViewPager()
        return rootView
    }

    private fun setViewPager(){
        viewPager = binding.viewpager2Serivce
        viewPager.adapter = ServiceAdapter(serviceList,onServiceClicked)
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r:Float = 1- abs(position)
            page.scaleY = 0.85f + r*0.15f
        }
        viewPager.setPageTransformer(compositePageTransformer)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.mPresenter = presenter
    }
}

class MainActivityResultContract:ActivityResultContract<String,String>(){
    override fun createIntent(context: Context, input: String?): Intent {
        val intent = Intent(context,NewPlanActivity::class.java)
        intent.putExtra(MainFragment.PLAN_TYPE_WEIGHT , input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra("RESULT")
        return if (resultCode == Activity.RESULT_OK && data != null) data
        else null
    }
}

