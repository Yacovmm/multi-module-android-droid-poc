package features.auth.auth_public.data.models

data class GetUrlsResponse(
    val urls: List<UrlResponse>
) {
    data class UrlResponse(
        val id: Int,
        val name: String,
        val path: String
    )
}
