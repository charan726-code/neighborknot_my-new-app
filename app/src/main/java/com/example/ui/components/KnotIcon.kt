package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.TertiaryAmberWarm

/**
 * An organic hand-tied knot symbol representing reciprocal community connection.
 */
@Composable
fun KnotGlyph(
    modifier: Modifier = Modifier,
    color: Color = TertiaryAmberWarm,
    size: Dp = 20.dp,
    strokeWidth: Float = 5f
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height

        // Draw an organic reef knot / infinity loop
        val path = Path().apply {
            moveTo(w * 0.2f, h * 0.45f)
            cubicTo(
                w * 0.05f, h * 0.15f,
                w * 0.45f, h * 0.10f,
                w * 0.50f, h * 0.50f
            )
            cubicTo(
                w * 0.55f, h * 0.90f,
                w * 0.95f, h * 0.85f,
                w * 0.80f, h * 0.50f
            )
            cubicTo(
                w * 0.70f, h * 0.20f,
                w * 0.30f, h * 0.80f,
                w * 0.20f, h * 0.55f
            )
        }

        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
