package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assert
import ch.tutteli.atrium.builders.charsequence.contains.exactly
import ch.tutteli.atrium.builders.charsequence.contains.value
import ch.tutteli.atrium.contains
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context

object CharSequenceContainsIndexSearcherSpec : Spek({

    context("text 'aaaa'") {
        test("search for 'aa' finds 3 hits since we want non disjoint matches") {
            assert("aaaa").contains.exactly(3).value("aa")
        }
    }

})
