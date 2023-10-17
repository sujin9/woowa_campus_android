package woowacourse.campus

import api.DefaultApi
import kotlinx.coroutines.test.runTest
import model.ResourceIdGet200Response
import org.junit.Assert.assertEquals
import org.junit.Test

class GeneratedApiTest {
    private val MOCK_SERVER_URL = "https://2b668cd5-b8c2-4172-b282-9c601ce3661a.mock.pstmn.io"

    @Test
    fun codegenTest() = runTest {
        val api = DefaultApi(
            MOCK_SERVER_URL, null, null
        )
        val response = api.resourceIdGet(1)
        val actual = response.body()
        val expected = ResourceIdGet200Response(1, "Example Resource")

        assertEquals(expected, actual)
    }
}
