package org.atriumlib.samples.mpp

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class SampleJvmTest {
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
        // toBeEven is defined in commonTest
        expect(2).toBeEven()
    }
}
