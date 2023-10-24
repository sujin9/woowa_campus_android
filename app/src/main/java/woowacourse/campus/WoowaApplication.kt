package woowacourse.campus

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import woowacourse.campus.di.homeModule

class WoowaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WoowaApplication)
            modules(homeModule)
        }
    }
}
