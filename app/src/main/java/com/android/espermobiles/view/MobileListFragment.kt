package com.android.espermobiles.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.android.espermobiles.R
import com.android.espermobiles.databinding.FragmentMobileListBinding
import com.android.espermobiles.db.model.FeaturesData
import com.android.espermobiles.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.koin.android.viewmodel.ext.android.sharedViewModel


class MobileListFragment : Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()
    private var list : List<FeaturesData> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val dataBinding = DataBindingUtil.inflate<FragmentMobileListBinding>(
            inflater,
            R.layout.fragment_mobile_list,
            container,
            false
        )
        dataBinding.viewModel = mainViewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.featuresLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", it.size.toString())
            if (it.isNotEmpty() && it != null) {
                Log.d("TAG", it.toString())
                list = it
                it.forEach {
                    if(it.featureID.toInt() == 1)
                        mainViewModel.featureName1.set(it.featureName)
                    if(it.featureID.toInt() == 2)
                        mainViewModel.featureName2.set(it.featureName)
                    if(it.featureID.toInt() == 3)
                        mainViewModel.featureName3.set(it.featureName)
                }
                val f1 = it.filter {
                    it.featureID == "1"
                }
                setCategoryChips(f1)
                val f2 = it.filter {
                    it.featureID == "2"
                }
                setCategoryChips(f2)
                val f3 = it.filter {
                    it.featureID == "3"
                }
                setCategoryChips(f3)
            }
        })

        mainViewModel.exclusionsLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("Exclusion TAG", it.second.size.toString())
            if (it.second.isNotEmpty()) {
                val l = ArrayList<FeaturesData>()
                it.second.forEach { optionId ->
                    list.forEach {
                        if(it.optionID == optionId)
                            l.add(it)
                    }
                }
                setCategoryChips(l, false, it.first)
            }
        })

        mainViewModel.submitClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                val featureList1 = generateSummary(view.findViewById(R.id.chipGroupMobiles))
                if(featureList1.isNotEmpty())
                    mainViewModel.feature1Summary.set(generateSummary(view.findViewById(R.id.chipGroupMobiles))[0])

                val featureList2 = generateSummary(view.findViewById(R.id.chipGroupStorage))
                if(featureList2.isNotEmpty())
                    mainViewModel.feature2Summary.set(generateSummary(view.findViewById(R.id.chipGroupStorage))[0])

                val featureList3 = generateSummary(view.findViewById(R.id.chipGroupFeatures))
                if(featureList3.isNotEmpty())
                    mainViewModel.feature3Summary.set(generateSummary(view.findViewById(R.id.chipGroupFeatures)))
                if(featureList1.isEmpty() || featureList2.isEmpty() || featureList3.isEmpty())
                    Toast.makeText(view.context, getString(R.string.warning_to_make_selection), Toast.LENGTH_LONG).show()
                else
                    Navigation.findNavController(view).navigate(R.id.action_navigation_mobile_list_to_navigation_summary)
            }
        })
    }

    private fun generateSummary(chipGroup: ChipGroup): List<FeaturesData> {
        val ids = chipGroup.checkedChipIds
        val featureDataSummaryList = ArrayList<FeaturesData>()
        list.forEach { featureData ->
            ids.forEach {
                if (featureData.optionID.toInt() == it)
                    featureDataSummaryList.add(featureData)
            }
        }
        return featureDataSummaryList
    }

    private fun setCategoryChips(categorys: List<FeaturesData>, enabled: Boolean = true, checked: Boolean = false) {
        var chipGroup = view?.findViewById<ChipGroup>(R.id.chipGroupMobiles)
        when {
            categorys[0].featureID.toInt() == 1 -> chipGroup = view?.findViewById(R.id.chipGroupMobiles)
            categorys[0].featureID.toInt() == 2 -> chipGroup = view?.findViewById(R.id.chipGroupStorage)
            categorys[0].featureID.toInt() == 3 -> chipGroup = view?.findViewById(R.id.chipGroupFeatures)
        }

        for (category in categorys) {
            val mChip =
                this.layoutInflater.inflate(R.layout.item_chip_category, null, false) as Chip
            mChip.text = category.optionName
            mChip.id = category.optionID.toInt()
            mChip.height = 100
            mChip.setOnCheckedChangeListener { buttonView, isChecked ->
                getExclusions(category.featureID, buttonView, isChecked)
            }
            if(enabled) {
                Glide.with(this)
                    .load(category.optionIcon)
                    .into(object : CustomTarget<Drawable>(48, 48) {
                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            mChip.chipIcon = errorDrawable
                        }
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            mChip.chipIcon = resource
                        }
                        override fun onLoadCleared(placeholder: Drawable?) {
                            mChip.chipIcon = placeholder
                        }

                    })
                chipGroup?.addView(mChip)
            }
            else {
                chipGroup?.setChildrenEnabled(category.optionID.toInt(), false, checked)
            }

        }
    }

    private fun ChipGroup.setChildrenEnabled(id: Int, enable: Boolean, checked: Boolean) {
        children.forEach {
            if(it.id == id) {
                if(it.isSelected)
                    it.isSelected = false
                it.isEnabled = !checked
            }
        }
    }

    private fun getExclusions(featureID: String, buttonView: CompoundButton?, checked: Boolean) {
        if(checked)
            mainViewModel.getExclusions(
                featureID = featureID,
                optionsID = buttonView?.id.toString(),
                checked = true
            )
        else
            mainViewModel.getExclusions(
                featureID = featureID,
                optionsID = buttonView?.id.toString(),
                checked = false
            )
    }
}