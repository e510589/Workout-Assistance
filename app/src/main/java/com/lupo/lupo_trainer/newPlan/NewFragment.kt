package com.lupo.lupo_trainer.newPlan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lupo.lupo_trainer.databinding.FragNewPlanBinding

class NewFragment : Fragment(),NewPlanContract.NewView{

    private var _binding:FragNewPlanBinding? = null
    private val binding get() = _binding!!

    companion object{
        fun getInstance() :NewFragment{
            return NewFragment()
        }
    }

    private val onButtonClickListener = View.OnClickListener {
        mPresenter?.verifyNameDate(binding.textInputEditNewPlanName.text.toString(),binding.textInputEditNewPlanDate.text.toString(), object: NewPlanContract.OnVerifyCompleteCallback{
            override fun onSuccess() {
                val _activity = activity as NewPlanActivity
                _activity.onPlanInitialComplete()
            }

            override fun onNameVerifiedFailed() {
                Toast.makeText(activity,"Plan name required!", Toast.LENGTH_SHORT).show()
            }

            override fun onDateVerifiedFailed() {
                Toast.makeText(activity,"Plan date required!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private var mPresenter:NewPlanContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragNewPlanBinding.inflate(inflater,container,false)
        val rootView = binding.root
        binding.buttonConfirmNewPlan.setOnClickListener(onButtonClickListener)
        return rootView
    }

    override fun setPresenter(presenter: NewPlanContract.Presenter) {
        mPresenter = presenter
    }
}