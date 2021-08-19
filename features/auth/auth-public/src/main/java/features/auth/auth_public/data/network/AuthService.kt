package features.auth.auth_public.data.network

import features.auth.auth_public.data.models.GetUrlsResponse
import features.auth.auth_public.data.models.LoginRequest
import features.auth.auth_public.data.models.LoginResponse
import libraries.shared_utils.network.models.DefaultResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @GET("auth/getUrls")
    suspend fun getUrls(
        @Query("email") email: String
    ): Response<DefaultResponse<GetUrlsResponse>>

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<DefaultResponse<LoginResponse>>
}
