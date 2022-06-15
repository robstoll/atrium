package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.asList
import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.toContain
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IterableSubjectChangersSamples {
    @Test
    fun asListFeature() {
        expect(0..2) asList o toEqual listOf(0, 1, 2)

        fails {
            expect(0..2) asList o toContain 3 toContain 4
        }
    }

    @Test
    fun asList() {
        expect(0..2)
            .asList {
                it toEqual listOf(0, 1, 2)
            }

        fails {
            expect(0..2)
                .asList {
                    it toContain 3
                    it toContain 4
                }
        }
    }
}
