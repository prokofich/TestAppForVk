package com.kvk.testappforvk.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class CustomWatchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var width = 0
    private var height = 0
    private var padding = 0
    private var fontSize = 0
    private var numeralSpacing = 0
    private var handTruncation = 0.0
    private var hourHandTruncation = 0.0
    private var radius = 0
    private val paint = Paint()
    private var isInit = false
    private val handler = Handler()

    init {
        initClock()
    }

    private fun initClock() {
        width = getWidth()
        height = getHeight()
        val minDimension = min(width, height)
        padding = minDimension / 20
        fontSize = minDimension / 12
        numeralSpacing = minDimension / 10
        handTruncation = (minDimension / 8) * 0.3
        hourHandTruncation = (minDimension / 5) * 0.3
        radius = minDimension / 2 - padding
        isInit = true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (!isInit) { initClock() }

        canvas.drawColor(Color.parseColor("#FFFFFF"))
        drawCircle(canvas)
        drawCenter(canvas)
        drawHands(canvas)
        drawNumeral(canvas)
        drawSecondsDots(canvas)
        handler.postDelayed(runnable, 1000)

    }

    private fun drawCircle(canvas: Canvas) {
        paint.reset()
        paint.color = Color.BLACK
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        val cx = width / 2f
        val cy = height / 2f
        val diameter = (min(width, height) - padding * 2).toFloat()
        canvas.drawCircle(cx, cy, diameter / 2, paint)
    }

    private fun drawCenter(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 12f, paint)
    }

    private fun drawHands(canvas: Canvas) {
        val calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR_OF_DAY).toFloat()
        hour = if (hour > 12) hour - 12 else hour
        drawHand(canvas, (hour + calendar.get(Calendar.MINUTE) / 60) * 5f, true)
        drawHand(canvas, calendar.get(Calendar.MINUTE).toFloat(), false)
        drawHand(canvas, calendar.get(Calendar.SECOND).toFloat(), false)
    }

    private fun drawHand(canvas: Canvas, loc: Float, isHour: Boolean) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handLength = if (isHour) radius * 0.5 else radius * 0.6


        val handWidth = if (isHour) (radius * 0.06).toFloat() else (radius * 0.04).toFloat()
        paint.strokeWidth = handWidth
        paint.color = Color.BLACK
        canvas.drawLine((width / 2).toFloat(),
            (height / 2).toFloat(),
            (width / 2 + cos(angle) * handLength).toFloat(),
            (height / 2 + sin(angle) * handLength).toFloat(),
            paint
        )
    }

    private fun drawNumeral(canvas: Canvas) {
        paint.textSize = fontSize.toFloat()
        val center = Point(width / 2, height / 2)
        for (number in 1..12) {
            val angle = Math.PI / 6 * (number - 3)
            val x = (center.x + cos(angle) * (radius - numeralSpacing) - paint.measureText(number.toString()) / 2).toFloat()
            val y = (center.y + sin(angle) * (radius - numeralSpacing) + fontSize / 3).toFloat()
            paint.color = Color.BLACK
            canvas.drawText(number.toString(), x, y, paint)
        }
    }

    private fun drawSecondsDots(canvas: Canvas) {
        paint.reset()
        paint.color = Color.BLACK
        val center = Point(width / 2, height / 2)
        val dotRadius = (radius * 0.01).toFloat()
        val dotSpacingAngle = Math.PI / 30
        val distanceToNumbers = radius * 0.95
        for (i in 0 until 60) {
            val angle = Math.PI / 2 - dotSpacingAngle * i
            val x = (center.x + cos(angle) * distanceToNumbers).toFloat()
            val y = (center.y - sin(angle) * distanceToNumbers).toFloat()

            val distanceToCenter = sqrt((x - center.x).toDouble().pow(2.0) + (y - center.y).toDouble().pow(2.0)).toFloat()
            if (distanceToCenter + dotRadius <= radius) {
                canvas.drawCircle(x, y, dotRadius, paint)
            }
        }
    }

    private val runnable = Runnable { invalidate() }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        isInit = false

    }

}