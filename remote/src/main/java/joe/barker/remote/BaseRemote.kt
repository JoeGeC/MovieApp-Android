package joe.barker.remote

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

abstract class BaseRemote {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        internal const val API_KEY = "40e668e12304e6a05b34c2ea7d7196ce"

        internal val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    protected fun <T> tryRemote(remoteCall: () -> Call<T>) : Result<T?, ErrorResponse?> {
        return try{
            val result = remoteCall.invoke().execute()
            return if (result.isSuccessful) {
                Result.Success(result.body())
            } else {
                val errorResponse = JsonAdapter.convertToError(result)
                Result.Failure(errorResponse)
            }
        } catch(exception: Exception){
            val error = ErrorResponse(exception.localizedMessage)
            Result.Failure(error)
        }
    }
}