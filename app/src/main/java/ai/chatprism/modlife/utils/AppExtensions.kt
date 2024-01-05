package ai.chatprism.modlife.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import java.math.RoundingMode

fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply { setGravity(Gravity.CENTER, 0, 0) }.show()
}

fun View.showSnackBarMsg(msg: String) {
    val snackBar = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
    snackBar.show()
}

fun Activity.hideSoftKeyboard(v: View) {
    val imm = this.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}

fun Activity.showSoftKeyboard(v: View) {
    val imm = this.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
//        imm.showSoftInput(v, 0)
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.showAlertMessage(message: String) {
    val dialogBuilder = AlertDialog.Builder(this)
    dialogBuilder.setMessage(message)
    dialogBuilder.setCancelable(false)
    dialogBuilder.setPositiveButton("ok") { _, _ -> }
    if (!this.isFinishing) {
        dialogBuilder.create().show()
    }
}

fun Activity.isNetworkAvailable() : Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return !(cm!!.activeNetworkInfo == null || !cm.activeNetworkInfo?.isConnected!!)
}

fun ImageView.setImage(context: Context, url: String) {
    Glide.with(context)
//        .load(Globals.getImageHttpsUrl(url))
        .load(url)
//        .placeholder(R.drawable.)
//        .error(R.drawable.)
        .into(this)
}

fun ImageView.setImageRounded(context: Context, url: String) {
    Glide.with(context)
        .load(Globals.getImageHttpsUrl(url))
//        .placeholder(R.drawable.)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun ImageView.setImageRoundedWithUri(context: Context, uri: Uri) {
    Glide.with(context)
        .load(uri)
//        .placeholder(R.drawable.ic_profile)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}


infix fun TextView.round(value: String) {
    this.text = value.toBigDecimal().setScale(1, RoundingMode.UP).toString()
}

fun String.round(): String {
    return this.toBigDecimal().setScale(1, RoundingMode.UP).toString()
}
fun String.roundTwo(): String {
    return this.toBigDecimal().setScale(2, RoundingMode.UP).toString()
}

inline fun Double.round(): String {
    return this.toBigDecimal().setScale(1, RoundingMode.UP).toString()
}
inline fun Float.round(): String {
    return this.toBigDecimal().setScale(1, RoundingMode.UP).toString()
}


