package features.auth.auth_public.domain.entities

import features.auth.auth_public.data.models.GetUrlsResponse
import java.io.Serializable

data class UrlEntity(
    val id: Int,
    val name: String,
    val url: String
) : Serializable {
    companion object {

        fun mapper(response: GetUrlsResponse): List<UrlEntity> {
            return response.urls.map { urlResponse ->
                val url = if (urlResponse.path.contains("http")) urlResponse.path else
                    "https://${urlResponse.path}"
                UrlEntity(
                    id = urlResponse.id,
                    name = urlResponse.name,
                    url = url
                )
            }
        }
    }
}
