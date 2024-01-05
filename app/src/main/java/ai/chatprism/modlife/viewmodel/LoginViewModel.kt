package ai.chatprism.modlife.viewmodel

import ai.chatprism.modlife.api.ApiResponse
import ai.chatprism.modlife.api.ErrorUtils
import ai.chatprism.modlife.api.Repository
import ai.chatprism.modlife.model.AuthenticationResponse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: Repository) : ViewModel() {

    fun verifyLoginUser(params: HashMap<String, String>): LiveData<ApiResponse<AuthenticationResponse>> {
        return liveData(Dispatchers.IO){
            emit(ApiResponse.loading())
            try {
                emit(ApiResponse.success(data = repository.executeUserLogin(params)))
            }
            catch (e: Exception){
                emit(ApiResponse.error(error = ErrorUtils.parseApiError(e)))
            }
        }
    }
}