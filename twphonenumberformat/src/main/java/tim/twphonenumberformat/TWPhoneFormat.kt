package tim.twphonenumberformat

import android.text.InputFilter
import android.widget.EditText

import java.util.ArrayList

object TWPhoneFormat {

    private val areaCodeList = object : ArrayList<String>() {
        init {
            add("02")
            add("03")
            add("04")
            add("05")
            add("06")
            add("07")
            add("08")
        }
    }

    private val areaCodeList2 = object : ArrayList<String>() {
        init {
            add("037")
            add("049")
            add("082")
            add("089")
            add("0836")
        }
    }

    private val areaCodeList3 = object : ArrayList<String>() {
        init {
            add("0836")
        }
    }

    private val eightDigits = object : ArrayList<String>() {
        init {
            add("(02)")
            add("(04)")
        }
    }

    private val sevenDigits = object : ArrayList<String>() {
        init {
            add("(03)")
            add("(04)7")
            add("(04)8")
            add("(05)")
            add("(06)")
            add("(07)")
            add("(08)")
        }
    }

    private val sevenDigits2 = object : ArrayList<String>() {
        init {
            add("(049)")
        }
    }

    private val sixDigits = object : ArrayList<String>() {
        init {
            add("(037)")
            add("(082)")
            add("(089)")
        }
    }

    private val fiveDigits = object : ArrayList<String>() {
        init {
            add("(0836)")
        }
    }

    /**
     * @param s phone number
     * @return formatted phone number
     */
    @JvmStatic
    fun getFormattedPhone(s: String?): String {

        if (s != null) {
            val stringBuilder = StringBuilder(getNumber(s))

            if (stringBuilder.length > 1) {

                val phoneCode = "09"
                if (stringBuilder.substring(0, 2) == phoneCode) {
                    if (stringBuilder.length > 4) {
                        stringBuilder.insert(4, "-")
                    }

                    if (stringBuilder.length > 8) {
                        stringBuilder.insert(8, "-")
                    }

                } else {
                    var isFormat = false
                    if (stringBuilder.length > 3) {
                        for (areaCode in areaCodeList3) {
                            if (stringBuilder.toString().substring(0, 4) == areaCode) {
                                formatAreaCode(stringBuilder, areaCode)
                                isFormat = true
                                break
                            }
                        }
                    }

                    if (!isFormat && stringBuilder.length > 2) {
                        for (areaCode in areaCodeList2) {
                            if (stringBuilder.toString().substring(0, 3) == areaCode) {
                                formatAreaCode(stringBuilder, areaCode)
                                isFormat = true
                                break
                            }
                        }
                    }

                    if (!isFormat) {
                        for (areaCode in areaCodeList) {
                            if (stringBuilder.toString().substring(0, 2) == areaCode) {
                                formatAreaCode(stringBuilder, areaCode)
                                break
                            }

                        }
                    }

                    sevenDigits
                            .filter { addDash(stringBuilder, it, 7) }
                            .forEach { return stringBuilder.toString() }

                    sevenDigits2
                            .filter { addDash(stringBuilder, it, 8) }
                            .forEach { return stringBuilder.toString() }

                    eightDigits
                            .filter { addDash(stringBuilder, it, 8) }
                            .forEach { return stringBuilder.toString() }

                    sixDigits
                            .filter { addDash(stringBuilder, it, 7) }
                            .forEach { return stringBuilder.toString() }

                    fiveDigits
                            .filter { addDash(stringBuilder, it, 7) }
                            .forEach { return stringBuilder.toString() }
                }
            }

            return stringBuilder.toString()
        }

        return ""

    }

    /**
     * @param editText input field , set the length according to the area code
     * @param s        phone number
     * @return formatted phone number
     */
    @JvmStatic
    fun getFormattedPhone(editText: EditText, s: String): String {

        val stringBuilder = StringBuilder(getNumber(s))

        if (stringBuilder.length > 1) {

            val phoneCode = "09"
            if (stringBuilder.substring(0, 2) == phoneCode) {
                if (stringBuilder.length > 4) {
                    stringBuilder.insert(4, "-")
                }

                if (stringBuilder.length > 8) {
                    stringBuilder.insert(8, "-")
                }
                editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(12))
                return stringBuilder.toString()

            } else {
                var isFormat = false
                if (stringBuilder.length > 3) {
                    for (areaCode in areaCodeList3) {
                        if (stringBuilder.toString().substring(0, 4) == areaCode) {
                            formatAreaCode(stringBuilder, areaCode)
                            isFormat = true
                            break

                        }
                    }
                }

                if (isFormat.not() && stringBuilder.length > 2) {
                    for (areaCode in areaCodeList2) {
                        if (stringBuilder.toString().substring(0, 3) == areaCode) {
                            formatAreaCode(stringBuilder, areaCode)
                            isFormat = true
                            break
                        }
                    }
                }

                if (isFormat.not()) {
                    for (areaCode in areaCodeList) {
                        if (stringBuilder.toString().substring(0, 2) == areaCode) {
                            formatAreaCode(stringBuilder, areaCode)
                            break
                        }
                    }
                }

                sevenDigits
                        .filter { addDash(stringBuilder, it, 7, editText, 12) }
                        .forEach { return stringBuilder.toString() }

                sevenDigits2
                        .filter { addDash(stringBuilder, it, 8, editText, 13) }
                        .forEach { return stringBuilder.toString() }

                eightDigits
                        .filter { addDash(stringBuilder, it, 8, editText, 13) }
                        .forEach { return stringBuilder.toString() }

                sixDigits
                        .filter { addDash(stringBuilder, it, 7, editText, 12) }
                        .forEach { return stringBuilder.toString() }

                fiveDigits
                        .filter { addDash(stringBuilder, it, 7, editText, 12) }
                        .forEach { return stringBuilder.toString() }
            }
        }

        editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))
        return stringBuilder.toString()
    }

    /**
     * @param s formatted phone number
     * @return phone number without any symbol
     */
    @JvmStatic
    fun getNumber(s: String): String = s.replace("(", "").replace(")", "").replace("-", "")

    private fun formatAreaCode(stringBuilder: StringBuilder, areaCode: String) {
        if (!stringBuilder.toString().contains("(")) {
            stringBuilder.insert(0, "(")
        }
        if (!stringBuilder.toString().contains(")")) {
            stringBuilder.insert(areaCode.length + 1, ")")
        }
    }

    private fun checkArea(stringBuilder: StringBuilder, digit: String): Boolean = stringBuilder.toString().contains(digit)

    private fun addDash(stringBuilder: StringBuilder, eightDigit: String, index: Int, editText: EditText, length: Int): Boolean {
        if (checkArea(stringBuilder, eightDigit)) {
            if (stringBuilder.length > index) {
                editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(length))
                stringBuilder.insert(index, "-")
                return true
            }
            return true
        }
        return false
    }

    private fun addDash(stringBuilder: StringBuilder, eightDigit: String, index: Int): Boolean {
        if (checkArea(stringBuilder, eightDigit)) {
            if (stringBuilder.length > index) {
                stringBuilder.insert(index, "-")
                return true
            }
            return true
        }
        return false
    }
}
