package ai.chatprism.modlife.utils

import ai.chatprism.modlife.R
import ai.chatprism.modlife.api.Urls
import ai.chatprism.modlife.base.BaseActivity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.TextUtils
import android.util.Base64
import android.util.Patterns
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Globals {

    fun generateHashKey(context: Context) {
        try {
            val info = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                D.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }

    }

    fun isValidEmail(target: CharSequence): Boolean {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches())
    }

    fun isValidPassword(target: CharSequence): Boolean {
        return target.length >= Constant.PASSWORD_MIN_LENGTH
    }

    private var progressDialog: Dialog? = null
    fun showProgressDialog(context: Context){
         progressDialog = Dialog(context as BaseActivity)
        progressDialog?.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
//        progressDialog?.setContentView(R.layout.loading_dialog)
        progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog?.setCanceledOnTouchOutside(false)
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

//    private var progressDialog: AlertDialog? = null
//    fun showProgressDialog(context: Context) {
//        progressDialog = AlertDialog.Builder(context)
//            .setView(R.layout.loading_dialog)
//            .setCancelable(false)
//            .show()
//    }

    fun hideProgressDialog() {
        progressDialog?.let {
            if(it.isShowing)
                it.dismiss()
        }
    }

    fun getFileBody(path: String, fileName: String): MultipartBody.Part {
        val file = File(path)
        D.d("Globals", "fileName= $fileName file.name= ${file.name}")
        val requestFileProfile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(fileName, file.name, requestFileProfile)
    }

    fun getMediaFileBody(path: String, fileName: String): MultipartBody.Part {
        val file = File(path)
//        val requestFileProfile = RequestBody.create(MediaType.parse("image/*"), file)
//        val requestFileProfile = RequestBody.create("image/*".toMediaTypeOrNull(), file)

        val requestFileProfile = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(fileName, file.name, requestFileProfile)
    }


    fun toRequestBody (value: String): RequestBody {
//        return RequestBody.create(value,MediaType.parse("text/plain"));
//        return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    fun getNotificationIcon(): Int {
        val useWhiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
//        return if (useWhiteIcon) R.drawable.ic_launcher else R.mipmap.ic_launcher
        return if (useWhiteIcon) R.mipmap.ic_launcher_round else R.mipmap.ic_launcher_round
    }

    fun getImageHttpsUrl(url: String): String {
        return Urls.MEDIA_URL + url
    }
}
