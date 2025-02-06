package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
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
                .entry { // expectation-group about the entry
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
                .entry { // expectation-group about the entry
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
            .toContain( // multiple expectation-group entries are evaluated independently
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

        fails {
            expect(listOf("A", "B")).toContainExactly(
                "C",
                "B",
                // optional
                report = { // allows configuring reporting, e.g.
                    showOnlyFailing() // would not show the successful `B`
                    showOnlyFailingIfMoreExpectedElementsThan(10)
                }
            )
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
                { toEqual(1) },       // fails
                { toBeLessThan(11) }, // succeeds
                // optional
                report = { // allows configuring reporting, e.g.
                    showOnlyFailing() // would not show the successful `toBeLessThan(11)`
                    showOnlyFailingIfMoreExpectedElementsThan(10)
                }
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

        fails {
            expect(listOf(1, 2, 2, 4)).toContainExactlyElementsOf(
                listOf(1, 2, 4),
                // optional
                report = { // allows configuring reporting, e.g.
                    showOnlyFailing() // would not show the successful first and second `1, 2`
                    showOnlyFailingIfMoreExpectedElementsThan(10)
                }
            )
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
    fun toHaveElementsAndAny() {
        expect(listOf(1, 2, 2, 4)).toHaveElementsAndAny {
            toBeGreaterThan(1)
            toBeLessThan(3)
        }
        expect(listOf(null, 2, 3)).toHaveElementsAndAny(null)

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
        expect(listOf(1, 2, 2, 4)).toHaveElementsAndNone { toEqual(3) }
        expect(listOf<Int?>(1, 2)).toHaveElementsAndNone(null)

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
        expect(listOf(null, null)).toHaveElementsAndAll(null)

        fails {
            expect(emptyList<Int>()).toHaveElementsAndAll { toBeGreaterThan(0) }
        }

        fails {
            expect(listOf(1, 2, 2, 4)).toHaveElementsAndAll {
                toBeLessThanOrEqualTo(3)
            }
        }
    }


    @Test
    fun toHaveElementsAndNoDuplicates() {
        expect(listOf("A", "B")).toHaveElementsAndNoDuplicates()

        fails {
            expect(listOf("A", "B", "C", "A")).toHaveElementsAndNoDuplicates()
        }
    }

    @Test
    fun notToHaveElementsOrAny() {
        // passes as the list is empty
        expect(emptyList<Int>()).notToHaveElementsOrAny { toBeGreaterThan(1) }

        // passes as (at least) one element fulfills the sub expectation
        expect(listOf(2, 3, 4)).notToHaveElementsOrAny { toBeLessThan(3) }
        expect(listOf(null, 2, 3)).notToHaveElementsOrAny(null)

        fails {
            expect(listOf(1, 2, 2, 4)).notToHaveElementsOrAny {
                toBeLessThan(1)
            }
        }
    }

    @Test
    fun notToHaveElementsOrAll() {
        // passes as the list is empty
        expect(emptyList<Int>()).notToHaveElementsOrAll { toBeGreaterThan(1) }

        // passes as all elements fulfill the sub expectation
        expect(listOf(2, 3, 4)).notToHaveElementsOrAll { toBeGreaterThan(1) }
        expect(listOf(null, null)).notToHaveElementsOrAll(null)

        fails {
            expect(listOf(1, 2, 2, 4)).notToHaveElementsOrAll {
                toBeLessThanOrEqualTo(3)
            }
        }
    }

    @Test
    fun notToHaveElementsOrNone() {
        // passes as the list is empty
        expect(emptyList<Int>()).notToHaveElementsOrNone { toBeGreaterThan(1) }

        // passes as no element fulfills the sub expectation
        expect(listOf(2, 3, 4)).notToHaveElementsOrNone { toBeGreaterThan(4) }
        expect(listOf<Int?>(2, 3, 4)).notToHaveElementsOrNone(null)

        fails {
            expect(listOf(1, 2, 2, 4)).notToHaveElementsOrNone {
                toBeLessThanOrEqualTo(1)
            }
        }
    }
}
