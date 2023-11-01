package woowacourse.campus.data.di

import api.GetAllAnnouncementApi
import api.GetAnnouncementApi
import org.koin.dsl.module

val apiModules = module {
    single { GetAllAnnouncementApi() }
    single { GetAnnouncementApi() }
}
