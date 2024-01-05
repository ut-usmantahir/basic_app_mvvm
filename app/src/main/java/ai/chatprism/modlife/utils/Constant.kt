package ai.chatprism.modlife.utils

object Constant {

    const val PREFS_NAME = "saavy-ai_prefs"
    const val DB_TAG = "room_db"
    const val NAME_MAX_LENGTH = 15
    const val PRIVACY_URL = "https://pages.flycricket.io"

    //API request header
    const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"

    //Notification Channel
    const val CHANNEL_ID = "channel_01"

    //API response codes
    const val RESPONSE_CODE_SUCCESS = 200
    const val RESPONSE_CODE_SUCCESS_WITH_EMPTY_ARRAY = 400
    const val RESPONSE_CODE_INVALID_USER_TOKEN = 401
    const val RESPONSE_RESULT_SUCCESS = "success"
    const val RESPONSE_RESULT_ERROR = "error"

    const val FORMAT_JSON = "json"

    const val PASSWORD_MIN_LENGTH = 8

    //request codes
    const val PICK_IMAGE = 1001
    const val CAMERA_REQUEST = 1002
    const val MY_CAMERA_PERMISSION_CODE = 1003

    //Intent Keys
    const val KEY_USER_MODEL = "user_model"
    const val KEY_DOCUMENT_TYPE = "document_type"
}