package com.lupo.lupo_trainer.newPlan

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.databinding.FragEditWeighttrainSetBinding
import java.lang.NumberFormatException

class EditWeightTrainFragment : Fragment(),EditWeightTrainContract.View {


    private var _binding :FragEditWeighttrainSetBinding? = null
    private val binding get() = _binding!!
    private var mPresenter:EditWeightTrainContract.Presenter? = null
    private lateinit var planName:String
    private lateinit var planDate:String
    private val mViewModel:NewPlanViewModel by activityViewModels()

    companion object{
        private const val TAG = "EditWeightTrainFragment"
    }

    private val onMenuItemClicked = object:Toolbar.OnMenuItemClickListener{
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId){
                R.id.save_set->{

                    mPresenter?.saveWeightChange(
                        planName,
                        planDate,
                        binding.autoCompletetextviewMuscles.text.toString(),
                        binding.textInputEditMoveName.text.toString(),
                        binding.textInputEditMoveTimes.text.toString(),
                        binding.textInputEditBreaksTime.text.toString(),
                        binding.textInputEditEquip.text.toString(),
                        binding.textInputEditWeightSet.text.toString(),
                        object:OnSaveTrainSetCallBack{
                            override fun onSaved() {
                                Toast.makeText(activity,"Weight Set will be saved.",Toast.LENGTH_LONG).show()
                            }

                            override fun onSetNotAvailable(msg: String) {
                                Toast.makeText(activity,msg,Toast.LENGTH_LONG).show()
                            }
                        })

                    return true
                }
                R.id.cancel_train_set->{

                    val _activity = activity as NewPlanActivity
                    _activity.onTrainSetEditCancel()

                    return true
                }
                else ->{
                    return true
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragEditWeighttrainSetBinding.inflate(inflater,container,false)
        val rootView = binding.root
        val muscles = resources.getStringArray(R.array.muscles_list)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.item_dropdown_list_muscle,muscles)
        binding.autoCompletetextviewMuscles.setAdapter(arrayAdapter)
        binding.materialtoolbarEditTrain.changeToolbarFont()
        binding.materialtoolbarEditTrain.setOnMenuItemClickListener(onMenuItemClicked)
//        val map = requireArguments().get("MAP")!! as HashMap<String,String>
        this.planName = mViewModel.getName()
        this.planDate = mViewModel.getName()

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

    fun androidx.appcompat.widget.Toolbar.changeToolbarFont(){
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view is TextView && view.text == title) {
                view.typeface = Typeface.create(Typeface.SERIF,Typeface.NORMAL)
                break
            }
        }
    }

    override fun setPresenter(presenter: EditWeightTrainContract.Presenter) {
        this.mPresenter = presenter
    }
}