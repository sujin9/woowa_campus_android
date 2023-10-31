package woowacourse.campus.data.di

import api.GetAllAnnouncementApi
import org.koin.dsl.module

val apiModules = module {
    single { GetAllAnnouncementApi() }
}
