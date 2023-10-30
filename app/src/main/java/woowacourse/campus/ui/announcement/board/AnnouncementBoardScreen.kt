package woowacourse.campus.ui.announcement.board

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun AnnouncementBoardScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnnouncementList()
    }
}

@Composable
private fun AnnouncementList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        items(3) {
            AnnouncementListItem(
                "6기 전체공지사항입니다.,", "6기 - 공지사항",
                "하티",
                "2022.01.04 15:42:33"
            )
        }
    }
}


@Composable
private fun AnnouncementListItem(
    title: String,
    channel: String,
    author: String,
    date: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                ),
                shape = RoundedCornerShape(10.dp),
            )
            .height(
                intrinsicSize = IntrinsicSize.Min
            )
            .padding(all = 16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Row(
            modifier = Modifier.padding(
                top = 8.dp
            ),
        ) {
            Text(
                text = channel,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxHeight()  //fill the max height
                    .width(1.dp)
            )
            Text(
                text = author,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Text(
                text = date,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AnnouncementBoardScreenPreview() {
    AnnouncementBoardScreen()
}