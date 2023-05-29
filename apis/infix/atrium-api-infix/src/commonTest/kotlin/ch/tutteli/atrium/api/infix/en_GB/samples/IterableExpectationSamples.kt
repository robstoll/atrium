package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class IterableExpectationSamples {
    @Test
    fun toContainBuilder() {
        expect(listOf("A", "B")) toContain o inGiven order and only the values("A", "B")

        fails {
            expect(listOf(5, 3, 2, 2, 4)) toContain o inAny order atMost 2 entry {
                it toBeGreaterThan 2
            }

        }
    }

    @Test
    fun notToContainBuilder() {
        expect(listOf("A", "B")) notToContain o elementsOf setOf("C", "D")

        fails {
            expect(listOf(1, 8, 5)) notToContain o entry {
                it toBeGreaterThan 6
            }
        }
    }

    @Test
    fun toContainValue() {
        expect(listOf("A", "B", "C")) toContain "B"

        fails {
            expect(setOf(4, 2, 1)) toContain 3
        }
    }

    @Test
    fun toContainValues() {
        expect(listOf("A", "B", "C")) toContain values("B", "C")

        fails {
            expect(setOf(4, 2, 1)) toContain values(3, 5)
        }
    }

    @Test
    fun toContainAssertion() {
        val list = listOf(1, 2, 2, 4)
        expect(list) toContain { it toBeGreaterThan 3 }

        fails {
            expect(list) toContain { it toBeGreaterThan 4 }
        }
    }

    @Test
    fun toContainAssertions() {
        expect(listOf(1, 2, 2, 4)) toContain entries(
            // multiple expectation-group entries are evaluated independently
            { it toBeLessThan 2 },
            { it toBeGreaterThan 3 }
        )



        fails {
            expect(listOf(1, 2, 2, 4)) toContain entries(
                { it toEqual 3 }, // fails because no element in the list equals 3
                { it toEqual 5 }  // still evaluated and also fails
            )
        }
    }

    @Test
    fun toContainExactlyValues() {
        expect(listOf(1, 2, 2, 4)) toContainExactly values(1, 2, 2, 4)

        fails {
            expect(listOf("A", "B")) toContainExactly values("A", "B", "C")
        }

        fails {
            expect(listOf("A", "B")) toContainExactly values("B", "A")
        }

        fails {
            expect(listOf("A", "B")) toContainExactly values(
                "C",
                "B",
                // optional
                reportOptionsInOrderOnly = { // allows configuring reporting, e.g.
                    showOnlyFailing() // would not show the successful `B`
                    showOnlyFailingIfMoreExpectedElementsThan(10)
                }
            )
        }
    }

    @Test
    fun toContainExactlyValue() {
        expect(listOf("A")) toContainExactly "A"

        fails {
            expect(listOf("A")) toContainExactly "D"
        }
    }

    @Test
    fun toContainExactlyAssertion() {
        expect(listOf(4)) toContainExactly {
            it toBeLessThan 5
            it toBeGreaterThan 3
        }

        fails {
            expect(listOf("A", "B")) toContainExactly {
                it toEqual "A"
            }
        }

        fails {
            // cast only necessary if Kotlin version < 1.4 due to a bug in Kotlin
            expect(listOf(null, "B")) toContainExactly (null as (Expect<String>.() -> Unit)?)
            // Kotlin > 1.4 would be
            // expect(listOf(null, "B")) toContainExactly null
        }
    }

    @Test
    fun toContainExactlyAssertions() {
        expect(listOf(3, 5, null)) toContainExactly entries(
            { it toEqual 3 },
            { it toBeLessThan 11 },
            null
        )

        fails {
            expect(listOf(3, 5, 7)) toContainExactly entries(
                { it toBeGreaterThan 2 },
                { it toBeLessThan 11 }
            )
        }

        fails {
            expect(listOf(3, 5)) toContainExactly entries(
                { it toEqual 1 },       // fails
                { it toBeLessThan 11 }, // succeeds
                // optional
                reportOptionsInOrderOnly = { // allows configuring reporting, e.g.
                    showOnlyFailing() // would not show the successful `toBeLessThan(11)`
                    showOnlyFailingIfMoreExpectedElementsThan(10)
                }
            )
        }
    }

    @Test
    fun toContainExactlyElementsOf() {
        expect(listOf(1, 2, 2, 4)) toContainExactlyElementsOf listOf(1, 2, 2, 4)

        fails {
            expect(listOf(2, 3, 4)) toContainExactlyElementsOf listOf(2, 3, 4, 1)
        }

        fails {
            expect(listOf(1, 2, 2, 4)) toContainExactlyElementsOf listOf(1, 2, 4)
        }

        fails {
            // alternative form where you can specify a lambda configuring the InOrderOnlyReportingOptions.
            expect(listOf(1, 2, 2, 4)) toContainExactly elementsOf(
                listOf(1, 2, 4),
                reportOptionsInOrderOnly = { // allows configuring reporting, e.g.
                    showOnlyFailing() // would not show the successful first and second `1, 2`
                    showOnlyFailingIfMoreExpectedElementsThan(10)
                }
            )
        }
    }

    @Test
    fun toContainElementsOf() {
        expect(listOf(1, 2, 2, 4)) toContainElementsOf listOf(1, 2, 4)

        fails {
            expect(listOf(1, 2, 2, 4)) toContainElementsOf listOf(1, 2, 3)
        }
    }

    @Test
    fun notToContainValue() {
        expect(listOf("A", "B", "C")) notToContain "D"

        fails {
            expect(listOf("A", "B", "C")) notToContain "A"
        }
    }

    @Test
    fun notToContainValues() {
        expect(listOf("A", "B", "C")) notToContain values("D", "E")

        fails {
            expect(listOf("A", "B", "C")) notToContain values("A", "D")
        }
    }

    @Test
    fun toHaveElements() {
        expect(listOf("A", 1, 3f)) toHave elements

        fails {
            expect(emptyList<Int>()) toHave elements
        }
    }

    @Test
    fun notToHaveElements() {
        expect(setOf<String>()) notToHave elements

        fails {
            expect(listOf("A", "B")) notToHave elements
        }
    }


    @Test
    fun toHaveElementsAndAny() {
        expect(listOf(1, 2, 2, 4)) toHaveElementsAndAny {
            it toBeGreaterThan 1
            it toBeLessThan 3
        }
        expect(listOf(null, 2, 3)) toHaveElementsAndAny null

        fails {
            expect(emptyList<Int>()) toHaveElementsAndAny { it toBeLessThan 11 }
        }

        fails {
            expect(listOf(1, 2, 2, 4)) toHaveElementsAndAny { it toBeGreaterThan 5 }
        }
    }

    @Test
    fun toHaveElementsAndNone() {
        expect(listOf(1, 2, 2, 4)) toHaveElementsAndNone { it toEqual 3 }
        expect(listOf<Int?>(1, 2, 2, 4)) toHaveElementsAndNone null

        fails {
            expect(emptyList<Int>()) toHaveElementsAndNone { it toEqual 11 }
        }

        fails {
            expect(listOf(1, 2, 2, 4)) toHaveElementsAndNone {
                it toBeLessThanOrEqualTo 1
            }
        }
    }

    @Test
    fun toHaveElementsAndAll() {
        expect(listOf(1, 2, 2, 4)) toHaveElementsAndAll { it toBeGreaterThan 0 }
        expect(listOf(null, null)) toHaveElementsAndAll null

        fails {
            expect(emptyList<Int>()) toHaveElementsAndAll { it toBeGreaterThan 0 }
        }

        fails {
            expect(listOf(1, 2, 2, 4)) toHaveElementsAndAll {
                it toBeLessThanOrEqualTo 3
            }
        }
    }


    @Test
    fun toHaveElementsAndNoDuplicates() {
        expect(listOf("A", "B")) toHaveElementsAnd noDuplicates

        fails {
            expect(listOf("A", "B", "C", "A")) toHaveElementsAnd noDuplicates
        }
    }

    @Test
    fun notToHaveElementsOrAny() {
        // passes as the list is empty
        expect(emptyList<Int>()) notToHaveElementsOrAny { it toBeGreaterThan 1 }

        // passes as (at least) one element fulfills the sub expectation
        expect(listOf(2, 3, 4)) notToHaveElementsOrAny { it toBeLessThan 3 }
        expect(listOf(null, 2, 3)) notToHaveElementsOrAny null

        fails {
            expect(listOf(1, 2, 2, 4)) notToHaveElementsOrAny {
                it toBeLessThan 1
            }
        }
    }

    @Test
    fun notToHaveElementsOrAll() {
        // passes as the list is empty
        expect(emptyList<Int>()) notToHaveElementsOrAll { it toBeGreaterThan 1 }

        // passes as all elements fulfill the sub expectation
        expect(listOf(2, 3, 4)) notToHaveElementsOrAll { it toBeGreaterThan 1 }
        expect(listOf(null, null)) notToHaveElementsOrAll null

        fails {
            expect(listOf(1, 2, 2, 4)) notToHaveElementsOrAll {
                it toBeLessThanOrEqualTo 3
            }
        }
    }

    @Test
    fun notToHaveElementsOrNone() {
        // passes as the list is empty
        expect(emptyList<Int>()) notToHaveElementsOrNone { it toBeGreaterThan 1 }

        // passes as no element fulfills the sub expectation
        expect(listOf(2, 3, 4)) notToHaveElementsOrNone { it toBeGreaterThan 4 }
        expect(listOf<Int?>(2, 3, 4)) notToHaveElementsOrNone null

        fails {
            expect(listOf(1, 2, 2, 4)) notToHaveElementsOrNone {
                it toBeLessThanOrEqualTo 1
            }
        }
    }
}
