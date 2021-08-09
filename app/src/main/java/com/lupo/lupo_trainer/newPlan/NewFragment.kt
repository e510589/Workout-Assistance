package com.lupo.lupo_trainer.newPlan

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.databinding.FragNewPlanBinding
import java.util.*

class NewFragment : Fragment(),NewPlanContract.NewView{

    private var _binding:FragNewPlanBinding? = null
    private val binding get() = _binding!!

    private val mViewModel: NewPlanViewModel by activityViewModels()

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
                mViewModel.setName(binding.textInputEditNewPlanName.text.toString())
                mViewModel.setDate(binding.textInputEditNewPlanDate.text.toString())
                val _activity = activity as NewPlanActivity
                _activity.onNameDateConfirm(binding.textInputEditNewPlanName.text.toString(),binding.textInputEditNewPlanDate.text.toString())
            }

            override fun onNameVerifiedFailed() {
                Log.d(TAG,"plan name not specified")
                Toast.makeText(activity,"Plan name required!", Toast.LENGTH_SHORT).show()
//                Snackbar.make(binding.constrainlayoutNewPlan,"Plan name required!",Snackbar.LENGTH_SHORT).show()
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
                var _month = month+1
                val text = "$year/$_month/$dayOfMonth"
                binding.textInputEditNewPlanDate.setText(text)
                val _activity = activity as NewPlanActivity
                val view = _activity.currentFocus
                val imm = _activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
            }
        },year,month,day)
        datePickerDialog.show()
    }

    private val onFocusChangeListener = object : View.OnFocusChangeListener{
        override fun onFocusChange(v: View?, hasFocus: Boolean) {
            if (hasFocus){
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(this@NewFragment.requireContext(),object:DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        var _month = month+1
                        val text = "$year/$_month/$dayOfMonth"
                        binding.textInputEditNewPlanDate.setText(text)

                    }
                },year,month,day)
                datePickerDialog.show()

            }
        }

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