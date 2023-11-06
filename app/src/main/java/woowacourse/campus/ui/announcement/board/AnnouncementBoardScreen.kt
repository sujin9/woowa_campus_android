package woowacourse.campus.ui.announcement.board

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.compose.koinViewModel
import woowacourse.campus.ui.common.VerticalDivider

@Composable
internal fun AnnouncementBoardScreen(
    announcementBoardViewModel: AnnouncementBoardViewModel = koinViewModel(),
    onAnnouncementItemClick: (announcementId: Long) -> Unit,
) {
    val uiState: AnnouncementBoardUiState by announcementBoardViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val listState: LazyListState = rememberLazyListState()
        AnnouncementList(
            uiState = uiState,
            listState = listState,
            onAnnouncementItemClick = onAnnouncementItemClick,
        )
        InfiniteListHandler(lazyListState = listState) {
            announcementBoardViewModel.getMoreAnnouncements()
        }
    }
}

@Composable
private fun AnnouncementList(
    uiState: AnnouncementBoardUiState,
    listState: LazyListState,
    onAnnouncementItemClick: (announcementId: Long) -> Unit,
) {
    when (uiState) {
        is AnnouncementBoardUiState.Success -> {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
            ) {
                items(uiState.announcements.size) {
                    val announcementPage = uiState.announcements[it]
                    AnnouncementListItem(
                        title = announcementPage.title,
                        channel = "6기 - 안드로이드",
                        author = announcementPage.author,
                        date = announcementPage.createdAt,
                        onAnnouncementItemClick = { onAnnouncementItemClick(announcementPage.id) },
                    )
                }
            }
        }
        is AnnouncementBoardUiState.Loading -> {}
    }
}

@Composable
fun InfiniteListHandler(
    lazyListState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit,
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItems - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                onLoadMore()
            }
    }
}

@Composable
private fun AnnouncementListItem(
    title: String,
    channel: String,
    author: String,
    date: String,
    onAnnouncementItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                ),
                shape = RoundedCornerShape(10.dp),
            )
            .height(
                intrinsicSize = IntrinsicSize.Min,
            )
            .padding(all = 16.dp)
            .clickable { onAnnouncementItemClick() },
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier.padding(
                top = 12.dp,
            ),
        ) {
            Text(
                text = channel,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            VerticalDivider(1.dp)
            Text(
                text = author,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            VerticalDivider(1.dp)
            Text(
                text = date,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

// @Preview(showSystemUi = true)
// @Composable
// private fun AnnouncementBoardScreenPreview() {
//    AnnouncementBoardScreen {}
// }
