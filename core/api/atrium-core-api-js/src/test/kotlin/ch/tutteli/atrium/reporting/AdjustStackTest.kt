package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.cc.infix.en_GB.Values
import ch.tutteli.atrium.api.cc.infix.en_GB.containsNot
import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.api.cc.infix.en_GB.toThrow
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import kotlin.test.Test

class AdjustStackTest {

    @Test
    fun assertionWhichFails_ErrorStackContainsNeitherInitNorMocha() {
        expect {
            assert(1) toBe 2
        }.toThrow<AssertionError> {
            AssertImpl.feature.property(this, {
                @Suppress("UNUSED_VARIABLE")
                val s = subject
                js("s.stack") as String
            }, Untranslatable("prototype.stack")) containsNot Values("init", "mocha")
        }
    }
}
