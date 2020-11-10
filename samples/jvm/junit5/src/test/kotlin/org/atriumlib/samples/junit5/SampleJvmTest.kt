package org.atriumlib.samples.junit5

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import org.junit.jupiter.api.Test

class SampleJvmTest {
    @Test
    fun `to be`() {
        expect(1).toBe(1)
    }

    @Test
    fun `expect an exception occurred`() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }


    @Test
    fun `expect an exception with a message occurred`() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            messageContains("hello")
        }
    }

    @Test
    fun `use own function`() {
        expect(2).isEven()
    }
}

fun Expect<Int>.isEven() = createAndAddAssertion(IS, Text("an even number")) { it % 2 == 0 }

