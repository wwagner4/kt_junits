package at.itsv.ke

import org.junit.jupiter.api.Test

class TestDummy {
    
    @Test
    fun testNotOk() {
        throw IllegalStateException("OK")
    }
    
}