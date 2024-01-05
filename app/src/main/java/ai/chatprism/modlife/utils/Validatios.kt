package ai.chatprism.modlife.utils

import android.util.Patterns

fun isValidMobile(phone: String): Boolean {
    return Patterns.PHONE.matcher(phone).matches()
}

