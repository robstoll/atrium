package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInOrderOnlyCreatorSamples {
    @Test
    fun value() {
        expect(listOf("A")).toContain.inOrder.only.value("A")

        fails { // because the List does not contain expected value
            expect(listOf("B")).toContain.inOrder.only.value("A")
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")).toContain.inOrder.only.value("A")
        }
    }

    @Test
    fun values() {
        expect(listOf("A", "B", "C")).toContain.inOrder.only.values(
            "A", "B", "C"
        )

        fails { // although same elements but not in same order
            expect(listOf("A", "B", "C")).toContain.inOrder.only.values(
                "A", "C", "B"
            )
        }

        fails { // because not all elements found
            expect(listOf("A", "B", "C")).toContain.inOrder.only.values(
                "A", "B"
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")).toContain.inOrder.only.values(
                "A", "B", "C", "D"
            )
        }

        fails { // because order is wrong
            expect(listOf("A", "B")).toContain.inOrder.only.values(
                "B", "A"
            )
        }
    }

    @Test
    fun entry() {
        expect(listOf("A")).toContain.inOrder.only.entry {
            toEqual("A")
        }

        expect(listOf(null)).toContain.inOrder.only.entry(null)

        fails { // because the List does not contain "A"
            expect(listOf("B")).toContain.inOrder.only.entry {
                toEqual("A")
            }
        }

        fails { // because the List does not contain null
            expect(listOf("A")).toContain.inOrder.only.entry(null)
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")).toContain.inOrder.only.entry {
                toEqual("A")
            }
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "C")).toContain.inOrder.only.entry {
                /* do nothing */
            }
        }
    }

    @Test
    fun entries() {
        expect(listOf("A", "B", "C")).toContain.inOrder.only.entries(
            { toEqual("A") },
            { toEqual("B") },
            { toEqual("C") }
        )

        expect(listOf(null, "A", null, "A")).toContain.inOrder.only.entries(
            null,
            { toEqual("A") },
            null,
            { toEqual("A") }
        )

        fails { // because the List contains an additional "A" at the beginning
            expect(listOf("A", "B", "C")).toContain.inOrder.only.entries(
                { toEqual("B") },
                { toEqual("C") }
            )
        }

        fails { // because the List does not contain a "D" at the end
            expect(listOf("A", "B", "C")).toContain.inOrder.only.entries(
                { toEqual("A") },
                { toEqual("B") },
                { toEqual("C") },
                { toEqual("D") }
            )
        }

        fails { // because the List contains a "C" and not null at the end
            expect(listOf("A", "B", "C")).toContain.inOrder.only.entries(
                { toEqual("A") },
                { toEqual("B") },
                null
            )
        }

        fails { // because order is wrong
            expect(listOf("A", "B", "C")).toContain.inOrder.only.entries(
                { toEqual("C") },
                { toEqual("B") },
                { toEqual("A") }
            )
        }

        fails { // because order is wrong
            expect(listOf(null, "A", null)).toContain.inOrder.only.entries(
                null,
                null,
                { toEqual("A") },
            )
        }

        fails { // because otherAssertionCreatorsOrNulls contains a lambda which is non-null and has no expectation
            expect(listOf("A", "B")).toContain.inOrder.only.entries(
                { toEqual("A") },
                { /* do nothing */ }
            )
        }
    }

    @Test
    fun elementsOf() {
        expect(listOf("A", "B", "C")).toContain.inOrder.only.elementsOf(
            listOf("A", "B", "C")
        )

        fails { // although same elements but not in same order
            expect(listOf("A", "B", "C")).toContain.inOrder.only.elementsOf(
                listOf("A", "C", "B")
            )
        }

        fails { // because not all elements found
            expect(listOf("A", "B", "C")).toContain.inOrder.only.elementsOf(
                listOf("A", "B")
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")).toContain.inOrder.only.elementsOf(
                listOf("A", "B", "C", "D")
            )
        }
    }
}
