package nullsafety

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NullSafetyTest {

    @Test
    fun smartCast() {
        // given
        val name: String? = "Not Null"
        val realName: String

        // when

        // then
        if (name != null) {
            realName = name
            assertNotNull(realName)
        }

    }
}