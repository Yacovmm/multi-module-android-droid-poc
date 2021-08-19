package libraries.shared_utils.network.models

data class DefaultResponse<T>(
    val statusCode: String,
    val errors: ArrayList<String>,
    val serializedResponse: T? = null
)
