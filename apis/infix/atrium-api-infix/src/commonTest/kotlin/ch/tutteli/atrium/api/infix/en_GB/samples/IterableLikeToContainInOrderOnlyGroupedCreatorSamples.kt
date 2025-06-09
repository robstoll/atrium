package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInOrderOnlyGroupedCreatorSamples {

    @Test
    fun inAnyOrderWithValues() {
        // Groups must appear in order, but within each group elements can be in any order
        expect(listOf("A", "B", "C", "D")) toContain o inGiven order and only grouped entries within group inAny order(
            values("A", "B"),        // first group: A and B (in any order)
            values("C", "D")         // second group: C and D (in any order)
        )

        // This also works - same groups, different internal order
        expect(listOf("B", "A", "D", "C")) toContain o inGiven order and only grouped entries within group inAny order(
            values("A", "B"),        // first group: A and B (in any order)
            values("C", "D")         // second group: C and D (in any order)
        )

        // Single element groups
        expect(listOf("A", "B", "C")) toContain o inGiven order and only grouped entries within group inAny order(
            value("A"),              // first group: single value A
            values("B", "C")         // second group: B and C (in any order)
        )

        fails { // because groups are in wrong order
            expect(listOf("C", "D", "A", "B")) toContain o inGiven order and only grouped entries within group inAny order(
                values("A", "B"),    // first group should come first
                values("C", "D")     // second group should come second
            )
        }

        fails { // because not all elements are present
            expect(listOf("A", "B", "C")) toContain o inGiven order and only grouped entries within group inAny order(
                values("A", "B"),
                values("C", "D")     // D is missing from the list
            )
        }

        fails { // because there are extra elements
            expect(listOf("A", "B", "C", "D", "E")) toContain o inGiven order and only grouped entries within group inAny order(
                values("A", "B"),
                values("C", "D")     // E is not expected
            )
        }
    }

    @Test
    fun inAnyOrderWithEntries() {
        // Using expectation lambdas for more complex assertions
        expect(listOf(1, 2, 10, 20)) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ it toBeGreaterThan 0 }, { it toBeLessThan 5 }),         // first group: numbers > 0 and < 5
            entries({ it toBeGreaterThan 5 }, { it toBeGreaterThan 5 })      // second group: numbers > 5
        )

        // Mixed with null values
        expect(listOf("A", null, "B", "C")) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ it toEqual "A" }, null),                              // first group: "A" and null
            entries({ it toEqual "B" }, { it toEqual "C" })                  // second group: "B" and "C"
        )

        // Single entry in a group
        expect(listOf(1, 10, 20)) toContain o inGiven order and only grouped entries within group inAny order(
            entry { it toBeLessThan 5 },                                    // first group: single entry < 5
            entries({ it toBeGreaterThan 5 }, { it toBeGreaterThan 5 })      // second group: numbers > 5
        )

        fails { // because one element doesn't match the expectation
            expect(listOf(1, 2, 10, 4)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ it toBeLessThan 5 }, { it toBeLessThan 5 }),        // 1 and 2 are < 5 ✓
                entries({ it toBeGreaterThan 5 }, { it toBeGreaterThan 5 })   // 10 is > 5 ✓, but 4 is not > 5 ✗
            )
        }

        fails { // because groups are mixed together
            expect(listOf(1, 10, 2, 20)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ it toBeLessThan 5 }, { it toBeLessThan 5 }),        // should be first group
                entries({ it toBeGreaterThan 5 }, { it toBeGreaterThan 5 })   // should be second group
            )
        }

        fails { // because assertionCreatorOrNull is non-null and has no expectation
            expect(listOf("A", "B", "C", "D")) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ it toEqual "A" }, { /* do nothing */ }),          // empty expectation block
                entries({ it toEqual "C" }, { it toEqual "D" })
            )
        }
    }

    @Test
    fun inAnyOrderWithReportingOptions() {
        // Using reporting options to customize failure messages
        expect(listOf("A", "B", "C", "D")) toContain o inGiven order and only grouped entries within group inAny order(
            values("A", "B"),
            values("C", "D"),
            report = {
                showOnlyFailing()  // only show failing elements in report
            },
            reportInGroup = {
                showOnlyFailingIfMoreExpectedElementsThan(5)  // customize in-group reporting
            }
        )

        fails {
            expect(listOf("A", "B", "X", "D")) toContain o inGiven order and only grouped entries within group inAny order(
                values("A", "B"),
                values("C", "D"),   // C is missing, X is unexpected
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
        expect(listOf("A", "B", "C", "D", "E", "F")) toContain o inGiven order and only grouped entries within group inAny order(
            values("A", "B"),        // first group
            values("C", "D"),        // second group
            values("E", "F")         // third group
        )

        // Groups can have different sizes
        expect(listOf("A", "B", "C", "D")) toContain o inGiven order and only grouped entries within group inAny order(
            value("A"),              // single element group
            values("B", "C", "D")    // three element group
        )

        // Mixed single and multiple element groups
        expect(listOf("A", "B", "C", "D", "E")) toContain o inGiven order and only grouped entries within group inAny order(
            value("A"),              // single value
            values("B", "C"),        // two values
            value("D"),              // single value again
            value("E")               // single value
        )

        fails { // because middle group is out of order
            expect(listOf("A", "B", "E", "F", "C", "D")) toContain o inGiven order and only grouped entries within group inAny order(
                values("A", "B"),    // first group ✓
                values("C", "D"),    // second group should come before third ✗
                values("E", "F")     // third group ✓
            )
        }
    }

    @Test
    fun inAnyOrderWithDuplicates() {
        // Handling duplicate values within and across groups
        expect(listOf("A", "A", "B", "B")) toContain o inGiven order and only grouped entries within group inAny order(
            values("A", "A"),        // first group with duplicates
            values("B", "B")         // second group with duplicates
        )

        // Same value can appear in different groups
        expect(listOf("A", "B", "A", "C")) toContain o inGiven order and only grouped entries within group inAny order(
            values("A", "B"),        // A appears in first group
            values("A", "C")         // A also appears in second group
        )

        fails { // because duplicate count doesn't match
            expect(listOf("A", "A", "A", "B")) toContain o inGiven order and only grouped entries within group inAny order(
                values("A", "A"),    // expects two A's, but there are three in the list
                value("B")
            )
        }
    }

    @Test
    fun inAnyOrderWithNullValues() {
        // Testing groups that contain null values
        expect(listOf("A", null, "B", null)) toContain o inGiven order and only grouped entries within group inAny order(
            values("A", null),         // first group with null
            values("B", null)          // second group with null
        )

        // Null can be mixed with other types
        expect(listOf(1, null, "test", 2.5)) toContain o inGiven order and only grouped entries within group inAny order(
            values(1, null),           // mixed Int and null
            values("test", 2.5)        // mixed String and Double
        )

        fails { // because null is not where expected
            expect(listOf("A", "B", null)) toContain o inGiven order and only grouped entries within group inAny order(
                values("A", null),     // expects null in first group
                value("B")             // but null is actually after B
            )
        }
    }

    @Test
    fun inAnyOrderEntriesWithStringList() {
        // Example with string expectations to demonstrate entry functionality
        expect(listOf("apple", "ant", "banana", "berry")) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ it toStartWith "a" }, { it toStartWith "a" }),      // first group: strings starting with "a"
            entries({ it toStartWith "b" }, { it toStartWith "b" })       // second group: strings starting with "b"
        )

        fails { // because not all strings match the expectation
            expect(listOf("apple", "cherry", "banana", "berry")) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ it toStartWith "a" }, { it toStartWith "a" }),  // "cherry" doesn't start with "a"
                entries({ it toStartWith "b" }, { it toStartWith "b" })
            )
        }
    }

    @Test
    fun inAnyOrderEntriesWithNumbers() {
        // Example with numeric expectations
        expect(listOf(1, 3, 20, 30)) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ it toBeLessThan 10 }, { it toBeLessThan 10 }),          // first group: numbers < 10
            entries({ it toBeGreaterThan 10 }, { it toBeGreaterThan 10 })    // second group: numbers > 10
        )

        fails { // because 5 is not greater than 10
            expect(listOf(1, 3, 20, 5)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ it toBeLessThan 10 }, { it toBeLessThan 10 }),      // first group matches
                entries({ it toBeGreaterThan 10 }, { it toBeGreaterThan 10 }) // 5 doesn't match > 10
            )
        }
    }
}
