package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInAnyOrderCreatorSamples {
    @Test
    fun value() {
        expect(listOf("A", "B")).toContain.inAnyOrder.exactly(1).value("A")
        expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(2).value("A")
        expect(listOf("A", "B", "B")).toContain.inAnyOrder.atMost(2).value("B")

        fails {
            expect(listOf("A", "B")).toContain.inAnyOrder.exactly(2).value("A")
            expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(3).value("A")
            expect(listOf("A", "B", "B", "B")).toContain.inAnyOrder.atMost(2).value("B")
        }
    }

    @Test
    fun values() {
        expect(listOf("A", "B")).toContain.inAnyOrder.exactly(1).values("B", "A")
        expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(2).values("B", "A")
        expect(listOf("A", "B", "B")).toContain.inAnyOrder.atMost(2).values("B", "A")

        fails {
            expect(listOf("A", "B")).toContain.inAnyOrder.exactly(2).values("B", "A")
            expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(3).values("B", "A")
            expect(listOf("A", "B", "B", "B")).toContain.inAnyOrder.atMost(2).values("B", "A")
        }
    }

    @Test
    fun entry() {
        expect(listOf("A", "B")).toContain.inAnyOrder.exactly(1).entry {
            toEqual("A")
        }

        expect(listOf("A", null, null)).toContain.inAnyOrder.exactly(2).entry(null) // null is identified

        expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(2).entry {
            toEqual("A")
        }

        expect(listOf("A", "B", "B")).toContain.inAnyOrder.atMost(2).entry {
            toEqual("A")
        }

        fails { // because the count of "A" is not 2
            expect(listOf("A", "B")).toContain.inAnyOrder.exactly(2).entry {
                toEqual("A")
            }
        }

        fails { // because all elements are not null
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.exactly(1).entry(null)
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.exactly(1).entry {
                /* do nothing */
            }
        }

        fails { // because the count of "A" is less than 3
            expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(3).entry {
                toEqual("A")
            }
        }

        fails { // because the count of "B" is more than 2
            expect(listOf("A", "B", "B", "B")).toContain.inAnyOrder.atMost(2).entry {
                toEqual("B")
            }
        }
    }

    @Test
    fun elementsOf() {
        expect(listOf("A", "B")).toContain.inAnyOrder.exactly(1).elementsOf(listOf("A", "B"))
        expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(2).elementsOf(listOf("A", "B"))
        expect(listOf("A", "B", "B")).toContain.inAnyOrder.atMost(2).elementsOf(listOf("A", "B"))

        fails {
            expect(listOf("A", "B")).toContain.inAnyOrder.exactly(2).elementsOf(listOf("A", "B"))
            expect(listOf("A", "B", "A", "B")).toContain.inAnyOrder.atLeast(3).elementsOf(listOf("A", "B"))
            expect(listOf("A", "B", "B", "B")).toContain.inAnyOrder.atMost(2).elementsOf(listOf("A", "B"))
        }
    }
}
