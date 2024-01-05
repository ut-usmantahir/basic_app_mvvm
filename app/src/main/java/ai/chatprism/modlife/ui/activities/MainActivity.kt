package ai.chatprism.modlife.ui.activities

import ai.chatprism.modlife.R
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import ai.chatprism.modlife.base.BaseActivity
import ai.chatprism.modlife.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val layoutResID: Int @LayoutRes get() = R.layout.activity_main
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResID)
    }

}