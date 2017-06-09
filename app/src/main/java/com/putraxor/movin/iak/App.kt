package com.putraxor.movin.iak

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.putraxor.movin.iak.data.DBMaker

/**
 * Created by putraxor on 04/06/17.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DBMaker.createDb(this)
        Fresco.initialize(this)
    }
}
