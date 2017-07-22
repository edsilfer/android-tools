package br.com.edsilfer.task_scheduler

import android.app.Application
import rx_activity_result2.RxActivityResult
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        RxActivityResult.register(this);
    }

}