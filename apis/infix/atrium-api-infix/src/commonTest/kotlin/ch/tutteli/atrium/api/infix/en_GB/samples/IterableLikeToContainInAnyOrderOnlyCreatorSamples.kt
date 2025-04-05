package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import ch.tutteli.atrium.api.infix.en_GB.inAny
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.*

class IterableLikeToContainInAnyOrderOnlyCreatorSamples {
    @Test
    fun value() {
        expect(listOf("A")) toContain o inAny order but only value("A")

        fails { // because the List does not contain expected value
            expect(listOf("B")) toContain o inAny order but only value("A")
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")) toContain o inAny order but only value("A")
        }
    }

    @Test
    fun values() {
        expect(listOf("A", "B", "C")) toContain o inAny order but only the values(
            "C", "B", "A"
        )

        fails { // because not all elements found
            expect(listOf("A", "B", "C")) toContain o inAny order but only the values(
                "B", "A"
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")) toContain o inAny order but only the values(
                "D", "C", "B", "A"
            )
        }
    }

    @Test
    fun entry() {
        expect(listOf("A")) toContain o inAny order but only entry {
            toEqual("A")
        }

        expect(listOf(null)) toContain o inAny order but only entry(null)

        fails { // because the List does not contain "A"
            expect(listOf("B")) toContain o inAny order but only entry {
                toEqual("A")
            }
        }

        fails { // because the List does not contain null
            expect(listOf("A")) toContain o inAny order but only entry(null)
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")) toContain o inAny order but only entry {
                toEqual("A")
            }
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "C")) toContain o inAny order but only entry {
                /* do nothing */
            }
        }
    }

    @Test
    fun entries() {
        expect(listOf("A", "B", "C")) toContain o inAny order but only the entries(
            { toEqual("C") },
            { toEqual("B") },
            { toEqual("A") }
        )

        expect(listOf("A", null, "A", null)) toContain o inAny order but only the entries(
            null,
            null,
            { toEqual("A") },
            { toEqual("A") }
        )

        fails { // because the List contains additionally an "A" (not covered in entries)
            expect(listOf("A", "B", "C")) toContain o inAny order but only the entries(
                { toEqual("C") },
                { toEqual("B") }
            )
        }

        fails { // because the List does not contain a "D"
            expect(listOf("A", "B", "C")) toContain o inAny order but only the entries(
                { toEqual("D") },
                { toEqual("C") },
                { toEqual("B") },
                { toEqual("A") }
            )
        }

        fails { // because the List does not contain a null
            expect(listOf("A", "B", "C")) toContain o inAny order but only the entries(
                { toEqual("C") },
                { toEqual("B") },
                null
            )
        }

        fails { // because otherAssertionCreatorsOrNulls contains a lambda which is non-null and has no expectation
            expect(listOf("A", "B")) toContain o inAny order but only the entries(
                { toEqual("A") },
                { /* do nothing */ }
            )
        }
    }

    @Test
    fun elementsOf() {
        expect(listOf("A", "B", "C")) toContain o inAny order but only elementsOf(
            listOf("A", "B", "C")
        )

        fails { // because not all elements found
            expect(listOf("A", "B", "C")) toContain o inAny order but only elementsOf(
                listOf("B", "A")
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")) toContain o inAny order but only elementsOf(
                listOf("B", "A", "C", "D")
            )
        }
    }
}
