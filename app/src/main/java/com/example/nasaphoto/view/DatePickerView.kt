package com.example.nasaphoto.view

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.DatePicker
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.nasaphoto.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.date_picker_view.view.*
import java.util.Calendar

class DatePickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr),
    LayoutContainer,
    DatePickerDialog.OnDateSetListener {

    override val containerView: View?
        get() = this

    lateinit var onDateClickedListener: (date: String) -> Unit

    init {
        View.inflate(context, R.layout.date_picker_view, this)
        configureView()
    }

    fun setOnDatePickedListener(listener: (date: String) -> Unit) {
        onDateClickedListener = listener
    }

    private fun configureView() {
        date_picker_icon.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()
        }

        invalidateView()
    }

    private fun invalidateView() {
        invalidate()
        requestLayout()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = "$year-${month + 1}-$dayOfMonth"
        onDateClickedListener.invoke(date)
    }
}
