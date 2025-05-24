package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class IterableLikeToContainInOrderOnlyGroupedCreatorSamples {

    // Helper functions to create Group instances for values
    private fun <T> groupOf(vararg elements: T): Group<T> = object : Group<T> {
        override fun toList(): List<T> = elements.toList()
    }

    // Helper functions to create Group instances for entries (expectation lambdas)
    private fun <T> groupOfEntries(vararg elements: (Expect<T>.() -> Unit)?): Group<(Expect<T>.() -> Unit)?> = object : Group<(Expect<T>.() -> Unit)?> {
        override fun toList(): List<(Expect<T>.() -> Unit)?> = elements.toList()
    }

    @Test
    fun inAnyOrderWithValues() {
        // Groups must appear in order, but within each group elements can be in any order
        expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A", "B"),        // first group: A and B (in any order)
            groupOf("C", "D")         // second group: C and D (in any order)
        )

        // This also works - same groups, different internal order
        expect(listOf("B", "A", "D", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A", "B"),        // first group: A and B (in any order)
            groupOf("C", "D")         // second group: C and D (in any order)
        )

        // Single element groups
        expect(listOf("A", "B", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A"),             // first group: single value A
            groupOf("B", "C")         // second group: B and C (in any order)
        )

        fails { // because groups are in wrong order
            expect(listOf("C", "D", "A", "B")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOf("A", "B"),    // first group should come first
                groupOf("C", "D")     // second group should come second
            )
        }

        fails { // because not all elements are present
            expect(listOf("A", "B", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOf("A", "B"),
                groupOf("C", "D")     // D is missing from the list
            )
        }

        fails { // because there are extra elements
            expect(listOf("A", "B", "C", "D", "E")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOf("A", "B"),
                groupOf("C", "D")     // E is not expected
            )
        }
    }

    @Test
    fun inAnyOrderWithEntries() {
        // Using expectation lambdas for more complex assertions
        expect(listOf(1, 2, 10, 20)).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOfEntries<Int>({ toBeGreaterThan(0) }, { toBeLessThan(5) }),      // first group: numbers > 0 and < 5
            groupOfEntries<Int>({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })   // second group: numbers > 5
        )

        // Mixed with null values
        expect(listOf("A", null, "B", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOfEntries<String>({ toEqual("A") }, null),                       // first group: "A" and null
            groupOfEntries<String>({ toEqual("B") }, { toEqual("C") })           // second group: "B" and "C"
        )

        // Single entry in a group
        expect(listOf(1, 10, 20)).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOfEntries<Int>({ toBeLessThan(5) }),                             // first group: single entry < 5
            groupOfEntries<Int>({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })  // second group: numbers > 5
        )

        fails { // because one element doesn't match the expectation
            expect(listOf(1, 2, 10, 4)).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOfEntries<Int>({ toBeLessThan(5) }, { toBeLessThan(5) }),        // 1 and 2 are < 5 ✓
                groupOfEntries<Int>({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })  // 10 is > 5 ✓, but 4 is not > 5 ✗
            )
        }

        fails { // because groups are mixed together
            expect(listOf(1, 10, 2, 20)).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOfEntries<Int>({ toBeLessThan(5) }, { toBeLessThan(5) }),        // should be first group
                groupOfEntries<Int>({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })  // should be second group
            )
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOfEntries<String>({ toEqual("A") }, { /* do nothing */ }),      // empty expectation block
                groupOfEntries<String>({ toEqual("C") }, { toEqual("D") })
            )
        }
    }

    @Test
    fun inAnyOrderWithReportingOptions() {
        // Using reporting options to customize failure messages
        expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A", "B"),
            groupOf("C", "D"),
            report = {
                showOnlyFailing()  // only show failing elements in report
            },
            reportInGroup = {
                showOnlyFailingIfMoreExpectedElementsThan(5)  // customize in-group reporting
            }
        )

        fails {
            expect(listOf("A", "B", "X", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOf("A", "B"),
                groupOf("C", "D"),   // C is missing, X is unexpected
                report = {
                    showOnlyFailing()
                },
                reportInGroup = {
                    showOnlyFailingIfMoreExpectedElementsThan(1)
                }
            )
        }
    }

    @Test
    fun inAnyOrderMultipleGroups() {
        // Testing with more than two groups
        expect(listOf("A", "B", "C", "D", "E", "F")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A", "B"),        // first group
            groupOf("C", "D"),        // second group
            groupOf("E", "F")         // third group
        )

        // Groups can have different sizes
        expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A"),             // single element group
            groupOf("B", "C", "D")    // three element group
        )

        // Mixed single and multiple element groups
        expect(listOf("A", "B", "C", "D", "E")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A"),             // single value
            groupOf("B", "C"),        // two values
            groupOf("D"),             // single value again
            groupOf("E")              // single value
        )

        fails { // because middle group is out of order
            expect(listOf("A", "B", "E", "F", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOf("A", "B"),    // first group ✓
                groupOf("C", "D"),    // second group should come before third ✗
                groupOf("E", "F")     // third group ✓
            )
        }
    }

    @Test
    fun inAnyOrderWithDuplicates() {
        // Handling duplicate values within and across groups
        expect(listOf("A", "A", "B", "B")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A", "A"),        // first group with duplicates
            groupOf("B", "B")         // second group with duplicates
        )

        // Same value can appear in different groups
        expect(listOf("A", "B", "A", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A", "B"),        // A appears in first group
            groupOf("A", "C")         // A also appears in second group
        )

        fails { // because duplicate count doesn't match
            expect(listOf("A", "A", "A", "B")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOf("A", "A"),    // expects two A's, but there are three in the list
                groupOf("B")
            )
        }
    }

    @Test
    fun inAnyOrderWithNullValues() {
        // Testing groups that contain null values
        expect(listOf("A", null, "B", null)).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf("A", null),         // first group with null
            groupOf("B", null)          // second group with null
        )

        // Null can be mixed with other types
        expect(listOf(1, null, "test", 2.5)).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOf(1, null),           // mixed Int and null
            groupOf("test", 2.5)        // mixed String and Double
        )

        fails { // because null is not where expected
            expect(listOf("A", "B", null)).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOf("A", null),     // expects null in first group
                groupOf("B")            // but null is actually after B
            )
        }
    }

    @Test
    fun inAnyOrderEntriesWithStringList() {
        // Example with string expectations to demonstrate entry functionality
        expect(listOf("apple", "ant", "banana", "berry")).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOfEntries<String>({ toStartWith("a") }, { toStartWith("a") }),      // first group: strings starting with "a"
            groupOfEntries<String>({ toStartWith("b") }, { toStartWith("b") })       // second group: strings starting with "b"
        )

        fails { // because not all strings match the expectation
            expect(listOf("apple", "cherry", "banana", "berry")).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOfEntries<String>({ toStartWith("a") }, { toStartWith("a") }),  // "cherry" doesn't start with "a"
                groupOfEntries<String>({ toStartWith("b") }, { toStartWith("b") })
            )
        }
    }

    @Test
    fun inAnyOrderEntriesWithNumbers() {
        // Example with numeric expectations
        expect(listOf(1, 3, 20, 30)).toContain.inOrder.only.grouped.within.inAnyOrder(
            groupOfEntries<Int>({ toBeLessThan(10) }, { toBeLessThan(10) }),          // first group: numbers < 10
            groupOfEntries<Int>({ toBeGreaterThan(10) }, { toBeGreaterThan(10) })    // second group: numbers > 10
        )

        fails { // because 5 is not greater than 10
            expect(listOf(1, 3, 20, 5)).toContain.inOrder.only.grouped.within.inAnyOrder(
                groupOfEntries<Int>({ toBeLessThan(10) }, { toBeLessThan(10) }),      // first group matches
                groupOfEntries<Int>({ toBeGreaterThan(10) }, { toBeGreaterThan(10) }) // 5 doesn't match > 10
            )
        }
    }
}
