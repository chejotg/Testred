package com.example.sergio.robleredtest.Utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class DateInputMask(val input : EditText) {

    fun listen() {
        input.addTextChangedListener(mDateEntryWatcher)
    }

    private val mDateEntryWatcher = object : TextWatcher {

        var edited = false
        val dividerCharacter = "/"

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            if (edited) {
                edited = false
                return
            }
            var working = getEditText()
            working = manageDateDivider(working, 2, start, before)
            working = manageDateDivider(working, 5, start, before)

            edited = true
            input.setText(working)
            input.setSelection(input.text.length)
            if( DateValidator().validate(input.text.toString())){
                input.setText("")
            }

        }

        private fun manageDateDivider(working: String, position : Int, start: Int, before: Int) : String{
            if (working.length == position) {
                return if (before <= position && start < position)
                    working + dividerCharacter
                else
                    working.dropLast(1)
            }
            return working
        }

        private fun getEditText() : String {
            return if (input.text.length >= 10)
                input.text.toString().substring(0, 10)
            else  input.text.toString()
        }

        override fun afterTextChanged(s: Editable) {
        }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }
}
class DateValidator {

    private val pattern: Pattern
    private var matcher: Matcher? = null

    init {
        pattern = Pattern.compile(DATE_PATTERN)
    }

    /**
     * Validate date format with regular expression
     * @param date date address for validation
     * @return true valid date fromat, false invalid date format
     */
    fun validate(date: String): Boolean {

        matcher = pattern.matcher(date)

        if (matcher!!.matches()) {

            matcher!!.reset()

            if (matcher!!.find()) {

                val day = matcher!!.group(1)
                val month = matcher!!.group(2)
                val year = Integer.parseInt(matcher!!.group(3))

                return if (day == "31" && (month == "4" || month == "6" || month == "9" ||
                                month == "11" || month == "04" || month == "06" ||
                                month == "09")) {
                    false // only 1,3,5,7,8,10,12 has 31 days
                } else if (month == "2" || month == "02") {
                    //leap year
                    if (year % 4 == 0) {
                        !(day == "30" || day == "31")
                    } else {
                        !(day == "29" || day == "30" || day == "31")
                    }
                } else {
                    true
                }
            } else {
                return false
            }
        } else {
            return false
        }
    }

    companion object {

        private val DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])[- /.](0?[1-9]|1[012])[- /.](19|20)\\d\\d"
    }
}