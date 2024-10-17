package theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyCustomTheme(content: @Composable () -> Unit){
    val colors = MaterialTheme.colors.copy(
        primary = MyOnPrimaryColor,
        onPrimary = MyPrimaryColor,
    )
    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}

@Composable
fun PrimaryColor(): Color {
    return if (MaterialTheme.colors.isLight) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onPrimary
    }
}
