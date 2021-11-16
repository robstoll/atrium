package org.atriumlib.samples.mpp

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import kotlin.test.Test

class SampleTest {
    @Test
    fun toEqual() {
        expect(1).toEqual(1)
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
            messageToContain("hello")
        }
    }

    @Test
    fun useOwnFunction() {
        expect(2).toBeEven()
    }
}


fun Expect<Int>.toBeEven() = createAndAddAssertion(IS, Text("an even number")) { it % 2 == 0 }
