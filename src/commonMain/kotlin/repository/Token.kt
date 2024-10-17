package repository

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


@Serializable
data class Token(
    val name: String,
    val symbol: String,
    val price: Double,
    val change: Double,
    val logoImage: ByteArray? = null
)