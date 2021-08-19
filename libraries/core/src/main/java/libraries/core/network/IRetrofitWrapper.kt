package libraries.core.network

import kotlinx.coroutines.withContext
import libraries.core.coroutines.ICoroutinesDispatchers
import libraries.shared_utils.network.models.DefaultResponse
import retrofit2.Response
import javax.inject.Inject

interface IRetrofitWrapper {
    suspend fun <T> request(call: suspend () -> Response<@JvmSuppressWildcards DefaultResponse<T>>): DefaultResponse<T?>
}

class RetrofitWrapperImpl @Inject constructor(
    private val dispatchers: ICoroutinesDispatchers
) : IRetrofitWrapper {
    override suspend fun <T> request(call: suspend () -> Response<DefaultResponse<T>>): DefaultResponse<T?> =
        withContext(dispatchers.io_thread) {
            try {
                val response = call()
                if (response.isSuccessful) {
                    response.body() as DefaultResponse<T?>
                } else {
                    response.errorBody() as DefaultResponse<T?>
                }
            } catch (e: Exception) {
                println(e)
                println("Erro")
                DefaultResponse(
                    statusCode = "500",
                    errors = arrayListOf(),
                    null
                )
            }
        }
}
