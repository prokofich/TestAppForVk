package com.kvk.testappforvk.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kvk.testappforvk.databinding.FragmentThreeBinding
import com.kvk.testappforvk.view.customview.CustomWatchView

class FragmentThree : Fragment() {

    private var binding:FragmentThreeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val customWatchView = CustomWatchView(requireContext())
        val params = ViewGroup.LayoutParams(dpToPx(200), dpToPx(200))
        customWatchView.id = View.generateViewId()
        customWatchView.layoutParams = params
        binding?.idFragment3Container?.addView(customWatchView)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

}