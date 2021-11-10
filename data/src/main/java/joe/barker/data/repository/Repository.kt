package joe.barker.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class Repository {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        internal const val API_KEY = "40e668e12304e6a05b34c2ea7d7196ce"

        internal val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}