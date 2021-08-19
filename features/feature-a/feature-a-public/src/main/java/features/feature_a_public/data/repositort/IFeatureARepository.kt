package features.feature_a_public.data.repositort

import features.feature_a_public.data.models.TesteResponse

interface IFeatureARepository {

    suspend fun getTeste(): TesteResponse
}
