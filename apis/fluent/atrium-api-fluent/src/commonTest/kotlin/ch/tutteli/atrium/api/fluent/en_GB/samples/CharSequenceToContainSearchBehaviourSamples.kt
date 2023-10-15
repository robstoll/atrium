package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainSearchBehaviourSamples {

    @Test
    fun ignoringCase() {
        expect("ABC").toContain.ignoringCase.exactly(1).value("a")
        expect("ABbC").toContain.ignoringCase.atLeast(2).value("b")

        fails {
            expect("AAAaaa").toContain.ignoringCase.atMost(3).value("a")
        }
    }

    @Test
    fun ignoringCaseWithNotChecker() {
        expect("ABC").notToContain.ignoringCase.value("d")

        fails { // because it contains a `d` which is the same as a `D` when case is ignored
            expect("abcd").notToContain.ignoringCase.value("D")
        }
    }
}
