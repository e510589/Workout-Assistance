package com.lupo.lupo_trainer.newPlan

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.lupo.lupo_trainer.databinding.FragNewPlanBinding
import java.util.*

class NewFragment : Fragment(),NewPlanContract.NewView{

    private var _binding:FragNewPlanBinding? = null
    private val binding get() = _binding!!

    companion object{
        private const val TAG = "NewFragment"

        private var instance:NewFragment? = null
        fun getInstance() :NewFragment{
            if (instance == null){
                instance = NewFragment()
            }
            return instance!!
        }
    }

    private var mPresenter:NewPlanContract.Presenter? = null

    private val onButtonClickListener = View.OnClickListener {
        mPresenter?.verifyNameDate(binding.textInputEditNewPlanName.text.toString(),binding.textInputEditNewPlanDate.text.toString(), object: NewPlanContract.OnVerifyCompleteCallback{
            override fun onSuccess() {
                val _activity = activity as NewPlanActivity
                _activity.onPlanInitialComplete(binding.textInputEditNewPlanName.text.toString(),binding.textInputEditNewPlanDate.text.toString())
            }

            override fun onNameVerifiedFailed() {
                Log.d(TAG,"plan name not specified")
                Toast.makeText(activity,"Plan name required!", Toast.LENGTH_SHORT).show()
            }

            override fun onDateVerifiedFailed() {
                Log.d(TAG,"plan date not specified")
                Toast.makeText(activity,"Plan date required!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val onDateEditorClickListener = View.OnClickListener {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this.requireContext(),object:DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                val text = "$year/$month/$dayOfMonth"
                binding.textInputEditNewPlanDate.setText(text)
            }
        },year,month,day)
        datePickerDialog.show()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragNewPlanBinding.inflate(inflater,container,false)
        val rootView = binding.root
        binding.buttonConfirmNewPlan.setOnClickListener(onButtonClickListener)
        binding.textInputEditNewPlanDate.setOnClickListener(onDateEditorClickListener)
        return rootView
    }

    override fun setPresenter(presenter: NewPlanContract.Presenter) {
        mPresenter = presenter
    }
}