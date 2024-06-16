package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect("ABBC") toContain o atLeast 2 value "B"

        fails {
            expect("ABB") toContain o atLeast 2 value "A"
        }
    }

    @Test
    fun atMost() {
        expect("ABBC") toContain o atMost 2 value "B"

        fails {
            expect("AABBAA") toContain o atMost 3 value "A"
        }
    }

    @Test
    fun notOrAtMost() {
        expect("ABBC") toContain o notOrAtMost 2 value "D"
        expect("ABBC") toContain o notOrAtMost 2 value "B"

        fails {
            expect("AABBAA") toContain o notOrAtMost 3 value "A"
        }
    }

    @Test
    fun butAtMost() {
        expect("ABBC") toContain o atLeast 1 butAtMost 2 value "B"

        fails {
            expect("ABBBBCD") toContain o atLeast 2 butAtMost 3 value "B"
        }
    }

    @Test
    fun exactly() {
        expect("ABCBAC") toContain o exactly 2 value "C"

        fails {
            expect("ABBBBCD") toContain o exactly 3 value "B"
        }
    }
}
