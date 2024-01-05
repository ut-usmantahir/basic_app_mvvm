package ai.chatprism.modlife.api

import androidx.annotation.NonNull
import androidx.annotation.Nullable


class ApiResponse<T> private constructor(
    val status: Status, @param:Nullable @field:Nullable
    val data: T?, @param:Nullable @field:Nullable
    val error: ApiErrorResponse?,
) {
    companion object {

        fun <T> loading(): ApiResponse<T> {
            return ApiResponse(Status.LOADING, null, null)
        }

        fun <T> loaded(): ApiResponse<T> {
            return ApiResponse(Status.LOADED, null, null)
        }

        fun <T> success(@NonNull data: T?): ApiResponse<T> {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(@NonNull error: ApiErrorResponse): ApiResponse<T> {
            return ApiResponse(Status.ERROR, null, error)
        }

    }
}