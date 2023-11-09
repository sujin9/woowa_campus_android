package woowacourse.campus.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class AuthRepository(context: Context) {

    private val masterKey: MasterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val storage: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    private val editor: SharedPreferences.Editor = storage.edit()

    fun putToken(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getToken(key: String): String {
        return storage.getString(key, DEFAULT_TOKEN) ?: DEFAULT_TOKEN
    }

    companion object {
        private const val FILE_NAME = "Campus"
        private const val DEFAULT_TOKEN = ""
    }
}
