package data

import kotlinx.serialization.Serializable


@Serializable
data class TokenImageLogos(
    val name: String,
    val logoURI: String,
    val logoImage: ByteArray? = null
)