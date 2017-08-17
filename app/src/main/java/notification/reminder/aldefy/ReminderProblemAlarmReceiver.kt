package notification.reminder.aldefy

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat

/**
 * Created by aditlal on 17/08/17.
 */
class ReminderProblemAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val reminder = intent.extras["reminder"] as Model // Null - problem
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)
        val notification = NotificationCompat.Builder(context, "Reminders")
                .setContentTitle(reminder.title)
                .setContentText(reminder.msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build()
        notificationManager.notify(1, notification)
    }
}
