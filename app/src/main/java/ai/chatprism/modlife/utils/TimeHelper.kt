package ai.chatprism.modlife.utils

import ai.chatprism.modlife.utils.D
import android.content.Context
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeHelper {

    fun dateFormatter(data: String): String {

        try {
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            val outputFormat: DateFormat = SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss a", Locale.ENGLISH)
            val date: Date? = inputFormat.parse(data)

            val outputDateStr: String = outputFormat.format(date!!)
            D.d("TimeHelper","outputDateStr: $outputDateStr")
            return outputDateStr
        }
        catch (ex: Exception){
            ex.printStackTrace()
        }
        return data
    }

    fun dateFormatter2(data: String): String {
        try{
            D.d("TimeAgoHelper", "UTC data: $data")
//        2022-07-25T10:19:19.000000Z
            val localFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.ENGLISH)
            localFormat.timeZone = TimeZone.getTimeZone("UTC")
            val localDate: Date = localFormat.parse(data)!!
            localFormat.timeZone = TimeZone.getDefault()
            val localDateStr: String = localFormat.format(localDate)

            D.d("TimeAgoHelper", "localDateStr: $localDateStr")

            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            val outputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd-hh:mm a", Locale.ENGLISH)
            val date: Date = inputFormat.parse(localDateStr)

            val outputDateStr: String = outputFormat.format(date)
            D.d("TimeAgoHelper","outputDateStr: $outputDateStr")
            return outputDateStr

        }
        catch (ex: Exception){

        }
        return ""
    }


    fun getCurrentDateAndTime(): String {
        val sdf = SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss a", Locale.ENGLISH)
        return sdf.format(Date())
    }

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
        return sdf.format(Date())
    }

    fun getTimeDurationInMinutes(startTime: String, endTime: String): Long {
        val dateFormat = SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss a", Locale.ENGLISH)

        try {
            val startDate = dateFormat.parse(startTime)
            val endDate = dateFormat.parse(endTime)
            val diff = endDate?.time!! - startDate?.time!!
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24

            return minutes
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0L
    }

    fun getEstimatedTime(context: Context, distanceInKms: Float): String {
//        val meter: Double = source.distanceTo(dest)
        val kms_per_min = 0.3
        val mins_taken = distanceInKms / kms_per_min
        val totalMinutes = mins_taken.toInt()
        D.d("TimeHelper", "kms : $distanceInKms mins :$mins_taken")
        return if (totalMinutes < 60) {
            "$totalMinutes mins}"
        }
        else {
            var minutes = (totalMinutes % 60).toString()
            minutes = if (minutes.length == 1) "0$minutes"
            else minutes
            (totalMinutes / 60).toString() + " hr " + minutes + "min"
        }
    }

}