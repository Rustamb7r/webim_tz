package ru.webim.tz.presentation.base

import android.app.Application
import ru.webim.tz.di.AppComponent
import ru.webim.tz.di.DaggerAppComponent

class BaseApplication : Application() {

    var appComponent: AppComponent = DaggerAppComponent
        .factory()
        .create(
            application = this,
            applicationContext = this
        )
}