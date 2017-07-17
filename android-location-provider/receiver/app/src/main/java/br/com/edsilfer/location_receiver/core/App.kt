package br.com.edsilfer.location_receiver.core

import android.app.Application
import android.content.Context
import timber.log.Timber


/**
 * Created by ferna on 5/14/2017.
 */
class App : Application() {
    companion object {
        private var mApp: App? = null
        fun getContext(): Context {
            if (mApp == null) {
                return Application()
            }
            return mApp!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        Timber.plant(Timber.DebugTree())
    }
}