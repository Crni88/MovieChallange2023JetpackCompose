import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FractionalStar(
    fillFraction: Float,
    starSize: Dp = 32.dp
) {
    Box(modifier = Modifier.size(starSize)) {
        // Draw the outlined star as the background, tinted gray.
        Icon(
            imageVector = Icons.Outlined.Star,
            contentDescription = "Empty star",
            tint = Color.Gray,
            modifier = Modifier.matchParentSize()
        )
        // Draw the filled star overlay only if there's some fill.
        if (fillFraction > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(starSize * fillFraction)
                    .clip(RectangleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Filled star",
                    tint = Color.Yellow,
                    modifier = Modifier.matchParentSize()
                )
            }
        }
    }
}

@Composable
fun FiveStarRating(
    rating: Float,
    starSize: Dp = 32.dp
) {
    Row {
        // Create five stars and calculate the fill fraction for each.
        for (i in 0 until 5) {
            val fill = (rating - i).coerceIn(0f, 1f)
            FractionalStar(fillFraction = fill, starSize = starSize)
        }
    }
}
