package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInOrderOnlyCreatorSamples {

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entries(
            1 to "a", 2 to "b"
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entries(
                2 to "b", // fails because subject does not have the same order
                1 to "a",
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entriesOf(
            mapOf(1 to "a", 2 to "b")
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entriesOf(
                mapOf(
                    2 to "b", // fails because subject does not have the same order
                    1 to "a",
                )
            )
        }
    }
}
