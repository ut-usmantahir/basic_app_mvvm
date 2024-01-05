package com.cyberasol.deera.utils

import android.view.View
import okhttp3.MultipartBody


const val gone = View.GONE
const val visible = View.VISIBLE
const val invisible = View.INVISIBLE


fun getFileExtension(path: String): String {
    return path.substring(path.lastIndexOf(".") + 1)
}

fun getCurrentTime(): String {
    //        val calender = Calendar.getInstance()
    //        calender.set(Calendar.HOUR_OF_DAY, calender.get(Calendar.HOUR_OF_DAY) - 5)
    //        val targetFormat = "yyyy-MM-dd'T'HH:mm:ss"
    //
    //        return "${SimpleDateFormat(targetFormat).format(calender.timeInMillis)}.000Z"

    return ""
}

fun getEmptyMultipartList(): ArrayList<MultipartBody.Part> {
    val multiPartList: ArrayList<MultipartBody.Part> = ArrayList()
    return multiPartList
}

