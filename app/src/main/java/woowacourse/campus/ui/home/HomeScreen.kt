package woowacourse.campus.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.campus.R

@Composable
internal fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AttendanceStatusBoard()
        Spacer(modifier = Modifier.padding(12.dp))
        AnnouncementList()
        Spacer(modifier = Modifier.padding(18.dp))
        ShortcutList()
    }
}

@Composable
fun AttendanceStatusBoard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
            )
    ) {

    }
}

@Composable
private fun AnnouncementList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HomeContentTitle(stringResource(R.string.home_announcement))
            Spacer(modifier = Modifier.padding(2.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_next),
                contentDescription = stringResource(R.string.home_announcement_description),
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10.dp),
                ),
        ) {
            items(3) { // view model 에서 받아온 데이터로 변경
                if (it != 0) {
                    Divider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    )
                }
                AnnouncementListItem("공지 $it 입니다", "내용 $it 입니다")
            }
        }
    }
}

@Composable
private fun AnnouncementListItem(
    title: String,
    content: String,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun ShortcutList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
    ) {
        HomeContentTitle(stringResource(R.string.home_shortcuts))
        Spacer(modifier = Modifier.padding(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val shortcutItems = listOf("찜꽁", "프롤로그", "LMS")
            items(shortcutItems.size) {
                ShortcutListItem(title = shortcutItems[it])
            }
        }
    }
}

@Composable
private fun ShortcutListItem(title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(10.dp),
            )
            .size(104.dp, 112.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "$title ${stringResource(id = R.string.home_shortcuts_description)}"
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp)
        )
    }
}

@Composable
private fun HomeContentTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
