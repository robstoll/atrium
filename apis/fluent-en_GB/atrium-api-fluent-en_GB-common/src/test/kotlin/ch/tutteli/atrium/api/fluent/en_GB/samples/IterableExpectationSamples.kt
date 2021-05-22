package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IterableExpectationSamples {
    @Test
    fun toContainBuilder() {
        expect(listOf("A", "B"))
            .toContain
            .inOrder // order specifier
            .only
            .values("A", "B")

        fails {
            expect(listOf(5, 3, 2, 2, 4))
                .toContain
                .inAnyOrder // order specifier
                .atMost(2)
                .entry { // assertion group about the entry
                    toBeGreaterThan(2)
                }
        }
    }

    @Test
    fun notToContainBuilder() {
        expect(listOf("A", "B"))
            .notToContain
            .elementsOf(setOf("C", "D"))

        fails {
            expect(listOf(1, 8, 5))
                .notToContain
                .entry { // assertion group about the entries
                    toBeGreaterThan(6)
                }
        }
    }

    @Test
    fun toContainValues() {
        expect(listOf("A", "B", "C")).toContain("B", "C")

        fails {
            expect(setOf(4, 2, 1)).toContain(3)
        }
    }

    @Test
    fun toContainAssertion() {
        val list = listOf(1, 2, 2, 4)
        expect(list).toContain { toBeGreaterThan(3) }

        fails {
            expect(list).toContain { toBeGreaterThan(4) }
        }
    }

    @Test
    fun toContainAssertions() {
        expect(listOf(1, 2, 2, 4))
            .toContain( // multiple assertion group entries are evaluated independently
                { toBeLessThan(2) },
                { toBeGreaterThan(3) }
            )

        fails {
            expect(listOf(1, 2, 2, 4))
                .toContain(
                    { toEqual(3) }, // fails because no element in the list equals 3
                    { toEqual(5) }  // still evaluated and also fails
                )
        }
    }

    @Test
    fun toContainExactlyValues() {
        expect(listOf(1, 2, 2, 4)).toContainExactly(1, 2, 2, 4)

        fails {
            expect(listOf("A", "B")).toContainExactly("A", "B", "C")
        }

        fails {
            expect(listOf("A", "B")).toContainExactly("B", "A")
        }
    }

    @Test
    fun toContainExactlyAssertion() {
        expect(listOf(4)).toContainExactly {
            toBeLessThan(5)
            toBeGreaterThan(3)
        }

        expect(listOf(null)).toContainExactly(null)

        fails {
            expect(listOf("A", "B")).toContainExactly {
                toEqual("A")
            }
        }

        fails {
            expect(listOf(null, "B")).toContainExactly(null)
        }
    }

    @Test
    fun toContainExactlyAssertions() {
        expect(listOf(3, 5, null)).toContainExactly(
            { toEqual(3) },
            { toBeLessThan(11) },
            null
        )

        fails {
            expect(listOf(3, 5, 7)).toContainExactly(
                { toBeGreaterThan(2) },
                { toBeLessThan(11) }
            )
        }

        fails {
            expect(listOf(3, 5)).toContainExactly(
                { toEqual(1) }, // fails
                { toBeLessThan(11) } // this assertion is not checked
            )
        }
    }

    @Test
    fun toContainExactlyElementsOf() {
        expect(listOf(1, 2, 2, 4)).toContainExactlyElementsOf(listOf(1, 2, 2, 4))

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
    fun notToContain() {
        expect(listOf("A", "B", "C")).notToContain("D", "E")

        fails {
            expect(listOf("A", "B", "C")).notToContain("A", "D")
        }
    }

    @Test
    fun toHaveElementsAndAny() {
        expect(listOf(1, 2, 2, 4)).toHaveElementsAndAny {
            toBeGreaterThan(1)
            toBeLessThan(3)
        }

        fails {
            expect(emptyList<Int>()).toHaveElementsAndAny { toBeLessThan(11) }
        }

        fails {
            expect(listOf(1, 2, 2, 4)).toHaveElementsAndAny {
                toBeGreaterThan(5)
            }
        }
    }

    @Test
    fun toHaveElementsAndNone() {
        expect(listOf(1, 2, 2, 4)).toHaveElementsAndNone {
            toEqual(3)
        }

        fails {
            expect(emptyList<Int>()).toHaveElementsAndNone { toEqual(11) }
        }

        fails {
            expect(listOf(1, 2, 2, 4)).toHaveElementsAndNone {
                toBeLessThanOrEqualTo(1)
            }
        }
    }

    @Test
    fun toHaveElementsAndAll() {
        expect(listOf(1, 2, 2, 4)).toHaveElementsAndAll { toBeGreaterThan(0) }

        fails {
            expect(emptyList<Int>()).toHaveElementsAndAll { toBeGreaterThan(0) }
        }

        fails {
            expect(listOf(1, 2, 2, 4)).toHaveElementsAndNone {
                toBeLessThanOrEqualTo(1)
            }
        }
    }

    @Test
    fun toHaveElements() {
        expect(listOf("A", 1, 3f)).toHaveElements()

        fails {
            expect(emptyList<Int>()).toHaveElements()
        }
    }

    @Test
    fun notToHaveElements() {
        expect(setOf<String>()).notToHaveElements()

        fails {
            expect(listOf("A", "B")).notToHaveElements()
        }
    }

    @Test
    fun notToContainDuplicates() {
        expect(listOf("A", "B")).notToContainDuplicates()

        fails {
            expect(listOf("A", "B", "C", "A")).notToContainDuplicates()
        }
    }
}
