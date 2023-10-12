package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInAnyOrderCreatorSamples {

    @Test
    fun entriesOf() {
        expect(
            mapOf(
                1 to "a",
                2 to "b",
            )
        )
            .toContain
            .inAnyOrder
            .entriesOf(
                mapOf(
                    2 to "b",
                )
            )


        fails {
            expect(
                mapOf(
                    1 to "a",
                    2 to "b",
                )
            )
                .toContain
                .inAnyOrder
                .entriesOf(
                    mapOf(
                        1 to "b", // fails because subject does not have this entry
                    )
                )
        }
    }
}
