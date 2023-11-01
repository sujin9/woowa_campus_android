package woowacourse.campus.ui.announcement.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import woowacourse.campus.ui.common.VerticalDivider

@Composable
internal fun AnnouncementDetailScreen(
    id: Long,
    announcementDetailViewModel: AnnouncementDetailViewModel = koinViewModel()
) {
    announcementDetailViewModel.updateUiState(id)
    val scrollState = rememberScrollState()
    val uiState: AnnouncementDetailUiState by announcementDetailViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (val state = uiState) {
            is AnnouncementDetailUiState.Success -> {
                AnnouncementInfoHeader(
                    "6기 - 공지사항",
                    state.author,
                    state.createdAt
                )
                AnnouncementContent(state.title, state.content)
            }

            is AnnouncementDetailUiState.Loading -> {}
            is AnnouncementDetailUiState.Failure -> {}
        }
        Spacer(modifier = Modifier.padding(20.dp))
    }
}


@Composable
fun AnnouncementInfoHeader(channel: String, author: String, createdAt: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(top = 20.dp, bottom = 12.dp)
            .height(IntrinsicSize.Min),
    ) {
        HeaderText(channel)
        VerticalDivider(2.dp)
        HeaderText(author)
        VerticalDivider(2.dp)
        HeaderText(createdAt)
    }
}

@Composable
private fun HeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
fun AnnouncementContent(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 380.dp)
            .padding(horizontal = 20.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(10.dp),
            )
            .border(
                BorderStroke(1.dp, color = MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(10.dp),
            ),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
        )

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier
                .padding(horizontal = 12.dp),
        )

        Text(
            text = content,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AnnouncementDetailPreview() {
    AnnouncementDetailScreen(id = 0)
}
