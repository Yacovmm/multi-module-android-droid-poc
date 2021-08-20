package features.feature_a.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.shared_ui.baseComponents.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import features.feature_a.databinding.FragmentFeatureAFirstBinding

@AndroidEntryPoint
class FeatureASecondFragment : BaseFragment<FragmentFeatureAFirstBinding>() {

    companion object {
        private const val ARG_PARAM2 = "param2"

        fun newInstance(param2: String) =
            FeatureASecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFeatureAFirstBinding
        get() = FragmentFeatureAFirstBinding::inflate

    override fun setupFragment() {
        binding.tvTitle.text = arguments?.getString(ARG_PARAM2) ?: "Tela 2"

//        binding.tv1.setOnClickListener {
//
//        }
    }

}
