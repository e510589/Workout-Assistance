package com.lupo.lupo_trainer.newPlan

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.databinding.FragTrainListBinding

class TrainListFragment() :Fragment(),TrainListConcract.EditView{

    private var _binding:FragTrainListBinding? = null
    private val binding get() =_binding!!
    private lateinit var mPresenter:TrainListConcract.Presenter

    private var planName:String = ""
    private var planDate:String = ""

    private val mViewModel:NewPlanViewModel by activityViewModels()

    companion object{
        const val RESULT_KEY = "TrainListFragment_KEY"
        const val RESULT_BUNDLE_KEY = "BUNDLE_KEY"
        const val RESULT_CANCEL = "SET_CANCEL"
        const val RESULT_SET_DONE = "SET_DONE"
        private const val TAG = "TrainListFragment"
    }

    private var onMenuItemClickListener =
        Toolbar.OnMenuItemClickListener { item ->
            when(item?.itemId){
                R.id.cancel->{
                    val _activity = activity as NewPlanActivity
                    var count = mPresenter.getTrainCount()
                    if(count == 0){

                        val dialog = AlertDialog.Builder(activity).setMessage(getString(R.string.current_plan_will_be_cancel)).
                        setPositiveButton(getString(R.string.confirm),object:DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog?.dismiss()
                                _activity.onNewPlanFinished()
                            }
                        })
                        dialog.show()

                    }else if(count > 0){
                        val dialog = AlertDialog.Builder(activity).setMessage("The new plan will be saved properly.\n Clicked confirm button to leave this page.").
                        setPositiveButton(getString(R.string.confirm),object:DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog?.dismiss()
                                _activity.onNewPlanFinished()
                            }
                        })
                        dialog.show()
                    }
                    true
                }
                R.id.new_set->{
                    Log.d(TAG,"new set clicked")
                    val _activity = activity as NewPlanActivity
                    _activity.onAddTrainSet()
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setFragmentResultListener(RESULT_KEY){
            requestKey, bundle ->
            val result = bundle.getString(RESULT_BUNDLE_KEY)
            when(result){
                RESULT_CANCEL ->{
                    Toast.makeText(activity,"Edit has been canceled.",Toast.LENGTH_LONG).show()
                }
                RESULT_SET_DONE ->{
                    Toast.makeText(activity,"Edit set done.",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragTrainListBinding.inflate(inflater,container,false)
        val rootView = binding.root
//        val map = requireArguments().get("MAP")!! as HashMap<String,String>
        this.planName = mViewModel.getName()
//        this.planName = map["N"]!!
        binding.textviewToolbarTilte.text = planName
        this.planDate = mViewModel.getDate()
//        this.planDate = map["D"]!!
        binding.textviewToolbarSubtilte.text = planDate
        binding.materialToolbarTrainList.setOnMenuItemClickListener(onMenuItemClickListener)
        return rootView
    }



    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun setPresenter(presenter: TrainListConcract.Presenter) {
        mPresenter=presenter
    }
}