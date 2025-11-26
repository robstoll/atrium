package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.messageToContain
import ch.tutteli.atrium.api.infix.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.Test

class TextSpec {
    @Test
    fun empty_string_throws() {
        expect {
            Text("")
        }.toThrow<IllegalArgumentException> {
            messageToContain("use Text.Empty instead")
        }
    }

    @Test
    fun blank_string_does_not_throw() {
        Text("  ")
    }
}
