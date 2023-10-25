package woowacourse.campus.data.di

import api.AnnouncementApi
import org.koin.dsl.module

val apiModules = module {
    single { AnnouncementApi() }
}
