package woowacourse.campus.data.di

import org.koin.dsl.module
import woowacourse.campus.data.repository.AnnouncementRepository

val repositoryModules = module {
    single { AnnouncementRepository(get()) }
}
