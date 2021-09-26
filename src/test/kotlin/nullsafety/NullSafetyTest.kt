package nullsafety

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

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

    @Test
    fun elvisOperator() {
        // given
        class Person(val name: String?)

        val elvis = Person("elvis")
        val presley = Person(null)

        // when
        val notNullName = elvis.name ?: "really?"
        val defaultName = presley.name ?: "default"

        // then
        assertAll (
            Executable { assertEquals(notNullName, elvis.name) },
            Executable { assertEquals(defaultName, "default") }
        )

    }
}