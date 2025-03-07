package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.atLeast
import ch.tutteli.atrium.api.infix.en_GB.atMost
import ch.tutteli.atrium.api.infix.en_GB.elementsOf
import ch.tutteli.atrium.api.infix.en_GB.entries
import ch.tutteli.atrium.api.infix.en_GB.entry
import ch.tutteli.atrium.api.infix.en_GB.exactly
import ch.tutteli.atrium.api.infix.en_GB.inAny
import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.order
import ch.tutteli.atrium.api.infix.en_GB.the
import ch.tutteli.atrium.api.infix.en_GB.toContain
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.infix.en_GB.value
import ch.tutteli.atrium.api.infix.en_GB.values
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInAnyOrderCreatorSamples {
    @Test
    fun value() {
        expect(listOf("A", "B")) toContain o inAny order exactly 1 value "A"
        expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 2 value ("A")
        expect(listOf("A", "B", "B")) toContain o inAny order atMost 2 value "B"

        fails {
            expect(listOf("A", "B")) toContain o inAny order exactly 2 value "A"
            expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 3 value "A"
            expect(listOf("A", "B", "B", "B")) toContain o inAny order atMost 2 value "B"
        }
    }

    @Test
    fun values() {
        expect(listOf("A", "B")) toContain o inAny order exactly 1 the values("B", "A")
        expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 2 the values("B", "A")
        expect(listOf("A", "B", "B")) toContain o inAny order atMost 2 the values("B", "A")

        fails {
            expect(listOf("A", "B")) toContain o inAny order exactly 2 the values("B", "A")
            expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 3 the values("B", "A")
            expect(listOf("A", "B", "B", "B")) toContain o inAny order atMost 2 the values("B", "A")
        }
    }

    @Test
    fun entry() {
        expect(listOf("A", "B")) toContain o inAny order exactly 1 entry {
            it toEqual "A"
        }

        expect(listOf("A", null, null)) toContain o inAny order exactly 2 entry null // null is identified

        expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 2 entry {
            it toEqual "A"
        }

        expect(listOf("A", "B", "B")) toContain o inAny order atMost 2 entry {
            it toEqual "A"
        }

        fails { // because the count of "A" is not 2
            expect(listOf("A", "B")) toContain o inAny order exactly 2 entry {
                it toEqual "A"
            }
        }

        fails { // because all elements are not null
            expect(listOf("A", "B", "C")) toContain o inAny order exactly 1 entry null
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "A", "B")) toContain o inAny order exactly 1 entry {
                /* do nothing */
            }
        }

        fails { // because the count of "A" is less than 3
            expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 3 entry {
                it toEqual "A"
            }
        }

        fails { // because the count of "B" is more than 2
            expect(listOf("A", "B", "B", "B")) toContain o inAny order atMost 2 entry {
                it toEqual "B"
            }
        }
    }

    @Test
    fun entries() {
        expect(listOf("A", "B")) toContain o inAny order exactly 1 the entries(
            { it toEqual "B" },
            { it toEqual "A" }
        )

        expect(listOf("A", null, "A", null)) toContain o inAny order exactly 2 the entries(
            null,
            { it toEqual "A" }
        )

        expect(listOf(null, null)) toContain o inAny order exactly 2 the entries(
            null
        )

        expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 2 the entries(
            { it toEqual "B" },
            { it toEqual "A" }
        )

        expect(listOf("A", "B", "B")) toContain o inAny order atMost 2 the entries(
            { it toEqual "B" },
            { it toEqual "A" }
        )

        fails { // because the count of "A" is not 2
            expect(listOf("A", "B", "B")) toContain o inAny order exactly 2 the entries(
                { it toEqual "A" },
                { it toEqual "B" }
            )
        }

        fails { // because all elements are not null
            expect(listOf("A", "B", "C")) toContain o inAny order exactly 1 the entries(
                { it toEqual "A" },
                null
            )
        }

        fails { // because otherAssertionCreatorsOrNulls contains a lambda which is non-null and has no expectation
            expect(listOf("A", "B")) toContain o inAny order exactly 1 the entries(
                { it toEqual "A" },
                { /* do nothing */ }
            )
        }

        fails { // because the count of "A" is less than 2
            expect(listOf("A", "B", "B")) toContain o inAny order atLeast 2 the entries(
                { it toEqual "B" },
                { it toEqual "A" }
            )
        }

        fails { // because the count of "B" is more than 2
            expect(listOf("A", "B", "B", "B")) toContain o inAny order atMost 2 the entries(
                { it toEqual "B" },
                { it toEqual "A" }
            )
        }
    }

    @Test
    fun elementsOf() {
        expect(listOf("A", "B")) toContain o inAny order exactly 1 elementsOf listOf("A", "B")
        expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 2 elementsOf listOf("A", "B")
        expect(listOf("A", "B", "B")) toContain o inAny order atMost 2 elementsOf listOf("A", "B")

        fails {
            expect(listOf("A", "B")) toContain o inAny order exactly 2 elementsOf listOf("A", "B")
            expect(listOf("A", "B", "A", "B")) toContain o inAny order atLeast 3 elementsOf listOf("A", "B")
            expect(listOf("A", "B", "B", "B")) toContain o inAny order atMost 2 elementsOf listOf("A", "B")
        }
    }
}
