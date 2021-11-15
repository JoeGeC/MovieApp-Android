package joe.barker.movieapp

import android.app.Application
import joe.barker.config.ConfigImpl

lateinit var config: ConfigImpl

class MovieApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        config = ConfigImpl(applicationContext)
    }
}