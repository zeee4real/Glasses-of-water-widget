package com.example.glassesofwaterwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RemoteViews
import android.widget.Toast

class WaterGlassesWidget: AppWidgetProvider() {

    companion object {
        private const val ADD_CLICK = "ADD_CLICK"
    }

    private var glassContainerLayout: LinearLayout? = null

        override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        context?.let {
            appWidgetIds?.let {
                for (widgetId in appWidgetIds) {
                    val view = RemoteViews(context.packageName, R.layout.water_glasses_widget)
                    view.setOnClickPendingIntent(
                        R.id.addBtn,
                        getPendingIntent(context, ADD_CLICK)
                    )
                    appWidgetManager?.updateAppWidget(widgetId, view)
                }
            }
        }
    }

    private fun getPendingIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context,0,intent,0)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent);
        context?.let {
            intent?.let {
                if(ADD_CLICK == intent.action) {
                    val imageView = ImageView(context)
                    imageView.setImageResource(R.drawable.water)
                }
            }
        }
    }
}