package features.auth.auth_public.data.models

data class LoginResponse(
    val id: Int,
    val name: String,
    val isValid: Boolean
)
