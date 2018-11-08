package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.cc.infix.en_GB.*
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.core.polyfills.stack
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class AdjustStackSpec : Spek({

    describe("assertion which fails") {
        it("does not contain spek nor junit in stack") {
            expect {
                assert(1) toBe 2
            }.toThrow<AssertionError> {
                property(subject::stack) notTo contain the Entries({ this contains "spek" }, { this contains "junit" })
            }
        }
    }
})
