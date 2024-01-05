package ai.chatprism.modlife.base

import ai.chatprism.modlife.R
import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import ai.chatprism.modlife.utils.Constant
import ai.chatprism.modlife.utils.SharedPrefsHelper
import org.koin.android.ext.android.inject


open class BaseActivity : AppCompatActivity() {
    var cameraResultLauncher: ActivityResultLauncher<Intent>? = null
    var galleryResultLauncher: ActivityResultLauncher<Intent>? = null

    val sharedPrefsHelper: SharedPrefsHelper by inject()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    open fun startNewActivity(intent: Intent?, isFinish: Boolean) {
        val activityOptions = ActivityOptions.makeCustomAnimation(
            this, R.anim.fade_in, R.anim.fade_out
        )
        startActivity(intent, activityOptions.toBundle())
        if (isFinish) {
            finish()
        }
    }

    open fun requestTotFull() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    open fun dialogFaceCenter() {
        //Grab the window of the dialog, and change the width
        val lp = WindowManager.LayoutParams()
        val window = window
        getWindow().setGravity(Gravity.CENTER)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        lp.copyFrom(window.attributes)
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        setFinishOnTouchOutside(false)
    }

    fun takeFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraResultLauncher?.launch(cameraIntent)

        //        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(cameraIntent, Constant.CAMERA_REQUEST)
    }

    open fun checkPermissionsForFile(): Boolean {
        return if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), Constant.MY_CAMERA_PERMISSION_CODE
            )
            false
        } else {
            true
        }
    }

    open fun pickImageFromGallery() {
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "image/*"
        contentSelectionIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
        galleryResultLauncher?.launch(chooserIntent)
    }


}
