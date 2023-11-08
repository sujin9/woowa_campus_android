package woowacourse.campus.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import woowacourse.campus.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    navigateToHome: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var password by rememberSaveable {
            mutableStateOf("")
        }
        Spacer(modifier = Modifier.padding(top = 160.dp))
        Image(
            painter = painterResource(
                id = R.drawable.img_logo,
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.padding(top = 32.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(
                    text = "비밀번호를 입력해주세요.",
                    modifier = Modifier
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Black,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(10.dp),
                )
                .border(
                    BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                    ),
                    shape = RoundedCornerShape(10.dp),
                ),
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Button(
            onClick = {
                loginViewModel.updateToken(password)
                navigateToHome()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        ) {
            Text(
                text = "로그인하기",
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AnnouncementBoardScreenPreview() {
    LoginScreen {}
}
