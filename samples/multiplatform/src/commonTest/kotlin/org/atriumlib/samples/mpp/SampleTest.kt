package org.atriumlib.samples.mpp

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import kotlin.test.Test

class SampleTest {
    @Test
    fun toBe() {
        expect(1).toBe(1)
    }

    @Test
    fun expectAnExceptionOccurred() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }


    @Test
    fun expectAnExceptionWithAMessageOccurred() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            messageContains("hello")
        }
    }

    @Test
    fun useOwnFunction() {
        expect(2).isEven()
    }
}


fun Expect<Int>.isEven() = createAndAddAssertion(IS, Text("an even number")) { it % 2 == 0 }
