package ai.chatprism.modlife.ui.activities

import ai.chatprism.modlife.R
import ai.chatprism.modlife.base.BaseActivity
import ai.chatprism.modlife.databinding.ActivityForgotPassBinding
import ai.chatprism.modlife.viewmodel.LoginViewModel
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPassActivity : BaseActivity() {
    private val layoutResID: Int @LayoutRes get() = R.layout.activity_forgot_pass
    private lateinit var binding: ActivityForgotPassBinding
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
        val intent = Intent(this, SignUpActivity::class.java)
        binding.btnForgotPass.setOnClickListener {
            startNewActivity(intent, false)
        }
    }

}