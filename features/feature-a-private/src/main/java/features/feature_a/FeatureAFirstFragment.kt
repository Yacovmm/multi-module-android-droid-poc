package features.feature_a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shared_ui.baseComponents.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import features.feature_a.databinding.FragmentFeatureAFirstBinding
import features.feature_a_public.navigation.IFeatureANavigation
import javax.inject.Inject

@AndroidEntryPoint
class FeatureAFirstFragment : BaseFragment<FragmentFeatureAFirstBinding>() {

    @Inject
    lateinit var screenNavigation: IFeatureANavigation

    companion object {
        fun newInstance() =
            FeatureAFirstFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFeatureAFirstBinding
        get() = FragmentFeatureAFirstBinding::inflate

    override fun setupFragment() {
        binding.tv.setOnClickListener {
            screenNavigation.navigateToFeatureB("Deu certoo")
        }
    }
}