package repository

import androidx.compose.ui.graphics.vector.ImageVector

data class StakingItemData(
    val name: String,
    val abbreviation: String,
    val apr: String,
    val iconRes: ImageVector // This would be a resource ID for the icon
)