package com.android.espermobiles.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.espermobiles.R
import com.android.espermobiles.core.MainApplication
import com.android.espermobiles.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_mobile_list,
            R.id.navigation_summary
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        if(MainApplication.isOnline(this))
            mainViewModel.fetchDataFromAPI()
        else {
            mainViewModel.fetchFeaturesDataFromDB()
            Toast.makeText(this, R.string.no_network_connectivity, Toast.LENGTH_LONG).show()
        }
    }
}