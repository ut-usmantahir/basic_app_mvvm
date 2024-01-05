package ai.chatprism.modlife.api

data class ApiErrorResponse(
    val result: String,
    val code: Int = 0,
    val message: String
)