package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInOrderOnlyCreatorSamples {
    @Test
    fun elementsOf(){
        expect(listOf("A","B","C")).toContain.inOrder.only.elementsOf(
            listOf("A","B","C")
        )

        fails {
            expect(listOf("A","B","C")).toContain.inOrder.only.elementsOf(
                listOf("A","C","B")
            )
        }
    }
}
