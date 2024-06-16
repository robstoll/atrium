package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainSearchBehaviourSamples {

    @Test
    fun ignoringCase() {
        expect("ABC") toContain o ignoring case exactly 1 value "a"
        expect("ABbC") toContain o ignoring case atLeast 2 value "b"

        fails {
            expect("AAAaaa") toContain o ignoring case atMost 3 value "a"
        }
    }

    @Test
    fun ignoringCaseWithNotChecker() {
        expect("ABC") notToContain o ignoring case value "d"

        fails { // because it contains a `d` which is the same as a `D` when case is ignored
            expect("abcd") notToContain o ignoring case value "D"
        }
    }
}
