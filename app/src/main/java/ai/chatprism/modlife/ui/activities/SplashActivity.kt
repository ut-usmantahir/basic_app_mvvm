package ai.chatprism.modlife.ui.activities

import ai.chatprism.modlife.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import ai.chatprism.modlife.base.BaseActivity
import ai.chatprism.modlife.databinding.ActivitySplashBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    private val layoutResID: Int @LayoutRes get() = R.layout.activity_splash
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResID)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        lifecycleScope.launch {
            delay(1000)
            switchActivity()
        }

//        Handler(Looper.getMainLooper()).postDelayed({ switchActivity() },1000)
    }

    private fun switchActivity() {
//        getFCMToken()
        if (sharedPrefsHelper.isLoggedIn()) {
            sharedPrefsHelper.getUserData()?.let {
//            startNewActivity(Intent(this, DashboardActivity::class.java), true)
            }
        }
        else {
            startNewActivity(Intent(this, LoginActivity::class.java), true)
        }
    }

//    private var fcmToken = ""
//    private fun getFCMToken() {
//        FirebaseMessaging.getInstance().isAutoInitEnabled = true
//
//        FirebaseMessaging.getInstance().token.addOnSuccessListener { result ->
//                if (result != null) {
//                    fcmToken = result
//                    D.d("SplashActivity", "FCM Token: $fcmToken")
//                    sharedPrefsHelper.setFCMToken(fcmToken)
//                }
//        }.addOnFailureListener {
//            D.d("SplashActivity", "FCM Token not generated: $it")
//        }
//    }
}
