package woowacourse.campus.ui.home

import woowacourse.campus.domain.model.Announcement

data class HomeUiState(
    val latestAnnouncements: List<Announcement>,
) {
    val latestAnnouncementsSize get() = latestAnnouncements.size
}
