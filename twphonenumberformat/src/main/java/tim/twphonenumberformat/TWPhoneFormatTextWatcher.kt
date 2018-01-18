package tim.twphonenumberformat

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by tim.lu on 2017/2/6.
 */

class TWPhoneFormatTextWatcher(private val editText: EditText) : TextWatcher {

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, count: Int) {
        if (count != 0) {
            editText.removeTextChangedListener(this)
            val temp = charSequence.toString()

            if (temp.isEmpty()) {
                editText.setText("")
            } else {
                editText.setText(TWPhoneFormat.getFormattedPhone(editText, temp))
                editText.setSelection(editText.text.length)
            }

            editText.addTextChangedListener(this)
        }
    }

    override fun afterTextChanged(editable: Editable) {

    }
}
