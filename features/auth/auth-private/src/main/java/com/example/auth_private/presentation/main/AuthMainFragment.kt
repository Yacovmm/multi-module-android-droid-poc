package com.example.auth_private.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.auth_private.databinding.AuthMainFragmentBinding
import com.example.shared_ui.baseComponents.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import features.feature_a_public.navigation.IFeatureANavigation
import javax.inject.Inject

@AndroidEntryPoint
class AuthMainFragment : BaseFragment<AuthMainFragmentBinding>() {

    companion object {
        fun newInstance() = AuthMainFragment()
    }

    private val viewModel: AuthMainViewModel by viewModels()

    @Inject
    lateinit var featureAScreenNavigation: IFeatureANavigation

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AuthMainFragmentBinding
        get() = AuthMainFragmentBinding::inflate

    override fun setupFragment() {

        binding.etPassword.isEnabled = false
        binding.btnLogin.isEnabled = false

        setupEditTextListeners()

        setupActions()

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.authLiveData.observe(viewLifecycleOwner) { authViewState ->
            handleLoading(authViewState.loading)

            when {
                authViewState.loginStatus -> {
                    featureAScreenNavigation.navigateToFirstScreen(viewModel.listUrls)
                }
            }
        }
    }

    private fun handleLoading(loading: Boolean) {
        if (loading)
            binding.progress.visibility = View.VISIBLE
        else
            binding.progress.visibility = View.GONE
    }

    private fun setupActions() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
    }

    private fun setupEditTextListeners() {
        binding.etEmail.doOnTextChanged { text, start, before, count ->
            if (viewModel.isValidEmail(text)) {
                binding.etPassword.isEnabled = true
                binding.btnLogin.isEnabled = true
            }
        }

        binding.etPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                viewModel.checkUrls(binding.etEmail.text.toString())
            }
        }
    }
}
