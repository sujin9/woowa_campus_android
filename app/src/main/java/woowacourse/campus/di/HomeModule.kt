package woowacourse.campus.di

import api.AnnouncementApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase
import woowacourse.campus.ui.home.HomeViewModel

val homeModule = module {
    single { AnnouncementApi() }
    factory { AnnouncementRepository(get()) }
    factory { GetLatestAnnouncementsUseCase(get()) }
    viewModel { HomeViewModel(get()) }
}
