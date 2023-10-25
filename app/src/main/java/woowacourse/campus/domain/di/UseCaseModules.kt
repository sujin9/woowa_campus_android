package woowacourse.campus.domain.di

import org.koin.dsl.module
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase

val useCaseModules = module {
    single { GetLatestAnnouncementsUseCase(get()) }
}
