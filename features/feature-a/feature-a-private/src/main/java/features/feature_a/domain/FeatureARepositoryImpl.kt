package features.feature_a.domain

import features.feature_a_public.data.models.TesteResponse
import features.feature_a_public.data.network.FeatureAService
import features.feature_a_public.data.repositort.IFeatureARepository
import libraries.core.network.IRetrofitWrapper
import javax.inject.Inject

class FeatureARepositoryImpl @Inject constructor(
    private val retrofitWrapperImpl: IRetrofitWrapper,
    private val featureAService: FeatureAService
) : IFeatureARepository {
    override suspend fun getTeste(): TesteResponse {
        val response = retrofitWrapperImpl.request {
            featureAService.getTeste()
        }

        val a = "as"

        return response.serializedResponse ?: TesteResponse("", "")
    }
}
