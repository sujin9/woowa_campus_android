package woowacourse.campus.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import woowacourse.campus.ui.announcement.board.AnnouncementBoardViewModel
import woowacourse.campus.ui.home.HomeViewModel

val viewModelModules = module {
    viewModel { HomeViewModel(get()) }
    viewModel { AnnouncementBoardViewModel(get()) }
}
