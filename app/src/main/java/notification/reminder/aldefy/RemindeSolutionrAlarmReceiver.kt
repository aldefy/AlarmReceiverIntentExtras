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
class ReminderSolutionAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val bytes = intent.extras["reminder"] as ByteArray //retrieve intent extras as bytes[]
        val parcel = unmarshall(bytes = bytes) // unmarshall bytes[] into Parcelable object
        val reminder = Model.Companion.CREATOR.createFromParcel(parcel) // Create our model class from parcel
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)
        val title: String = reminder.title
        val desc: String = reminder.msg

        val notification = NotificationCompat.Builder(context, "Reminders")
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build()
        notificationManager.notify(1, notification)
    }
}
