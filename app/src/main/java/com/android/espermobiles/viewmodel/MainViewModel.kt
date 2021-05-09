package com.android.espermobiles.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.espermobiles.repository.MobileRepository

class MainViewModel(
    private val repository: MobileRepository
): ViewModel() {

    fun fetchDataFromAPI() {
        repository.fetchDataFromAPI()
                .subscribe({
                    Log.d("TAG", "Success")
                },
                        {

                        })
    }
}