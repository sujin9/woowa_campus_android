package woowacourse.campus

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import woowacourse.campus.data.di.apiModules
import woowacourse.campus.data.di.repositoryModules
import woowacourse.campus.domain.di.useCaseModules
import woowacourse.campus.ui.di.viewModelModules

class WoowaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WoowaApplication)
            modules(apiModules)
            modules(repositoryModules)
            modules(useCaseModules)
            modules(viewModelModules)
        }
    }
}
