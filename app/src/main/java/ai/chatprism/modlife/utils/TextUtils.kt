package ai.chatprism.modlife.utils

import ai.chatprism.modlife.R
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

object TextUtils {
    fun getTermsAndConditionsSpannable(
        tv: AppCompatTextView,
        context: Context
    ) {
        val agreeTo = "Agreee"
        val termsAndConditions = "Terms"
        val termsAndConditionsFull = "$agreeTo $termsAndConditions"
        val spannable = SpannableString(termsAndConditionsFull)

        //setting click listener for terms and conditions
        spannable.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
//                    (context as BaseActivity).startNewActivity(
//                        Intent(context, HelpActivity::class.java)
//                            .apply {
//                                putExtra(Constant.KEY_HELP_PAGE_TYPE, Constant.HELP_TERMS_AND_CONDITIONS)
//                                   },
//                        false
//                    )
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false // set to false to remove underline
                }
            },
            agreeTo.length,
            termsAndConditionsFull.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //setting color of post type
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
            agreeTo.length,
            termsAndConditionsFull.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

//        //setting style(Bold Text) of name
//        spannable.setSpan(
//            StyleSpan(Typeface.BOLD),
//            agreeTo.length,
//            termsAndConditions.length,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )

//        //setting color of name text
//        spannable.setSpan(
//            ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorBlack)),
//            agreeTo.length,
//            termsAndConditions.length,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )

//        //setting text size of name
//        val nameSize = context.resources.getDimensionPixelSize(R.dimen.textSize12)
//        spannable.setSpan(
//            AbsoluteSizeSpan(nameSize),
//            0,
//            agreeTo.length + termsAndConditions.length,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )

        tv.text = spannable
        tv.movementMethod = LinkMovementMethod.getInstance()
    }


}