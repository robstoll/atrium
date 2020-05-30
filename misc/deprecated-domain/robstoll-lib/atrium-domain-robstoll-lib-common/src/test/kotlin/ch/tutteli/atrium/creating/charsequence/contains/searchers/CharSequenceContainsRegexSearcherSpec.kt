package ch.tutteli.atrium.creating.charsequence.contains.searchers

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.api.verbs.internal.expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CharSequenceContainsRegexSearcherSpec : Spek({

    describe("text 'aaaa'") {
        it("search for 'aa' finds 3 hits since we want non disjoint matches") {
            expect("aaaa").contains.exactly(3).regex("aa")
        }
        it("search for 'aa?' finds 4 hits since we want non disjoint matches") {
            expect("aaaa").contains.exactly(4).regex("aa?")
        }
    }

})
