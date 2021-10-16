package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IterableExpectationSamples {
    @Test
    fun toContainBuilder() {
        expect(listOf("A", "B")) toContain o inGiven order and only the values("A", "B")

        fails {
            expect(listOf(5, 3, 2, 2, 4)) toContain o inAny order atMost 2 entry {
                toBeGreaterThan(2)
            }

        }
    }

    @Test
    fun notToContainBuilder() {
        expect(listOf("A", "B")) notToContain o elementsOf setOf("C", "D")

        fails {
            expect(listOf(1, 8, 5)) notToContain o entry {
                toBeGreaterThan(6)
            }
        }
    }

    @Test
    fun toContainValue() {
        expect(listOf("A", "B", "C")) toContain value("B")

        fails {
            expect(setOf(4, 2, 1)) toContain value(3)
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
        expect(list) toContain {
            toBeGreaterThan(3)
        }

        fails {
            expect(list) toContain {
                toBeGreaterThan(4)
            }
        }
    }

    @Test
    fun toContainAssertions() {
        expect(listOf(1, 2, 2, 4)) toContain entries(
            // multiple assertion group entries are evaluated independently
            { toBeLessThan(2) },
            { toBeGreaterThan(3) }
        )



        fails {
            expect(listOf(1, 2, 2, 4)) toContain entries(
                    { toEqual(3) }, // fails because no element in the list equals 3
                    { toEqual(5) }  // still evaluated and also fails
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
    }

    @Test
    fun toContainExactlyValue() {
        expect(listOf("A")) toContainExactly value("A")

        fails {
            expect(listOf("A")) toContainExactly value("D")
        }
    }

    @Test
    fun toContainExactlyAssertion() {
        expect(listOf(4)) toContainExactly {
            toBeLessThan(5)
            toBeGreaterThan(3)
        }

        fails {
            expect(listOf("A", "B")) toContainExactly {
                toEqual("A")
            }
        }

        fails {
            expect(listOf(null, "B")) toContainExactly value(null)
        }
    }

    @Test
    fun toContainExactlyAssertions() {
        expect(listOf(3, 5, null)) toContainExactly entries(
            { toEqual(3) },
            { toBeLessThan(11) },
            null
        )

        fails {
            expect(listOf(3, 5, 7)) toContainExactly entries(
                { toBeGreaterThan(2) },
                { toBeLessThan(11) }
            )
        }

        fails {
            expect(listOf(3, 5)) toContainExactly entries(
                { toEqual(1) },      // fails
                { toBeLessThan(11) } // this assertion is not checked
            )
        }
    }

    @Test
    fun toContainExactlyElementsOf() {
        expect(listOf(1, 2, 2, 4)) toContainExactlyElementsOf listOf(1, 2, 2, 4)

        fails {
            expect(listOf(2, 3, 4)).toContainExactlyElementsOf(listOf(2, 3, 4, 1))
        }

        fails {
            expect(listOf(1, 2, 2, 4)).toContainExactlyElementsOf(listOf(1, 2, 4))
        }
    }

    @Test
    fun toContainElementsOf() {
        expect(listOf(1, 2, 2, 4)).toContainElementsOf(listOf(1, 2, 4))

        fails {
            expect(listOf(1, 2, 2, 4)).toContainElementsOf(listOf(1, 2, 3))
        }
    }

    @Test
    fun notToContainValue() {
        expect(listOf("A", "B", "C")) notToContain value("D")

        fails {
            expect(listOf("A", "B", "C")) notToContain value("A")
        }
    }

    @Test
    fun notToContain() {
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
            toBeGreaterThan(1)
            toBeLessThan(3)
        }

        fails {
            expect(emptyList<Int>()) toHaveElementsAndAny { toBeLessThan(11) }
        }

        fails {
            expect(listOf(1, 2, 2, 4)) toHaveElementsAndAny {
                toBeGreaterThan(5)
            }
        }
    }

    @Test
    fun toHaveElementsAndNone() {
        expect(listOf(1, 2, 2, 4)) toHaveElementsAndNone {
            toEqual(3)
        }

        fails {
            expect(emptyList<Int>()) toHaveElementsAndNone { toEqual(11) }
        }

        fails {
            expect(listOf(1, 2, 2, 4)) toHaveElementsAndNone {
                toBeLessThanOrEqualTo(1)
            }
        }
    }

    @Test
    fun toHaveElementsAndAll() {
        expect(listOf(1, 2, 2, 4)) toHaveElementsAndAll { toBeGreaterThan(0) }

        fails {
            expect(emptyList<Int>()) toHaveElementsAndAll { toBeGreaterThan(0) }
        }

        fails {
            expect(listOf(1, 2, 2, 4)) toHaveElementsAndNone {
                toBeLessThanOrEqualTo(1)
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
}
