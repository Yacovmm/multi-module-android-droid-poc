package features.feature_a.presentation.FirstScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.shared_ui.baseComponents.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import features.auth.auth_public.data.repository.IAuthRepository
import features.auth.auth_public.domain.entities.UrlEntity
import features.feature_a.databinding.FragmentFeatureAFirstBinding
import features.feature_a_public.navigation.IFeatureANavigation
import javax.inject.Inject

@AndroidEntryPoint
class FeatureAFirstFragment : BaseFragment<FragmentFeatureAFirstBinding>() {

    @Inject
    lateinit var screenNavigation: IFeatureANavigation

    @Inject
    lateinit var authRepository: IAuthRepository

    private val viewModel: FirstScreenViewModel by viewModels()

    companion object {
        val URLS_KEY = "URLS_KEY"

        fun newInstance(list: ArrayList<UrlEntity>): FeatureAFirstFragment {
            val fragment = FeatureAFirstFragment()
            val bundle = Bundle().apply {
                putSerializable(URLS_KEY, list)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFeatureAFirstBinding
        get() = FragmentFeatureAFirstBinding::inflate

    override fun setupFragment() {

        println(arguments?.get(URLS_KEY))

        setupObservers()

        binding.tvTitle.text = authRepository.userData ?: "Teste"

        binding.tvTitle.setOnClickListener {
            viewModel.getData()
        }

        binding.tv2.setOnClickListener {
            screenNavigation.navigateToFeatureB("Deu certoo")
        }
    }

    private fun setupObservers() {
        viewModel.testeLiveData.observe(viewLifecycleOwner) {
            binding.tv1.text = it.name
            binding.tv2.text = it.type
        }
    }
}
