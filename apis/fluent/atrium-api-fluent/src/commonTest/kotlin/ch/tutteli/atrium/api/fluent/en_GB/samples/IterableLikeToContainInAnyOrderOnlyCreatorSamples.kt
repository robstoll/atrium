package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInAnyOrderOnlyCreatorSamples {
    @Test
    fun value() {
        expect(listOf("A")).toContain.inAnyOrder.only.value("A")

        fails { // because the List does not contain expected value
            expect(listOf("B")).toContain.inAnyOrder.only.value("A")
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")).toContain.inAnyOrder.only.value("A")
        }
    }

    @Test
    fun values() {
        expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.values(
            "C", "B", "A"
        )

        fails { // because not all elements found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.values(
                "B", "A"
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.values(
                "D", "C", "B", "A"
            )
        }
    }

    @Test
    fun entry() {
        expect(listOf("A")).toContain.inAnyOrder.only.entry {
            toEqual("A")
        }

        expect(listOf(null)).toContain.inAnyOrder.only.entry(null)

        fails { // because the List does not contain "A"
            expect(listOf("B")).toContain.inAnyOrder.only.entry {
                toEqual("A")
            }
        }

        fails { // because the List does not contain null
            expect(listOf("A")).toContain.inAnyOrder.only.entry(null)
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")).toContain.inAnyOrder.only.entry {
                toEqual("A")
            }
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.entry {
                /* do nothing */
            }
        }
    }

    @Test
    fun entries() {
        expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.entries(
            { toEqual("C") },
            { toEqual("B") },
            { toEqual("A") }
        )

        expect(listOf("A", null, "A", null)).toContain.inAnyOrder.only.entries(
            null,
            null,
            { toEqual("A") },
            { toEqual("A") }
        )

        fails { // because the List contains additionally an "A" (not covered in entries)
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.entries(
                { toEqual("C") },
                { toEqual("B") }
            )
        }

        fails { // because the List does not contain a "D"
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.entries(
                { toEqual("D") },
                { toEqual("C") },
                { toEqual("B") },
                { toEqual("A") }
            )
        }

        fails { // because the List does not contain a null that is in entries
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.entries(
                { toEqual("C") },
                { toEqual("B") },
                null
            )
        }

        fails { // because otherAssertionCreatorsOrNulls contains a lambda which is non-null and has no expectation
            expect(listOf("A", "B")).toContain.inAnyOrder.only.entries(
                { toEqual("A") },
                { /* do nothing */ }
            )
        }
    }

    @Test
    fun elementsOf() {
        expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.elementsOf(
            listOf("A", "B", "C")
        )

        fails { // because not all elements found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.elementsOf(
                listOf("B", "A")
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.elementsOf(
                listOf("B", "A", "C", "D")
            )
        }
    }
}
