package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.messageToContain
import ch.tutteli.atrium.api.infix.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import io.kotest.core.spec.style.DescribeSpec

class TextSpec : DescribeSpec({
    describe("creating a Text") {
        it("empty string; throws IllegalArgumentException") {
            expect{
                Text("")
            }.toThrow<IllegalArgumentException> {
                messageToContain("use Text.Empty instead")
            }
        }

        it("blank string; does not throw") {
            Text("  ")
        }
    }
})
