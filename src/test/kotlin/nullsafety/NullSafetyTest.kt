package nullsafety

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NullSafetyTest {

    @Test
    fun smartCast() {
        // given
        val name: String? = "Not Null"
        val realName: String

        // then
        if (name != null) {
            realName = name
            assertNotNull(realName)
        }

    }

    @Test
    fun smartCast_not_working() {
        // given
        class Person(var name: String?)
        var person = Person(null)
        val length: Int

        // then
        if (person.name != null) {
//            length = person.name.length ** Cannot Call **
            length = person.name!!.length // not null assertion operator
            assertNotNull(length)
        }

        person.name?.let {
            val safeCallLength = it.length
            assertNotNull(safeCallLength)
        }
    }
}