package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInAnyOrderOnlyCreatorSamples {
    @Test
    fun elementsOf(){
        expect(listOf("A","B","C")).toContain.inAnyOrder.only.elementsOf(
            listOf("A","B","C")
        )

        fails {
            expect(listOf("A","B","C")).toContain.inAnyOrder.only.elementsOf(
                listOf("A","B")
            )
        }
    }
}
