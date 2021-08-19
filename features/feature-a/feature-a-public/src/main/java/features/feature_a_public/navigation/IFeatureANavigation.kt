package features.feature_a_public.navigation

import features.auth.auth_public.domain.entities.UrlEntity

interface IFeatureANavigation {
    fun navigateToFeatureB(param: String)
    fun navigateToFirstScreen(urls: List<UrlEntity>)
}
