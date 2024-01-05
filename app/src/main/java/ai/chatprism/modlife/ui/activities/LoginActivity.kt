package ai.chatprism.modlife.ui.activities

import ai.chatprism.modlife.R
import ai.chatprism.modlife.api.ApiResponse
import ai.chatprism.modlife.api.Status
import ai.chatprism.modlife.viewmodel.LoginViewModel
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import ai.chatprism.modlife.base.BaseActivity
import ai.chatprism.modlife.databinding.ActivityLoginBinding
import ai.chatprism.modlife.model.AuthenticationResponse
import ai.chatprism.modlife.utils.Constant
import ai.chatprism.modlife.utils.Globals
import ai.chatprism.modlife.utils.showAlertMessage
import ai.chatprism.modlife.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    private val layoutResID: Int @LayoutRes get() = R.layout.activity_login
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        initViews()
//        hitLoginApi()
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResID)
    }

    private fun hitLoginApi() {
        val params = HashMap<String, String>()
        params["mobile_no"] = "phoneNumber"
        params["fcm_token"] = sharedPrefsHelper.getToken()
        params["password"] =  "binding.etPassword.text.toString()"

        viewModel.verifyLoginUser(params).observe(this) { response ->
            consumeDriverLoginResponse(response)
        }
    }

    private fun initViews() {
        val intent = Intent(this, ForgotPassActivity::class.java)
        binding.btnLogin.setOnClickListener {
            startNewActivity(intent, false)
        }
    }

    private fun consumeDriverLoginResponse(apiResponse: ApiResponse<AuthenticationResponse>?) {
        when (apiResponse?.status) {
            Status.LOADING -> Globals.showProgressDialog(this)

            Status.SUCCESS -> {
                Globals.hideProgressDialog()
                renderDriverLoginResponse(apiResponse.data as AuthenticationResponse)
            }
            Status.ERROR -> {
                Globals.hideProgressDialog()
                this.showAlertMessage(apiResponse.error?.message.toString())
            }
            else -> {
            }
        }
    }

    private fun renderDriverLoginResponse(response: AuthenticationResponse?) {
        if (response?.result == Constant.RESPONSE_RESULT_SUCCESS) {
            this.showToast(response.message)

            sharedPrefsHelper.setIsLoggedIn(true)
            sharedPrefsHelper.setToken(response.token.toString())
            sharedPrefsHelper.setUserId(response.data?.user_id!!)
            sharedPrefsHelper.setUserData(response.data)
        }
        else {
            this.showAlertMessage(response?.message!!)
        }
    }

}