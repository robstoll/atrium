package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.messageToContain
import ch.tutteli.atrium.api.infix.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import io.kotest.core.spec.style.FunSpec

class TextSpec : FunSpec({
    test("empty string; throws IllegalArgumentException") {
        expect {
            Text("")
        }.toThrow<IllegalArgumentException> {
            messageToContain("use Text.Empty instead")
        }
    }

    test("blank string; does not throw") {
        Text("  ")
    }
})
