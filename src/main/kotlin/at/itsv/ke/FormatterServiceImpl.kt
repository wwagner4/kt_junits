package at.itsv.ke

import java.text.SimpleDateFormat
import java.util.*

class FormatterServiceImpl {

    fun f(value: Double?, locale: Locale = Locale.GERMAN): String {
        if (value == null) return ""
        return "%.2f".format(locale, value)
    }

    fun f(value: Date?): String {
        if (value == null) return ""
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        return sdf.format(value)
    }

}