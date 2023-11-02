package woowacourse.campus.data.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.data.repository.AuthRepository

val repositoryModules = module {
    single { AnnouncementRepository(get(), get()) }
    single { AuthRepository(androidContext()) }
}
