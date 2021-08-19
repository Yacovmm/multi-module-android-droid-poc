package features.feature_a_public.data.network

import features.feature_a_public.data.models.TesteResponse
import libraries.shared_utils.network.models.DefaultResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeatureAService {

    @GET("endpoint/teste")
    suspend fun getTeste(): Response<DefaultResponse<TesteResponse>>
}
