package org.atriumlib.samples.mpp

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class SampleJsTest {
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
        // isEven is defined in commonTest
        expect(2).isEven()
    }
}
