package at.itsv.ke

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TestDummy {

    fun squared(value: Double): Double {
        return value * value
    }

    @Test
    fun testSquared() {
        assertEquals(4.0, squared(2.0))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "0|0",
            "1|1",
            "2|4",
            "3.0|9.0",
            "2.222|4.937284"],
        delimiter = '|'
    )
    @DisplayName("value {0} squared = {1}")
    internal fun add(first: Double, expectedResult: Double) {
        assertEquals(expectedResult, squared(first)) {
            "$first squared should equal $expectedResult"
        }
    }


}