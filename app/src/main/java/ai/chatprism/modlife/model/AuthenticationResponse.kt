package ai.chatprism.modlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AuthenticationResponse(
    val result: String,
    val message: String,
    val data: AuthenticationData?,
    val token: String?,

)

@Parcelize
data class AuthenticationData(
    val user_id: Int?,
    var name: String?,
) : Parcelable