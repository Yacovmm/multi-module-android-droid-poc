package features.feature_a.presentation.FirstScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        arguments?.get(URLS_KEY)?.let {
            viewModel.urlsList = it as List<UrlEntity>
        }

        setupObservers()

        bindViews()

        setupListUrls(items = viewModel.urlsList)
    }

    private fun setupListUrls(items: List<UrlEntity>) {
        val composeView = binding.composeList
        composeView.setContent {
            Surface(color = MaterialTheme.colors.background) {
                LazyColumnClickable(items = items) {
                    Toast.makeText(
                        context, "Url $it", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @Composable
    fun LazyColumnClickable(items: List<UrlEntity>, selectedUrl: (String) -> Unit) {
        LazyColumn() {
            items(items.size) {
                val currentItem = items[it]
                Surface(
                    modifier = Modifier
                        .clickable { selectedUrl(currentItem.name) }
                ) {
                    Text(
                        text = "Url ${currentItem.url}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
    }

    private fun bindViews() {
        binding.tvTitle.text = authRepository.userData ?: "Teste"

        binding.tvTitle.setOnClickListener {
            viewModel.getData()
        }

        binding.tvTitle.setOnClickListener {
            screenNavigation.navigateToFeatureB("Deu certoo")
        }
    }

    private fun setupObservers() {
        viewModel.testeLiveData.observe(viewLifecycleOwner) {
//            binding.tv1.text = it.name
//            binding.tv2.text = it.type
        }
    }
}
