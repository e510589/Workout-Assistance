package com.lupo.lupo_trainer.newPlan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lupo.lupo_trainer.databinding.FragEditPlanBinding
import com.lupo.lupo_trainer.databinding.FragNewPlanBinding

class EditPlanFragment :Fragment() {

    private var _binding:FragEditPlanBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragEditPlanBinding.inflate(inflater,container,false)
        val rootView = binding.root
        return rootView
    }
}