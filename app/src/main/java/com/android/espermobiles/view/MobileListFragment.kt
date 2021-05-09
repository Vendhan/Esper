package com.android.espermobiles.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.espermobiles.R
import com.android.espermobiles.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MobileListFragment : Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_mobile_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = requireActivity().findViewById<AppCompatButton>(R.id.button)
        button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_mobile_list_to_navigation_selection);
        }
    }
}