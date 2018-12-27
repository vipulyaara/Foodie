package com.foodie.consumer.config

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.security.Key
import java.security.KeyStore
import javax.crypto.KeyGenerator

/**
 * @author Vipul Kumar; dated 27/12/18.
 *
 * Utility to generate and encrypt api keys.
 */
class ApiKeyStore {
    private val alias = "foursquare_api_key"
    val provider = "AndroidKeyStore"
    val keyStore = KeyStore.getInstance(provider)

    @RequiresApi(Build.VERSION_CODES.M)
    fun generateKey() {
        keyStore.load(null)

        if (!keyStore.containsAlias(alias)) {
            val keyGenerator =
                KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, provider)
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setRandomizedEncryptionRequired(false)
                    .build()
            )
            keyGenerator.generateKey()
        }
    }

    fun fetchApiKey(): Key? {
        return keyStore.getKey(alias, null)
    }
}
