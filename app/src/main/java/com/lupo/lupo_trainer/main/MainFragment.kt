package com.lupo.lupo_trainer.main


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.databinding.FragMainBinding
import com.lupo.lupo_trainer.main.serviceItem.ServiceItem

class MainFragment : Fragment() {
    /**
     * This property is only valid between onCreateView and onDestroyView.
     * If you don't want to do null checking everytime when you call binding.
     * You can user another object to store the _binding object and assert it
     * as an non-null object by "!!" operator
     */

    companion object{
        val SERVICE_QUICK_START = 0
        val SERVICE_EDIT_PLAN = 1
        val SERVICE_NEW_MOVE = 3
        val TAG = "MainFragment"
    }

    private var _binding: FragMainBinding? = null
    private val binding get() = _binding!!

    private var serviceList:ArrayList<ServiceItem> = ArrayList()
    private lateinit var viewPager:ViewPager2

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

        var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r:Float = 1-Math.abs(position)
            page.scaleY = 0.85f + r*0.15f
        }
        viewPager.setPageTransformer(compositePageTransformer)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

