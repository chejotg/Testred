package com.example.sergio.robleredtest.Utils

import android.widget.DatePicker
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.example.sergio.robleredtest.R
import kotlinx.android.synthetic.main.content_login.*
import java.util.*


class MyDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {
    internal var pYear: Int = 0
    internal var pDay: Int = 0
    internal var pMonth: Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dialogDatePicker = DatePickerDialog(getActivity(),R.style.CustomDatePickerDialogTheme, this, year, month, day)
        dialogDatePicker.datePicker.spinnersShown = true
        dialogDatePicker.datePicker.calendarViewShown = false
        return dialogDatePicker
        // Create a new instance of DatePickerDialog and return it
        //return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        pYear = year
        pDay = day
        pMonth = month
        txtDateofBirth!!.setText("$pDay/$pMonth/$pYear")
    }
}