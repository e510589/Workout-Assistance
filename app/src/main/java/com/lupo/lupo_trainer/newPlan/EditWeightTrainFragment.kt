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
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.databinding.FragEditTrainSetBinding
import com.lupo.lupo_trainer.databinding.FragTrainListBinding

class EditWeightTrainFragment : Fragment(),EditWeightTrainContract.View {


    private var _binding :FragEditTrainSetBinding? = null
    private val binding get() = _binding!!
    private var mPresenter:EditWeightTrainContract.Presenter? = null
    private lateinit var planName:String
    private lateinit var planDate:String
    private val mViewModel:NewPlanViewModel by activityViewModels()
    private lateinit var muscles:Array<String>
    private lateinit var types:Array<String>

    companion object{
        private const val TAG = "EditWeightTrainFragment"
    }

    private val onMenuItemClicked = object:Toolbar.OnMenuItemClickListener{
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId){
                R.id.save_set->{
                    when(binding.autoCompletetextviewTraintype.text.toString()){

                        types.get(0)->{
                            mPresenter?.verifyWeightSet(
                                planName,
                                planDate,
                                binding.autoCompletetextviewMuscles.text.toString(),
                                binding.textInputEditMoveName.text.toString(),
                                binding.textInputEditMoveCycles.text.toString(),
                                binding.textInputEditBreaksTime.text.toString(),
                                binding.textInputEditEquip.text.toString(),
                                binding.textInputEditWeightSet.text.toString(),
                                binding.textInputEditTimesSet.text.toString(),
                                object:OnSaveTrainSetCallBack{
                                    override fun onSaved() {
                                        Toast.makeText(activity,getString(R.string.train_set_will_be_saved),Toast.LENGTH_LONG).show()
                                        setFragmentResult(TrainListFragment.RESULT_KEY,
                                            bundleOf(TrainListFragment.RESULT_BUNDLE_KEY to TrainListFragment.RESULT_SET_DONE))
                                        val _activity = activity as NewPlanActivity
                                        _activity.onTrainSetEditDone()
                                    }

                                    override fun onSetNotAvailable(msg: String) {
                                        Toast.makeText(activity,msg,Toast.LENGTH_LONG).show()
                                    }
                                })
                        }
                        types.get(1)->{
                            mPresenter?.verifyCardioSet(
                                planName,
                                planDate,
                                binding.textInputEditMoveName.text.toString(),
                                binding.textInputEditMoveCycles.text.toString(),
                                binding.textInputEditEquip.text.toString(),
                                object:OnSaveTrainSetCallBack{
                                    override fun onSaved() {
                                        Toast.makeText(activity,getString(R.string.train_set_will_be_saved),Toast.LENGTH_LONG).show()
                                        setFragmentResult(TrainListFragment.RESULT_KEY,
                                            bundleOf(TrainListFragment.RESULT_BUNDLE_KEY to TrainListFragment.RESULT_SET_DONE))
                                        val _activity = activity as NewPlanActivity
                                        _activity.onTrainSetEditDone()
                                    }

                                    override fun onSetNotAvailable(msg: String) {
                                        Toast.makeText(activity,msg,Toast.LENGTH_LONG).show()
                                    }
                                })
                        }
                    }
                    return true
                }
                R.id.cancel_train_set->{

                    setFragmentResult(TrainListFragment.RESULT_KEY,
                        bundleOf(TrainListFragment.RESULT_BUNDLE_KEY to TrainListFragment.RESULT_CANCEL))

                    val _activity = activity as NewPlanActivity
                    _activity.onTrainSetEditDone()

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
        muscles = resources.getStringArray(R.array.muscles_list)
        types = resources.getStringArray(R.array.trainTypes)
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

        _binding = FragEditTrainSetBinding.inflate(inflater,container,false)
        val rootView = binding.root
        val musAdapter = ArrayAdapter(requireContext(),R.layout.item_dropdown_list_muscle,muscles)
        binding.autoCompletetextviewMuscles.setAdapter(musAdapter)

        val typeAdapter = ArrayAdapter(requireContext(),R.layout.item_dropdown_list_traintypes,types)
        binding.autoCompletetextviewTraintype.setAdapter(typeAdapter)
        binding.autoCompletetextviewTraintype.setText(typeAdapter.getItem(0).toString(),false)
        binding.autoCompletetextviewTraintype.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0->{
                    Log.d(TAG,typeAdapter.getItem(0).toString() + " clicked!")
                    binding.relativelayoutEditMuscles.visibility = View.VISIBLE
                    binding.relativelayoutEditBreaksTime.visibility = View.VISIBLE
                    binding.relativelayoutEditWeightSet.visibility = View.VISIBLE
                    binding.relativelayoutEditTimesSet.visibility = View.VISIBLE
                }
                1->{
                    Log.d(TAG,typeAdapter.getItem(1).toString() + " clicked!")
                    binding.relativelayoutEditMuscles.visibility = View.GONE
                    binding.relativelayoutEditBreaksTime.visibility = View.GONE
                    binding.relativelayoutEditWeightSet.visibility = View.GONE
                    binding.relativelayoutEditTimesSet.visibility = View.GONE
                }
                else->{

                }
            }
        }

        binding.materialtoolbarEditTrain.changeToolbarFont()
        binding.materialtoolbarEditTrain.setOnMenuItemClickListener(onMenuItemClicked)
//        val map = requireArguments().get("MAP")!! as HashMap<String,String>
        this.planName = mViewModel.getName()
        this.planDate = mViewModel.getDate()

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