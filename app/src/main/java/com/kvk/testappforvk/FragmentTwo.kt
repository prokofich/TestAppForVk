package com.kvk.testappforvk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kvk.testappforvk.databinding.FragmentOneBinding
import com.kvk.testappforvk.databinding.FragmentTwoBinding

class FragmentTwo : Fragment() {

    private var binding:FragmentTwoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}