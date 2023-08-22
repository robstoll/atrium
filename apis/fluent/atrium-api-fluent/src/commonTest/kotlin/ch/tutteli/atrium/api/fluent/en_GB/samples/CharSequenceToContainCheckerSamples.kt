package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect("ABBC").toContain.atLeast(2).value("B")

        fails {
            expect("ABB").toContain.atLeast(2).value("A")
        }
    }
}
