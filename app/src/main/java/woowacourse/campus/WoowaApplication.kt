package woowacourse.campus

import android.app.Application
import android.content.Context

class WoowaApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: WoowaApplication

        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }
}
