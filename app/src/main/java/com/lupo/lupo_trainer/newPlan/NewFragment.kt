package com.lupo.lupo_trainer.newPlan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lupo.lupo_trainer.databinding.FragNewPlanBinding

class NewFragment : Fragment(){

    private var _binding:FragNewPlanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragNewPlanBinding.inflate(inflater,container,false)
        val rootView = binding.root
        return rootView
    }
}