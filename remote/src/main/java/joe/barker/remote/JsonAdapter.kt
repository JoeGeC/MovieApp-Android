package joe.barker.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joe.barker.data.response.ErrorResponse
import retrofit2.Response

class JsonAdapter{
    companion object{
        private val gson = Gson()

        fun <T> convertToError(result: Response<T>) : ErrorResponse {
            val type = object : TypeToken<ErrorResponse>() {}.type
            return gson.fromJson(result.errorBody()!!.charStream(), type)
        }
    }
}