package com.android.espermobiles.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.espermobiles.db.model.FeaturesData
import com.android.espermobiles.repository.MobileRepository

class MainViewModel(
    private val repository: MobileRepository
): ViewModel() {

    val featureName1 = ObservableField<String>("")
    val featureName2 = ObservableField<String>("")
    val featureName3 = ObservableField<String>("")
    val showLoading = ObservableBoolean()

    val featuresLiveData = MutableLiveData<List<FeaturesData>>()
    val exclusionsLiveData = MutableLiveData<Pair<Boolean, List<String>>>()
    val submitClicked = MutableLiveData<Boolean>()

    val feature1Summary = ObservableField<FeaturesData>()
    val feature2Summary = ObservableField<FeaturesData>()
    val feature3Summary = ObservableField<List<FeaturesData>>()

//    init {
//        fetchFeaturesDataFromDB()
//    }

    fun fetchDataFromAPI() {
        showLoading.set(true)
        repository.fetchDataFromAPI()
                .subscribe({
                    showLoading.set(false)
                    Log.d("TAG", "Success")
                    fetchFeaturesDataFromDB()
                },
                        {
                            fetchFeaturesDataFromDB()
                        })
    }

    fun fetchFeaturesDataFromDB() {
        showLoading.set(true)
        repository.getFeaturesData()
            .subscribe ({
                showLoading.set(false)
                featuresLiveData.postValue(it)
            },
                {
                    Log.d("fetchFeaturesDataFromDB", it.toString())
                }
            )
    }

    fun getExclusions(featureID: String, optionsID: String, checked: Boolean) {
        val result = repository.getExclusions(featureID, optionsID)
        val list = ArrayList<String>()
        result.forEach {
            if(it.optionID1 == optionsID)
                list.add(it.optionID2)
            if(it.optionID2 == optionsID)
                list.add(it.optionID1)
        }
        exclusionsLiveData.postValue(Pair(checked, list))
    }

    fun onClickSubmit(view: View) {
        submitClicked.postValue(true)
    }
}