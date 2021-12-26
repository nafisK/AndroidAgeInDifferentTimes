package com.example.ageindifferenttime

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View) {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            // outputs as vars from the given inputs
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(this, "$selectedDay/$selectedMonth/$selectedYear", Toast.LENGTH_SHORT).show();

                // setting date to id in xml
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)

                // converting the date to a more understandable date using words
                // sdf: simple date format
                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
                // parsing returns a date object
                val theDate = sdf.parse(selectedDate)

                // converts the date from jan 1 1970: milli seconds to minutes
                val selectedDateInMinutes = theDate!!.time / 60000

                // converts selected time to milliseconds and then mins
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000

                // getting different of selected time
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())


            },
            // inputs from the Calender Class
            year, month, dayOfMonth
        )

        // dpd separated to limit and set a max date along with removing the current day to not see 0 as a result
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}