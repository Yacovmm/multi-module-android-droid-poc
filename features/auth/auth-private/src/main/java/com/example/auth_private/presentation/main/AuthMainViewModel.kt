package com.example.auth_private.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import features.auth.auth_public.data.models.LoginRequest
import features.auth.auth_public.data.repository.IAuthRepository
import features.auth.auth_public.domain.entities.UrlEntity
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthMainViewModel @Inject constructor(
    private val authRepository: IAuthRepository
) : ViewModel() {

    fun isValidEmail(text: CharSequence?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(text)
        return matcher.matches()
    }

    private val _listUrls: MutableList<UrlEntity> = mutableListOf()
    val listUrls: List<UrlEntity> get() = _listUrls
    fun checkUrls(email: String) = viewModelScope.launch {
        _authLiveData.postValue(
            AuthViewState(loading = true)
        )
        val response = authRepository.getUrls(email)

        _listUrls.addAll(response)
        _authLiveData.postValue(
            AuthViewState(loading = false)
        )
    }

    private val _authLiveData = MutableLiveData<AuthViewState>()
    val authLiveData: LiveData<AuthViewState>
        get() = _authLiveData

    fun login(email: String, password: String) = viewModelScope.launch {
        _authLiveData.postValue(
            AuthViewState(loading = true)
        )

        val response = authRepository.login(
            LoginRequest(
                email = email,
                password = password
            )
        )

        _authLiveData.postValue(
            AuthViewState(loginStatus = response)
        )
    }
}

data class AuthViewState(
    val loading: Boolean = false,
    val loginStatus: Boolean = false
)
