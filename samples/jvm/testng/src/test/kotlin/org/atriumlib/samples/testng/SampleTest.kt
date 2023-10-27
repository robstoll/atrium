package org.atriumlib.samples.testng

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.reporting.Text
import org.testng.annotations.Test

internal class SampleTest {

    @Test
    fun `to equal`() {
        val result = 1 + 1
        expect(result).toEqual(2)
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
            messageToContain("hello")
        }
    }

    @Test
    fun `use own function`() {
        expect(2).toBeEven()
    }
}

fun Expect<Int>.toBeEven() =
    _logic.createAndAppend(TO_BE, Text("an even number")) { it % 2 == 0 }
