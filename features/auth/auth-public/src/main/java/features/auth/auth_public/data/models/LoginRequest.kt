package features.auth.auth_public.data.models

data class LoginRequest(
    val email: String,
    val password: String,
)
