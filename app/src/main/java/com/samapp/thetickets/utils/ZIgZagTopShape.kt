package com.samapp.thetickets.utils

import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.geometry.Size

class ZigZagTopShape(
    private val zigZagHeight: Float = 20f,
    private val zigZagWidth: Float = 20f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, zigZagHeight)

            var currentX = 0f
            var goingUp = true

            // Draw zigzag across the top edge
            while (currentX < size.width) {
                val nextX = currentX + zigZagWidth / 2
                val nextY = if (goingUp) 0f else zigZagHeight
                lineTo(nextX.coerceAtMost(size.width), nextY)
                currentX = nextX
                goingUp = !goingUp
            }

            // Draw the remaining sides (right, bottom, left)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        return Outline.Generic(path)
    }
}
