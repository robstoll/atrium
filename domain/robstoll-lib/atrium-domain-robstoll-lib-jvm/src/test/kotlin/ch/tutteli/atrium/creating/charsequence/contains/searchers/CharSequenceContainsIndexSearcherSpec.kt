package ch.tutteli.atrium.creating.charsequence.contains.searchers

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.value
import ch.tutteli.atrium.api.verbs.internal.assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context

object CharSequenceContainsIndexSearcherSpec : Spek({

    context("text 'aaaa'") {
        test("search for 'aa' finds 3 hits since we want non disjoint matches") {
            assert("aaaa").contains.exactly(3).value("aa")
        }
    }

})
