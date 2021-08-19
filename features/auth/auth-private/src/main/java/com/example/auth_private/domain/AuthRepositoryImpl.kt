package com.example.auth_private.domain

import features.auth.auth_public.data.models.LoginRequest
import features.auth.auth_public.data.network.AuthService
import features.auth.auth_public.data.repository.IAuthRepository
import features.auth.auth_public.domain.entities.UrlEntity
import libraries.core.network.IRetrofitWrapper
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val retrofitWrapper: IRetrofitWrapper,
    private val authService: AuthService
) : IAuthRepository {

    private var user: String? = null
    override val userData: String?
        get() = user

    override suspend fun getUrls(email: String): List<UrlEntity> {
        val response = retrofitWrapper.request {
            authService.getUrls(email = email)
        }

        // TODO: Verify if there is errors ...
        response.serializedResponse?.let {
            return UrlEntity.mapper(response = it)
        } ?: run {
            // TODO: Treat errors
            return emptyList()
        }
    }

    override suspend fun login(request: LoginRequest): Boolean {
        val response = retrofitWrapper.request {
            authService.login(request)
        }

        response.serializedResponse?.let {
            user = it.name
            return true
        } ?: return false
    }
}
