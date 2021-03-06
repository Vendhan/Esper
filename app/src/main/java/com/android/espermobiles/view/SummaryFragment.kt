package com.android.espermobiles.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.espermobiles.R
import com.android.espermobiles.databinding.FragmentSummaryBinding
import com.android.espermobiles.db.model.FeaturesData
import com.android.espermobiles.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SummaryFragment : Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataBinding = DataBindingUtil.inflate<FragmentSummaryBinding>(
            inflater,
            R.layout.fragment_summary,
            container,
            false
        )
        dataBinding.viewModel = mainViewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feature1 = mainViewModel.feature1Summary.get()
        val image = view.findViewById<AppCompatImageView>(R.id.mobile_image)
        Glide.with(this).load(feature1?.optionIcon).into(image)
        mainViewModel.mobileName.set(feature1?.optionName)
        mainViewModel.storageName.set(mainViewModel.feature2Summary.get()?.optionName)
        setCategoryChips(mainViewModel.feature3Summary.get()!!)
    }

    private fun setCategoryChips(categorys: List<FeaturesData>) {
        val chipGroup = view?.findViewById<ChipGroup>(R.id.chipGroupSummary)
        for (category in categorys) {
            val mChip =
                this.layoutInflater.inflate(R.layout.item_chip_category, null, false) as Chip
            mChip.text = category.optionName
            mChip.id = category.optionID.toInt()
            mChip.height = 100
            mChip.setOnCheckedChangeListener { buttonView, isChecked ->
            }
            Glide.with(this)
                .load(category.optionIcon)
                .into(object : CustomTarget<Drawable>(48, 48) {
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        mChip.chipIcon = errorDrawable
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        mChip.chipIcon = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        mChip.chipIcon = placeholder
                    }

                })
            chipGroup?.addView(mChip)
        }
    }
}
