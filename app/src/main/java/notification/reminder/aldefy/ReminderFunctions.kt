package notification.reminder.aldefy

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

/**
 * Created by aditlal on 17/08/17.
 */
fun scheduleReminderNotificationProblem(context: Context, seconds: Int, model: Model) {
    val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, ReminderProblemAlarmReceiver::class.java)
    intent.putExtra("reminder", model)
    val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    alarmMgr.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().timeInMillis + 10000, alarmIntent)
}

fun scheduleReminderNotificationSolution(context: Context, seconds: Int, model: Model) {
    val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, ReminderSolutionAlarmReceiver::class.java)
    val bytes = marshall(model)
    intent.putExtra("reminder", bytes)
    val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    alarmMgr.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().timeInMillis + 10000, alarmIntent)
}
