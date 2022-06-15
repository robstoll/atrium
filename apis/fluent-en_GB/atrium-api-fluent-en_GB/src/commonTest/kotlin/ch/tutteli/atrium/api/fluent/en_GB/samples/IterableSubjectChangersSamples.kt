package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.asList
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IterableSubjectChangersSamples {
    @Test
    fun asListFeature() {
        expect(0..2)
            .asList()
            .toEqual(listOf(0, 1, 2))

        fails {
            expect(0..2)
                .asList()
                .toContain(3)
                .toContain(4)
        }
    }

    @Test
    fun asList() {
        expect(0..2)
            .asList {
                toEqual(listOf(0, 1, 2))
            }

        fails {
            expect(0..2)
                .asList {
                    toContain(3)
                    toContain(4)
                }
        }
    }
}
