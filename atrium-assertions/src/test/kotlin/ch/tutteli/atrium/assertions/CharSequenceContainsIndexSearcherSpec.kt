package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.exactly
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.value
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context

object CharSequenceContainsIndexSearcherSpec : Spek({

    context("text 'aaaa'") {
        test("search for 'aa' finds 3 hits since we want non disjoint matches") {
            assert("aaaa").contains.exactly(3).value("aa")
        }
    }

})
