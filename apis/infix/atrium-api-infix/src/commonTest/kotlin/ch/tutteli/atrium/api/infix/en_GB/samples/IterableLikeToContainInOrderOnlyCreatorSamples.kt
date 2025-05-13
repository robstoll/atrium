package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.and
import ch.tutteli.atrium.api.infix.en_GB.elementsOf
import ch.tutteli.atrium.api.infix.en_GB.entries
import ch.tutteli.atrium.api.infix.en_GB.entry
import ch.tutteli.atrium.api.infix.en_GB.inGiven
import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.only
import ch.tutteli.atrium.api.infix.en_GB.order
import ch.tutteli.atrium.api.infix.en_GB.the
import ch.tutteli.atrium.api.infix.en_GB.toContain
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.infix.en_GB.value
import ch.tutteli.atrium.api.infix.en_GB.values
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInOrderOnlyCreatorSamples {
    @Test
    fun value() {
        expect(listOf("A")) toContain o inGiven order and only value "A"

        fails { // because the List does not contain expected value
            expect(listOf("B")) toContain o inGiven order and only value "A"
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")) toContain o inGiven order and only value "A"
        }
    }

    @Test
    fun values() {
        expect(listOf("A", "B", "C")) toContain o inGiven order and only the values("A", "B", "C")

        fails { // although same elements but not in same order
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the values("A", "C", "B")
        }

        fails { // because not all elements found
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the values("A", "B")
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the values("A", "B", "C", "D")
        }

        fails { // because order is wrong
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the values("B", "A")
        }
    }


    @Test
    fun entry() {
        expect(listOf("A")) toContain o inGiven order and only entry {
            it toEqual "A"
        }

        expect(listOf(null)) toContain o inGiven order and only entry null

        fails { // because the List does not contain "A"
            expect(listOf("B")) toContain o inGiven order and only entry {
                it toEqual "A"
            }
        }

        fails { // because the List does not contain null
            expect(listOf("A")) toContain o inGiven order and only entry null
        }

        fails { // because the List contains multiple elements
            expect(listOf("A", "A")) toContain o inGiven order and only entry {
                it toEqual "A"
            }
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "C")) toContain o inGiven order and only entry {
                /* do nothing */
            }
        }
    }

    @Test
    fun entries() {
        expect(listOf("A", "B", "C")) toContain o inGiven order and only the entries(
            { it toEqual "A" },
            { it toEqual "B" },
            { it toEqual "C" })

        expect(listOf(null, "A", null, "A")) toContain o inGiven order and only the entries(
            null,
            { it toEqual "A" },
            null,
            { it toEqual "A" })

        fails { // because the List contains an additional "A" at the beginning
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the entries(
                { it toEqual "B" },
                { it toEqual "C" })
        }

        fails { // because the List does not contain a "D" at the end
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the entries(
                { it toEqual "A" },
                { it toEqual "B" },
                { it toEqual "C" },
                { it toEqual "D" })
        }

        fails { // because the List contains a "C" and not null at the end
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the entries(
                { it toEqual "A" },
                { it toEqual "B" },
                null
            )
        }

        fails { // because order is wrong
            expect(listOf("A", "B", "C")) toContain o inGiven order and only the entries(
                { it toEqual "C" },
                { it toEqual "B" },
                { it toEqual "A" })
        }

        fails { // because order is wrong
            expect(listOf(null, "A", null)) toContain o inGiven order and only the entries(
                null,
                null,
                { it toEqual "A" },
            )
        }

        fails { // because otherAssertionCreatorsOrNulls contains a lambda which is non-null and has no expectation
            expect(listOf("A", "B")) toContain o inGiven order and only the entries(
                { it toEqual "A" },
                { /* do nothing */ })
        }
    }

    @Test
    fun elementsOf() {
        expect(listOf("A", "B", "C")) toContain o inGiven order and only elementsOf (listOf("A", "B", "C"))


        fails { // although same elements but not in same order
            expect(listOf("A", "B", "C")) toContain o inGiven order and only elementsOf (listOf("A", "C", "B"))
        }

        fails { // because not all elements found
            expect(listOf("A", "B", "C")) toContain o inGiven order and only elementsOf (listOf("A", "B"))
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")) toContain o inGiven order and only elementsOf (listOf("A", "B", "C", "D"))
        }
    }
}
