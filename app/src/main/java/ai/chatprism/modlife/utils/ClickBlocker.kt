package ai.chatprism.modlife.utils

import android.content.Context
import android.os.Handler

object ClickBlocker {
    private var mIsBlockClick = false
    private const val DEFAULT_BLOCK_TIME = 1000L


    /**
     * Block any event occurs in 1000 millisecond to prevent spam action
     * @return false if not in block state, otherwise return true.
     */
    fun block(context: Context, blockInMillis: Long = DEFAULT_BLOCK_TIME): Boolean {
        if (!mIsBlockClick) {
            mIsBlockClick = true

            Handler(context.mainLooper).postDelayed({ mIsBlockClick = false }, blockInMillis)
            return false
        }
        return true
    }

    fun block(context: Context): Boolean {
        return block(context, DEFAULT_BLOCK_TIME)
    }
}