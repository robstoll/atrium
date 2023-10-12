package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInAnyOrderOnlyCreatorSamples {

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entries(
            2 to "b", 1 to "a"
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entries(
                1 to "a" // fails because subject has additional entries
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entriesOf(
            mapOf(2 to "b", 1 to "a")
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entriesOf(
                mapOf(1 to "a") // fails because subject has additional entries
            )
        }
    }
}
