package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainSearchBehaviourSamples {

    @Test
    fun ignoringCase() {
        expect("ABC") containsIgnoringCase exactly 1 value "a"
        expect("ABbC") containsIgnoringCase atLeast 2 value "b"

        fails {
            expect("AAAaaa") containsIgnoringCase atMost 3 value "a"
        }
    }
}