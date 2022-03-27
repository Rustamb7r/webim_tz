package ru.webim.tz.presentation.ground

import android.app.Application
import ru.webim.tz.di.AppComponent
import ru.webim.tz.di.DaggerAppComponent

class GroundApplication : Application() {

    var appComponent: AppComponent = DaggerAppComponent
        .factory()
        .create(
            application = this,
            applicationContext = this
        )
}