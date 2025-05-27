package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class IterableLikeToContainInOrderOnlyGroupedCreatorSamples {

    @Test
    fun inAnyOrderWithValues() {
        // Groups must appear in order, but within each group elements can be in any order
        expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Values("A", "B"),        // first group: A and B (in any order)
            Values("C", "D")         // second group: C and D (in any order)
        )

        // This also works - same groups, different internal order
        expect(listOf("B", "A", "D", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Values("A", "B"),        // first group: A and B (in any order)
            Values("C", "D")         // second group: C and D (in any order)
        )

        // Single element groups
        expect(listOf("A", "B", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Value("A"),              // first group: single value A
            Values("B", "C")         // second group: B and C (in any order)
        )

        fails { // because groups are in wrong order
            expect(listOf("C", "D", "A", "B")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Values("A", "B"),    // first group should come first
                Values("C", "D")     // second group should come second
            )
        }

        fails { // because not all elements are present
            expect(listOf("A", "B", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Values("A", "B"),
                Values("C", "D")     // D is missing from the list
            )
        }

        fails { // because there are extra elements
            expect(listOf("A", "B", "C", "D", "E")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Values("A", "B"),
                Values("C", "D")     // E is not expected
            )
        }
    }

    @Test
    fun inAnyOrderWithEntries() {
        // Using expectation lambdas for more complex assertions
        expect(listOf(1, 2, 10, 20)).toContain.inOrder.only.grouped.within.inAnyOrder(
            Entries({ toBeGreaterThan(0) }, { toBeLessThan(5) }),         // first group: numbers > 0 and < 5
            Entries({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })      // second group: numbers > 5
        )

        // Mixed with null values
        expect(listOf("A", null, "B", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Entries({ toEqual("A") }, null),                              // first group: "A" and null
            Entries({ toEqual("B") }, { toEqual("C") })                  // second group: "B" and "C"
        )

        // Single entry in a group
        expect(listOf(1, 10, 20)).toContain.inOrder.only.grouped.within.inAnyOrder(
            Entry { toBeLessThan(5) },                                    // first group: single entry < 5
            Entries({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })      // second group: numbers > 5
        )

        fails { // because one element doesn't match the expectation
            expect(listOf(1, 2, 10, 4)).toContain.inOrder.only.grouped.within.inAnyOrder(
                Entries({ toBeLessThan(5) }, { toBeLessThan(5) }),        // 1 and 2 are < 5 ✓
                Entries({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })   // 10 is > 5 ✓, but 4 is not > 5 ✗
            )
        }

        fails { // because groups are mixed together
            expect(listOf(1, 10, 2, 20)).toContain.inOrder.only.grouped.within.inAnyOrder(
                Entries({ toBeLessThan(5) }, { toBeLessThan(5) }),        // should be first group
                Entries({ toBeGreaterThan(5) }, { toBeGreaterThan(5) })   // should be second group
            )
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Entries({ toEqual("A") }, { /* do nothing */ }),          // empty expectation block
                Entries({ toEqual("C") }, { toEqual("D") })
            )
        }
    }

    @Test
    fun inAnyOrderWithReportingOptions() {
        // Using reporting options to customize failure messages
        expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Values("A", "B"),
            Values("C", "D"),
            report = {
                showOnlyFailing()  // only show failing elements in report
            },
            reportInGroup = {
                showOnlyFailingIfMoreExpectedElementsThan(5)  // customize in-group reporting
            }
        )

        fails {
            expect(listOf("A", "B", "X", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Values("A", "B"),
                Values("C", "D"),   // C is missing, X is unexpected
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
            Values("A", "B"),        // first group
            Values("C", "D"),        // second group
            Values("E", "F")         // third group
        )

        // Groups can have different sizes
        expect(listOf("A", "B", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Value("A"),              // single element group
            Values("B", "C", "D")    // three element group
        )

        // Mixed single and multiple element groups
        expect(listOf("A", "B", "C", "D", "E")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Value("A"),              // single value
            Values("B", "C"),        // two values
            Value("D"),              // single value again
            Value("E")               // single value
        )

        fails { // because middle group is out of order
            expect(listOf("A", "B", "E", "F", "C", "D")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Values("A", "B"),    // first group ✓
                Values("C", "D"),    // second group should come before third ✗
                Values("E", "F")     // third group ✓
            )
        }
    }

    @Test
    fun inAnyOrderWithDuplicates() {
        // Handling duplicate values within and across groups
        expect(listOf("A", "A", "B", "B")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Values("A", "A"),        // first group with duplicates
            Values("B", "B")         // second group with duplicates
        )

        // Same value can appear in different groups
        expect(listOf("A", "B", "A", "C")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Values("A", "B"),        // A appears in first group
            Values("A", "C")         // A also appears in second group
        )

        fails { // because duplicate count doesn't match
            expect(listOf("A", "A", "A", "B")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Values("A", "A"),    // expects two A's, but there are three in the list
                Value("B")
            )
        }
    }

    @Test
    fun inAnyOrderWithNullValues() {
        // Testing groups that contain null values
        expect(listOf("A", null, "B", null)).toContain.inOrder.only.grouped.within.inAnyOrder(
            Values("A", null),         // first group with null
            Values("B", null)          // second group with null
        )

        // Null can be mixed with other types
        expect(listOf(1, null, "test", 2.5)).toContain.inOrder.only.grouped.within.inAnyOrder(
            Values(1, null),           // mixed Int and null
            Values("test", 2.5)        // mixed String and Double
        )

        fails { // because null is not where expected
            expect(listOf("A", "B", null)).toContain.inOrder.only.grouped.within.inAnyOrder(
                Values("A", null),     // expects null in first group
                Value("B")             // but null is actually after B
            )
        }
    }

    @Test
    fun inAnyOrderEntriesWithStringList() {
        // Example with string expectations to demonstrate entry functionality
        expect(listOf("apple", "ant", "banana", "berry")).toContain.inOrder.only.grouped.within.inAnyOrder(
            Entries({ toStartWith("a") }, { toStartWith("a") }),      // first group: strings starting with "a"
            Entries({ toStartWith("b") }, { toStartWith("b") })       // second group: strings starting with "b"
        )

        fails { // because not all strings match the expectation
            expect(listOf("apple", "cherry", "banana", "berry")).toContain.inOrder.only.grouped.within.inAnyOrder(
                Entries({ toStartWith("a") }, { toStartWith("a") }),  // "cherry" doesn't start with "a"
                Entries({ toStartWith("b") }, { toStartWith("b") })
            )
        }
    }

    @Test
    fun inAnyOrderEntriesWithNumbers() {
        // Example with numeric expectations
        expect(listOf(1, 3, 20, 30)).toContain.inOrder.only.grouped.within.inAnyOrder(
            Entries({ toBeLessThan(10) }, { toBeLessThan(10) }),          // first group: numbers < 10
            Entries({ toBeGreaterThan(10) }, { toBeGreaterThan(10) })    // second group: numbers > 10
        )

        fails { // because 5 is not greater than 10
            expect(listOf(1, 3, 20, 5)).toContain.inOrder.only.grouped.within.inAnyOrder(
                Entries({ toBeLessThan(10) }, { toBeLessThan(10) }),      // first group matches
                Entries({ toBeGreaterThan(10) }, { toBeGreaterThan(10) }) // 5 doesn't match > 10
            )
        }
    }
}
