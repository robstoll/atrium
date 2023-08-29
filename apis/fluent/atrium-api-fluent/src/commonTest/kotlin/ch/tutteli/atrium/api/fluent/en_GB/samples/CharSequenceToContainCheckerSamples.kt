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

    @Test
    fun butAtMost() {
        expect("ABBC").toContain.atLeast(1).butAtMost(2).value("B")

        fails {
            expect("ABBBBCD").toContain.atLeast(2).butAtMost(3).value("B")
        }
    }

    @Test
    fun exactly() {
        expect("ABCBAC").toContain.exactly(2).value("C")

        fails {
            expect("ABBBBCD").toContain.exactly(3).value("B")
        }
    }
}
