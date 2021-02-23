package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.messageContains
import ch.tutteli.atrium.api.infix.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TextSpec : Spek({
    describe("creating a Text") {
        it("empty string; throws IllegalArgumentException") {
            expect{
                Text("")
            }.toThrow<IllegalArgumentException> {
                messageContains("use Text.Empty instead")
            }
        }

        it("blank string; does not throw") {
            Text("  ")
        }
    }
})
