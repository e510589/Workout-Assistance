package com.lupo.lupo_trainer.newPlan

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.databinding.FragEditPlanBinding

class EditPlanFragment() :Fragment(),EditConcract.EditView{

    private var _binding:FragEditPlanBinding? = null
    private val binding get() =_binding!!
    private var mPresenter:EditConcract.Presenter? = null

    companion object{
        val TAG:String = "EditPlanFragment"
        private var instance:EditPlanFragment? = null
        fun getInstance():EditPlanFragment{
            if (instance == null){
                instance = EditPlanFragment()
            }
            return instance!!
        }
    }

    private var onMenuItemClickListener =
        Toolbar.OnMenuItemClickListener { item ->
            when(item?.itemId){
                R.id.cancel->{
                    Log.d(TAG,"cancel clicked")
                    true
                }
                R.id.new_set->{
                    Log.d(TAG,"new set clicked")
                    true
                }
                else->{
                    false
                }
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mPresenter?.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragEditPlanBinding.inflate(inflater,container,false)
        val rootView = binding.root
        val map = requireArguments().get("MAP")!! as HashMap<String,String>
        binding.materialToolbarEditPlan.title = map["N"]
        binding.materialToolbarEditPlan.subtitle = map["D"]
        binding.materialToolbarEditPlan.setOnMenuItemClickListener(onMenuItemClickListener)

        return rootView
    }

    override fun setPresenter(presenter: EditConcract.Presenter) {
        mPresenter=presenter
    }
}