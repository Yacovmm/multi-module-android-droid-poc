package libraries.core.network.mock

import libraries.core.R

object ApiMockManager {

    fun checkUrlAndGetJson(url: String): Int {
        return when {
            url.contains(ApiMock.FeatureA.TesteEndpoint.url) ->
                ApiMock.FeatureA.TesteEndpoint.jsonRawRes
            url.contains(ApiMock.AuthModule.getUrls.url) ->
                ApiMock.AuthModule.getUrls.jsonRawRes
            url.contains(ApiMock.AuthModule.login.url) ->
                ApiMock.AuthModule.login.jsonRawRes
            else -> ApiMock.ERROR_JSON_PATH
        }
    }

    sealed class ApiMock(val url: String, val jsonRawRes: Int) {

        object FeatureA {
            object TesteEndpoint : ApiMock(
                url = "endpoint/teste",
                jsonRawRes = R.raw.teste_response
            )
        }

        object AuthModule {
            object getUrls : ApiMock(
                url = "auth/getUrls",
                jsonRawRes = R.raw.get_urls_response
            )

            object login : ApiMock(
                url = "auth/login",
                jsonRawRes = R.raw.login_response
            )
        }

        companion object {
            val ERROR_JSON_PATH = R.raw.default_error
        }
    }
}
