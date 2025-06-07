package com.samapp.thetickets.utils

import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ZigZagLeftShape(
    private val zigZagHeight: Float = 30f,
    private val zigZagWidth: Float = 20f
) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        path.moveTo(0f, 0f)

        var y = 0f
        var toggle = true

        while (y < size.height) {
            if (toggle) {
                path.lineTo(zigZagWidth, y + zigZagHeight / 2)
            } else {
                path.lineTo(0f, y + zigZagHeight / 2)
            }
            y += zigZagHeight / 2
            toggle = !toggle
        }

        // Complete the other 3 sides of the rectangle
        path.lineTo(size.width, size.height)
        path.lineTo(size.width, 0f)
        path.close()

        return Outline.Generic(path)
    }
}