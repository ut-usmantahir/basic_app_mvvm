package ai.chatprism.modlife.api

import ai.chatprism.modlife.model.AuthenticationResponse
import androidx.lifecycle.LiveData
import io.reactivex.Observable

class Repository(private val apiCallInterface: ApiCallInterface) {

    suspend fun executeUserLogin(parameters: Map<String, String>): AuthenticationResponse {
        return apiCallInterface.userLogin(parameters)
    }


}
