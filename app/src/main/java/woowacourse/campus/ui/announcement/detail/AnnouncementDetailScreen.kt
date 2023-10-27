package woowacourse.campus.ui.announcement.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun AnnouncementDetailScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // TODO: view model에서 받아온 값으로 대체
        val fakeTitle = "6기 우아한테크코스에 오신 것을 환영합니다! 꼭 읽어주세요 !"
        var fakeContent = ""
        repeat(30) { fakeContent += "안녕하세요. 6기 여러분.\n여러분 모두 민트를 봐주세요.\n" }

        AnnouncementInfoHeader("6기-공지사항", "하티", "2023.10.12 16:59")
        AnnouncementContent(fakeTitle, fakeContent)
        Spacer(modifier = Modifier.padding(20.dp))
    }
}

@Composable
fun AnnouncementInfoHeader(channel: String, author: String, createdAt: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .height(IntrinsicSize.Min)
    ) {
        HeaderText(channel)
        VerticalDivider()
        HeaderText(author)
        VerticalDivider()
        HeaderText(createdAt)
    }
}

@Composable
private fun HeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 8.dp)
            .width(2.dp)
            .background(color = MaterialTheme.colorScheme.onSurface)
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
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            maxLines = 2,
        )

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier
                .padding(horizontal = 12.dp)
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
    AnnouncementDetailScreen()
}
