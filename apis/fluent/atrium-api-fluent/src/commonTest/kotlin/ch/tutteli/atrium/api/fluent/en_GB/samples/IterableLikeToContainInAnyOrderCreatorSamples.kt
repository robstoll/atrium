package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInAnyOrderCreatorSamples {
    @Test
    fun elementsOf(){
        expect(listOf("A","B")).toContain.inAnyOrder.exactly(1).elementsOf(listOf("A","B"))
        expect(listOf("A","B","A","B")).toContain.inAnyOrder.atLeast(2).elementsOf(listOf("A","B"))
        expect(listOf("A","B","B")).toContain.inAnyOrder.atMost(2).elementsOf(listOf("A","B"))

        fails {
            expect(listOf("A","B")).toContain.inAnyOrder.exactly(2).elementsOf(listOf("A","B"))
            expect(listOf("A","B","A","B")).toContain.inAnyOrder.atLeast(3).elementsOf(listOf("A","B"))
            expect(listOf("A","B","B","B")).toContain.inAnyOrder.atMost(2).elementsOf(listOf("A","B"))
        }
    }
}
