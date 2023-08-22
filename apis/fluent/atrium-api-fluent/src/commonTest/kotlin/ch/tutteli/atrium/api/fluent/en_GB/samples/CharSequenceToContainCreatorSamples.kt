package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCreatorSamples {

    @Test
    fun value() {
        // `value` is a final step in the CharSequence.toContain building process and can be used with
        // various checkers (see CharSequenceToContainCheckerSamples) and search behaviours
        // (see CharSequenceToContainSearchBehaviourSamples)

        expect("ABC").toContain.exactly(1).value("A")
        expect("ABBBC").toContain.atLeast(2).value("B")

        fails {
            expect("AAAAAA").toContain.atMost(3).value("A")
        }
    }
}
