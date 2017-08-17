package notification.reminder.aldefy

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {


    lateinit var today: Date
    lateinit var pickedDateTime: Date
    var isSolution = false;
    @SuppressLint("SimpleDateFormat")
    var df: DateFormat = SimpleDateFormat("dd MMMM yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDTProb.setOnClickListener {
            if (etTitle.text.isNullOrEmpty() || etDesc.text.isNullOrEmpty()) {
                Toast.makeText(this@MainActivity, "Please enter some text", Toast.LENGTH_SHORT).show()
            } else {
                isSolution = false
                showDatePicker()
            }
        }
        btnDTSolution.setOnClickListener {
            if (etTitle.text.isNullOrEmpty() || etDesc.text.isNullOrEmpty()) {
                Toast.makeText(this@MainActivity, "Please enter some text", Toast.LENGTH_SHORT).show()
            } else {
                isSolution = true
                showDatePicker()
            }
        }
    }

    private fun showDatePicker() {
        today = Date()
        val cal = Calendar.getInstance()
        cal.time = today
        val datePickerDialog = DatePickerDialog.newInstance(this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        datePickerDialog!!.isThemeDark = false
        datePickerDialog.showYearPickerFirst(false)
        datePickerDialog.accentColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
        datePickerDialog.minDate = Calendar.getInstance()
        datePickerDialog.show(fragmentManager, "DatePickerDialog")
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        pickedDateTime = GregorianCalendar(year, monthOfYear, dayOfMonth).time
        tvReminder.text = String.format(resources.getString(R.string.reminder_set_for), df.format(pickedDateTime))
        val model = Model(msg = "some random message", title = "Hey yea this is title")
        if (isSolution)
            scheduleReminderNotificationSolution(this@MainActivity, model)
        else
            scheduleReminderNotificationProblem(this@MainActivity, model)
    }


}
