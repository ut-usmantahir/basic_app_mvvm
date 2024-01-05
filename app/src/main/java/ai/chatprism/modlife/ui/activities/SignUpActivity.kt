package ai.chatprism.modlife.ui.activities

import ai.chatprism.modlife.R
import ai.chatprism.modlife.base.BaseActivity
import ai.chatprism.modlife.databinding.ActivitySignUpBinding
import ai.chatprism.modlife.viewmodel.LoginViewModel
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity() {
    private val layoutResID: Int @LayoutRes get() = R.layout.activity_sign_up
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        initViews()
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResID)
    }

    private fun initViews() {
        val intent = Intent(this, MainActivity::class.java)
        binding.btSignUp.setOnClickListener {
            startNewActivity(intent, false)
        }
    }

}