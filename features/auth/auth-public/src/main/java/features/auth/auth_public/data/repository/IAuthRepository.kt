package features.auth.auth_public.data.repository

import features.auth.auth_public.data.models.LoginRequest
import features.auth.auth_public.domain.entities.UrlEntity

interface IAuthRepository {

    val userData: String?

    suspend fun getUrls(email: String): List<UrlEntity>

    suspend fun login(request: LoginRequest): Boolean
}
