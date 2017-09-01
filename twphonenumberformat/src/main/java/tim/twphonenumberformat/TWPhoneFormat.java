package tim.twphonenumberformat;

import android.text.InputFilter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class TWPhoneFormat {

    private static List<String> areaCodeList = new ArrayList<String>() {{
        add("02");
        add("03");
        add("04");
        add("05");
        add("06");
        add("07");
        add("08");
    }};

    private static List<String> areaCodeList2 = new ArrayList<String>() {{
        add("037");
        add("049");
        add("082");
        add("089");
        add("0836");
    }};

    private static List<String> areaCodeList3 = new ArrayList<String>() {{
        add("0836");
    }};

    private static List<String> eightDigits = new ArrayList<String>() {{
        add("(02)");
        add("(04)");
    }};

    private static List<String> sevenDigits = new ArrayList<String>() {{
        add("(03)");
        add("(04)7");
        add("(04)8");
        add("(08)");
    }};

    private static List<String> sixDigits = new ArrayList<String>() {{
        add("(037)");
        add("(082)");
        add("(089)");
    }};

    private static List<String> fiveDigits = new ArrayList<String>() {{
        add("(0836)");
    }};

    /**
     * @param s phone number
     * @return formatted phone number
     */
    public static String getFormattedPhone(String s) {

        if (s != null) {
            StringBuilder stringBuilder = new StringBuilder(getNumber(s));

            if (stringBuilder.length() > 1) {

                String phoneCode = "09";
                if (stringBuilder.substring(0, 2).equals(phoneCode)) {
                    if (stringBuilder.length() > 4) {
                        stringBuilder.insert(4, "-");
                    }

                    if (stringBuilder.length() > 8) {
                        stringBuilder.insert(8, "-");
                    }

                } else {
                    boolean isFormat = false;
                    if (stringBuilder.length() > 3) {
                        for (String areaCode : areaCodeList3) {
                            if (stringBuilder.toString().substring(0, 4).equals(areaCode)) {
                                formatAreaCode(stringBuilder, areaCode);
                                isFormat = true;
                                break;
                            }
                        }
                    }

                    if (!isFormat && stringBuilder.length() > 2) {
                        for (String areaCode : areaCodeList2) {
                            if (stringBuilder.toString().substring(0, 3).equals(areaCode)) {
                                formatAreaCode(stringBuilder, areaCode);
                                isFormat = true;
                                break;
                            }
                        }
                    }

                    if (!isFormat) {
                        for (String areaCode : areaCodeList) {
                            if (stringBuilder.toString().substring(0, 2).equals(areaCode)) {
                                formatAreaCode(stringBuilder, areaCode);
                                break;
                            }

                        }
                    }

                    for (String sevenDigit : sevenDigits) {
                        if (addDash(stringBuilder, sevenDigit, 7)) {
                            return stringBuilder.toString();
                        }
                    }

                    for (String eightDigit : eightDigits) {
                        if (addDash(stringBuilder, eightDigit, 8)) {
                            return stringBuilder.toString();
                        }
                    }

                    for (String sixDigit : sixDigits) {
                        if (addDash(stringBuilder, sixDigit, 7)) {
                            return stringBuilder.toString();
                        }
                    }

                    for (String fiveDigit : fiveDigits) {
                        if (addDash(stringBuilder, fiveDigit, 7)) {
                            return stringBuilder.toString();
                        }
                    }
                }
            }

            return stringBuilder.toString();
        }

        return "";

    }

    /**
     * @param editText input field , set the length according to the area code
     * @param s        phone number
     * @return formatted phone number
     */
    public static String getFormattedPhone(EditText editText, String s) {

        StringBuilder stringBuilder = new StringBuilder(getNumber(s));

        if (stringBuilder.length() > 1) {

            String phoneCode = "09";
            if (stringBuilder.substring(0, 2).equals(phoneCode)) {
                if (stringBuilder.length() > 4) {
                    stringBuilder.insert(4, "-");
                }

                if (stringBuilder.length() > 8) {
                    stringBuilder.insert(8, "-");
                }
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
                return stringBuilder.toString();

            } else {
                boolean isFormat = false;
                if (stringBuilder.length() > 3) {
                    for (String areaCode : areaCodeList3) {
                        if (stringBuilder.toString().substring(0, 4).equals(areaCode)) {
                            formatAreaCode(stringBuilder, areaCode);
                            isFormat = true;
                            break;
                        }
                    }
                }

                if (!isFormat && stringBuilder.length() > 2) {
                    for (String areaCode : areaCodeList2) {
                        if (stringBuilder.toString().substring(0, 3).equals(areaCode)) {
                            formatAreaCode(stringBuilder, areaCode);
                            isFormat = true;
                            break;
                        }
                    }
                }

                if (!isFormat) {
                    for (String areaCode : areaCodeList) {
                        if (stringBuilder.toString().substring(0, 2).equals(areaCode)) {
                            formatAreaCode(stringBuilder, areaCode);
                            break;
                        }

                    }
                }

                for (String sevenDigit : sevenDigits) {
                    if (addDash(stringBuilder, sevenDigit, 7, editText, 12)) {
                        return stringBuilder.toString();
                    }
                }

                for (String eightDigit : eightDigits) {
                    if (addDash(stringBuilder, eightDigit, 8, editText, 13)) {
                        return stringBuilder.toString();
                    }
                }

                for (String sixDigit : sixDigits) {
                    if (addDash(stringBuilder, sixDigit, 7, editText, 12)) {
                        return stringBuilder.toString();
                    }
                }

                for (String fiveDigit : fiveDigits) {
                    if (addDash(stringBuilder, fiveDigit, 7, editText, 12)) {
                        return stringBuilder.toString();
                    }
                }
            }
        }

        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        return stringBuilder.toString();
    }

    /**
     * @param s formatted phone number
     * @return phone number without any symbol
     */
    public static String getNumber(String s) {
        s = s.replace("(", "").replace(")", "").replace("-", "");
        return s;
    }

    private static void formatAreaCode(StringBuilder stringBuilder, String areaCode) {
        if (!stringBuilder.toString().contains("(")) {
            stringBuilder.insert(0, "(");
        }
        if (!stringBuilder.toString().contains(")")) {
            stringBuilder.insert(areaCode.length() + 1, ")");
        }
    }

    private static boolean checkArea(StringBuilder stringBuilder, String digit) {
        return stringBuilder.toString().contains(digit);
    }

    private static boolean addDash(StringBuilder stringBuilder, String eightDigit, int index, EditText editText, int length) {
        if (checkArea(stringBuilder, eightDigit)) {
            if (stringBuilder.length() > index) {
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
                stringBuilder.insert(index, "-");
                return true;
            }
            return true;
        }
        return false;
    }

    private static boolean addDash(StringBuilder stringBuilder, String eightDigit, int index) {
        if (checkArea(stringBuilder, eightDigit)) {
            if (stringBuilder.length() > index) {
                stringBuilder.insert(index, "-");
                return true;
            }
            return true;
        }
        return false;
    }
}
