package com.example.myagecal

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvselectedDate :TextView? = null
    private var tvintomintview :TextView? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.datepicker)
        tvselectedDate = findViewById(R.id.FixedDate)
        tvintomintview = findViewById(R.id.intMints)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickDatePicker()
    {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd =  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { view, selectedyear, selectedmonth, selecteddayOfMonth ->
//                Toast.makeText(this, "Selected Date is: $selectedyear/${selectedmonth+1}/$selecteddayOfMonth", Toast.LENGTH_LONG).show()

                val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                tvselectedDate?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                   val dateintoMint = theDate.time/60000
                    val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentdate?.let {
                        val cureentdateintoMints = currentdate.time/60000
                        val diff = cureentdateintoMints-dateintoMint
                        tvintomintview?.text = diff.toString()
                    }
                }
            },
            year,
            month,
            day
        )//.show()
        dpd.datePicker.maxDate = System.currentTimeMillis()-8600000
        dpd.show()
    }

}
