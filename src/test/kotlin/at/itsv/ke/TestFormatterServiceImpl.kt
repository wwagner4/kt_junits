package at.itsv.ke

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class TestFormatterServiceImpl {

    val formatter = FormatterServiceImpl()

    @CsvSource(
        value = [
            "0.1|0,10",
            "1|1,00",
            "2|2,00",
            "33.234234|33,23",
            "33.235234|33,24",
            "222222.222|222222,22",
            "222222.229|222222,23"],
        delimiter = '|'
    )
    @ParameterizedTest(name = "{0} is formatted to {1}")
    internal fun formatDoubelGerman(input: Double, expected: String) {
        Assertions.assertEquals(expected, formatter.fmtDouble(input, Locale.GERMAN)) {
            "$input formatted GERMAN should equal $expected"
        }
    }

    @CsvSource(
        value = [
            "0.1|0.10",
            "1|1.00",
            "2|2.00",
            "33.234234|33.23",
            "33.235234|33.24",
            "222222.222|222222.22",
            "222222.229|222222.23"],
        delimiter = '|'
    )
    @ParameterizedTest(name = "{0} is formatted to {1}")
    internal fun formatDoubelEn(input: Double, expected: String) {
        Assertions.assertEquals(expected, formatter.fmtDouble(input, Locale.ENGLISH)) {
            "$input formatted EN should equal $expected"
        }
    }

    @CsvSource(
        value = [
            "hallo|hallo",
            "karl |karl",
            " eva|eva",
            "jack in the bottle|jack in the bottle",
            "christmas tree |christmas tree"],
        delimiter = '|'
    )
    @ParameterizedTest(name = "{0} is formatted to {1}")
    internal fun formatString(input: String, expected: String) {
        Assertions.assertEquals(expected, formatter.fmtString(input)) {
            "$input formatted should equal $expected"
        }
    }

    @Test
    fun testStringNull() {
        val v: String? = null
        Assertions.assertEquals("", formatter.fmtString(v))
    }

    @Test
    fun testDateNullEn() {
        val v: Double? = null
        Assertions.assertEquals("", formatter.fmtDouble(v, Locale.ENGLISH))
    }

    @Test
    fun testDateNullGerman() {
        val v: Double? = null
        Assertions.assertEquals("", formatter.fmtDouble(v, Locale.GERMAN))
    }

    @Test
    fun testDateNullDefault() {
        val v: Double? = null
        Assertions.assertEquals("", formatter.fmtDouble(v))
    }

    @CsvSource(
        value = [
            "2020|1|1|01.01.2020",
            "2000|12|24|24.12.2000",
            "1999|4|12|12.04.1999"],
        delimiter = '|'
    )

    @ParameterizedTest(name = "{0}-{1}-{2} is formatted to {3}")
    internal fun formatDate(year: Int, month: Int, day: Int, expected: String) {

        fun dat(year: Int, month: Int, day: Int): Date {
            val ld = LocalDate.of(year, month, day)
            return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant())
        }

        val date = dat(year, month, day)
        Assertions.assertEquals(expected, formatter.fmtDate(date)) {
            "$year $month $day formatted should equal $expected"
        }
    }

}

