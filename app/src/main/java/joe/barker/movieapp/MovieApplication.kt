package joe.barker.movieapp

import android.app.Application
import joe.barker.config.Config

lateinit var config: Config

class MovieApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        config = Config(applicationContext)
    }
}