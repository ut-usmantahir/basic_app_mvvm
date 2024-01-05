package ai.chatprism.modlife.api

import ai.chatprism.modlife.utils.D
import com.google.gson.Gson
//import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
//import retrofit2.HttpException
//import retrofit2.HttpException
import retrofit2.adapter.rxjava2.Result.error
import retrofit2.HttpException
//import retrofit2.adapter.rxjava2.HttpException



object ErrorUtils {
    fun parseApiError(throwable: Throwable): ApiErrorResponse {
        return try {
            D.e("throwable: ", throwable.toString())
//            val json = JSONObject(apiError)
//            val error = ApiErrorResponse(
//                json.optString("status", ""),
//                json.optString("message", "")
//            )
            val errorResponse = Gson().fromJson(
                (throwable as HttpException).response().errorBody()?.charStream(),
//                (throwable as HttpException).response().errorBody()?.charStream(),
                ApiErrorResponse::class.java
            )

            errorResponse
        } catch (ex: Exception) {
            ex.printStackTrace()
            parseError(throwable)
        }
    }


    private fun parseError(t: Throwable): ApiErrorResponse {
        return try {
            ApiErrorResponse(
                "",
                500, // default code setting
                t.message!!
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            ApiErrorResponse(
                "",
                500, // default code setting
                t.message!!
            )
        }
    }

    private fun getProperErrorMessage(json: JSONObject): ApiErrorResponse {
        val jsonObject = json.getJSONObject("data")
        val result = json.optString("result", "")
        var message = ""
        val iterator: MutableIterator<String> = jsonObject.keys()
        if (iterator.hasNext()) {
            val key = iterator.next()
            try {
                val value = jsonObject[key] as JSONArray
                message = value.getString(0)
            } catch (e: JSONException) {
                // Something went wrong!
            }
        }
        return ApiErrorResponse(result, 422, message, )
    }
}