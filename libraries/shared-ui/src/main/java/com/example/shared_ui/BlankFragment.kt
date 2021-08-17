package com.example.shared_ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shared_ui.baseComponents.BaseFragment
import com.example.shared_ui.databinding.FragmentBlankBinding


class BlankFragment : BaseFragment<FragmentBlankBinding>() {



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBlankBinding
        get() = FragmentBlankBinding::inflate

    override fun setupFragment() {

    }
}