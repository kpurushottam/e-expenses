package com.krp.android.expense.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.krp.android.expense.HomeActivity
import com.krp.android.expense.R

object ShortcutUtil {

    /**
     * Create a [ShortcutInfoCompat] for a reservation.
     */
    private fun createShortcutInfoForReservation(
        context: Context,
        reservationId: String,
        shortLabel: String,
        longLabel: String,
    ): ShortcutInfoCompat {
        val shortcutId = "active_reservation_$reservationId"
        return ShortcutInfoCompat.Builder(context, shortcutId)
            .setShortLabel(shortLabel)
            .setLongLabel(longLabel)
            .setIcon(IconCompat.createWithBitmap(textAsBitmap("1", 15f, Color.GREEN)!!))
            .setIntents(
                arrayOf(
                    Intent(context, HomeActivity::class.java).apply {
                        action = Intent.ACTION_VIEW
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    },
                    Intent(context, HomeActivity::class.java).apply {
                        action = Intent.ACTION_VIEW
                        val bundle = Bundle().apply {
                            putString("reservation_id", reservationId)
                        }
                        putExtras(bundle)
                    },
                )
            )
            .build()
    }

    fun textAsBitmap(text: String?, textSize: Float, textColor: Int): Bitmap? {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        val baseline = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 0.5f).toInt() // round
        val height = (baseline + paint.descent() + 0.5f).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text!!, 0f, baseline, paint)
        return image
    }

    /**
     * Request creating Pinned App Shortcut for a reservation.
     */
    @Throws(Exception::class)
    fun requestPinShortcutForReservation(
        context: Context,
        reservationId: String,
        shortLabel: String,
        longLabel: String,
        requestCode: Int = 1,
    ) {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
            val shortcutInfo = createShortcutInfoForReservation(
                context,
                reservationId,
                shortLabel,
                longLabel,
            )

            val intent = ShortcutManagerCompat.createShortcutResultIntent(
                context,
                shortcutInfo,
            )

            val successCallback = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )

            ShortcutManagerCompat.requestPinShortcut(
                context,
                shortcutInfo,
                successCallback.intentSender,
            )
        } else {
            throw Exception("Pinned App Shortcut not supported")
        }
    }
}