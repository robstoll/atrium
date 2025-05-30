package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
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
    fun atMost() {
        expect("ABBC").toContain.atMost(2).value("B")

        fails {
            expect("AABBAA").toContain.atMost(3).value("A")
        }
    }

    @Test
    fun notOrAtMost() {
        expect("ABBC").toContain.notOrAtMost(2).value("D")
        expect("ABBC").toContain.notOrAtMost(2).value("B")

        fails {
            expect("AABBAA").toContain.notOrAtMost(3).value("A")
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
