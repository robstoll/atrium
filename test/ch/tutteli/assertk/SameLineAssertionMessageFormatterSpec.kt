package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class SameLineAssertionMessageFormatterSpec : Spek({
    val testee = SameLineAssertionMessageFormatter()
    describe("format") {
        it("is empty string if list is empty") {
            val result = testee.format(listOf())
            assert(result).isEmpty()
        }

        it("writes pair on the same line separated by colon and space") {
            val result = testee.format(listOf("bla" to "bli"))
            assert(result).toBe("bla: bli")
        }

        it("uses the system line separator") {
            val separator = System.getProperty("line.separator")!!
            val result = testee.format(listOf("bla" to "bli", "ble" to "blu"))
            assert(result).toBe("bla: bli${separator}ble: blu")
        }
    }
})

