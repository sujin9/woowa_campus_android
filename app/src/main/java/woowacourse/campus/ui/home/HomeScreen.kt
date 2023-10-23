package woowacourse.campus.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeScreen() {
    AnnouncementList()
}

@Composable
private fun AnnouncementList() {
    LazyColumn {
        items(3) { // view model 에서 받아온 데이터로 변경
            AnnouncementListItem("공지 $it 입니다", "내용 $it 입니다")
        }
    }
}

@Composable
private fun AnnouncementListItem(
    title: String,
    content: String,
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = content,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
private fun AnnouncementListItemPreview() {
    AnnouncementListItem("6기 우아한 테크코스에 오신 것을 환영합니다!!", "6기-공지사항 | 하티 | 2023.10.12 16:59")
}
