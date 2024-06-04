package com.illeyrocci.cityweather.presentation.city_list.components

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CityStickyLabelDecoration(
    private val labelTextSize: Int,
    private val labelWidth: Int
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val adapter = parent.adapter as CityAdapter
        var firstLetterOfGroup: Char? = null

        for (i in 0 until parent.childCount) {

            val child = parent.getChildAt(i)
            val adapterPosition = parent.getChildAdapterPosition(child)
            val firstLetterOfThisCity = adapter.cityNameAt(adapterPosition)!!.first()
            val firstLetterOfTopCity =
                adapter.cityNameAt(parent.getChildAdapterPosition(parent.getChildAt(0)))!!.first()
            val firstLetterOfNextTopCity = if (parent.childCount == 1) null else
                adapter.cityNameAt(parent.getChildAdapterPosition(parent.getChildAt(1)))!!.first()

            if (firstLetterOfGroup == null || firstLetterOfGroup != firstLetterOfThisCity) {
                val paint = Paint()
                paint.color = Color.BLACK
                paint.textSize = labelTextSize.toFloat()
                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                val textBounds = Rect()
                paint.getTextBounds(firstLetterOfThisCity.toString(), 0, 1, textBounds)
                val textWidth = textBounds.right - textBounds.left
                val textHeight = textBounds.bottom - textBounds.top
                val x = (child.left - textWidth) / 2
                val y = (child.bottom + textHeight
                        + child.top * if (firstLetterOfThisCity == firstLetterOfTopCity
                    && firstLetterOfThisCity == firstLetterOfNextTopCity) -1 else 1) / 2
                c.drawText(
                    firstLetterOfThisCity.toString(),
                    x.toFloat(),
                    y.toFloat(),
                    paint
                )
            }
            firstLetterOfGroup = firstLetterOfThisCity
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left += labelWidth
    }
}