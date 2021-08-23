package features.feature_a.presentation.FirstScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import com.example.shared_ui.baseComponents.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import features.auth.auth_public.data.repository.IAuthRepository
import features.auth.auth_public.domain.entities.UrlEntity
import features.feature_a.R
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

        setupSpinner()
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            viewModel.filterList
        )
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = filterSpinnerListener
    }

    private val filterSpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            viewModel.orderBySpinner(pos)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    @Composable
    fun DropDownList(
        requestToOpen: Boolean = false,
        list: List<String>,
        request: (Boolean) -> Unit,
        selectedString: (String) -> Unit
    ) {
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = requestToOpen,
            onDismissRequest = { request(false) },
        ) {
            list.forEach {
                DropdownMenuItem(
                    modifier = Modifier.wrapContentWidth(
                        align = Alignment.CenterHorizontally
                    ).wrapContentHeight(),
                    onClick = {
                        request(false)
                        selectedString(it)
                    }
                ) {
                    Text(
                        it,
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
            }
        }
    }

    @Composable
    fun FilterSelection() {
        val filterList = listOf(
            "",
            "A-Z",
            "Z-A",
            "PIX",
        )
        val text = remember { mutableStateOf("") } // initial value
        val isOpen = remember { mutableStateOf(false) } // initial value
        val openCloseOfDropDownList: (Boolean) -> Unit = {
            isOpen.value = it
        }
        val userSelectedString: (String) -> Unit = {
            text.value = it
        }
        Box {
            Column {
                TextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    label = { Text(text = "Filter") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    enabled = false
                )
                DropDownList(
                    requestToOpen = isOpen.value,
                    list = filterList,
                    openCloseOfDropDownList,
                    userSelectedString
                )
            }
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .background(Color.Transparent)
                    .padding(10.dp)
                    .clickable(
                        onClick = { isOpen.value = true }
                    )
            )
        }
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
        viewModel.filteredLiveData.observe(viewLifecycleOwner) {
            setupListUrls(items = it)
        }
    }
}
