package ai.chatprism.modlife.api

import ai.chatprism.modlife.model.AuthenticationResponse
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import retrofit2.http.*

interface ApiCallInterface {


    @POST(Urls.USER_LOGIN)
    suspend fun userLogin(
        @Body parameters: Map<String, String>
    ): AuthenticationResponse

}
