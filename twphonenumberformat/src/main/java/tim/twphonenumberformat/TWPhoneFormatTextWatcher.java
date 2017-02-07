package tim.twphonenumberformat;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by tim.lu on 2017/2/6.
 */

public class TWPhoneFormatTextWatcher implements TextWatcher {

    private EditText editText;

    public TWPhoneFormatTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
        if (count != 0) {
            editText.removeTextChangedListener(this);
            String temp = charSequence.toString();

            if (temp.length() == 0) {
                editText.setText("");
            } else {
                editText.setText(TWPhoneFormat.getFormattedPhone(editText, temp));
                editText.setSelection(editText.getText().length());
            }

            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
