package ch.tutteli.atrium.api.infix.en_GB

import kotlin.test.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInOrderOnlyReportOptions

/**
 * Migrated from Atrium's custom test framework to kotlin-test.
 * This test class verifies the behavior of toContain with inGiven order, only, grouped entries, and inAny order.
 */
class IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec {

    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to
                Companion::toContainInOrderOnlyGroupedInAnyOrderEntries

        private fun toContainInOrderOnlyGroupedInAnyOrderEntries(
            expect: Expect<Iterable<Double?>>,
            a1: Group<(Expect<Double>.() -> Unit)?>,
            a2: Group<(Expect<Double>.() -> Unit)?>,
            aX: Array<out Group<(Expect<Double>.() -> Unit)?>>,
            report: InOrderOnlyReportingOptions.() -> Unit,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
        ): Expect<Iterable<Double?>> =
            if (report === emptyInOrderOnlyReportOptions && reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX
                )
            } else if (reportInGroup == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report
                )
            } else if (report == emptyInAnyOrderOnlyReportOptions) {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, reportInGroup = reportInGroup
                )
            } else {
                expect toContain o inGiven order and only grouped entries within group inAny order(
                    a1, a2, *aX, report = report, reportInGroup = reportInGroup
                )
            }

        private fun groupFactory(groups: Array<out (Expect<Double>.() -> Unit)?>) =
            when (groups.size) {
                0 -> object : Group<(Expect<Double>.() -> Unit)?> {
                    override fun toList() = emptyList<Expect<Double>.() -> Unit>()
                }
                1 -> entry(groups[0])
                else -> entries(groups[0], *groups.drop(1).toTypedArray())
            }

        private fun context(vararg assertionCreators: (Expect<Double>.() -> Unit)?) =
            groupFactory(assertionCreators)
    }

    // ===== Empty Group Validation Tests =====

    @Test
    fun `throws IllegalArgumentException if first group is empty`() {
        assertFailsWith<IllegalArgumentException> {
            val (_, containsFun) = getContainsPair()
            containsFun(
                expect(listOf(1.0, 2.0, 3.0, 4.0)),
                context(),
                context({ toEqual(-1.2) }),
                emptyArray(),
                emptyInOrderOnlyReportOptions,
                emptyInAnyOrderOnlyReportOptions
            )
        }.also { exception ->
            assertTrue(exception.message?.contains("a group of values cannot be empty") == true)
        }
    }

    @Test
    fun `throws IllegalArgumentException if second group is empty`() {
        assertFailsWith<IllegalArgumentException> {
            val (_, containsFun) = getContainsPair()
            containsFun(
                expect(listOf(1.0, 2.0, 3.0, 4.0)),
                context({ toEqual(1.2) }),
                context(),
                emptyArray(),
                emptyInOrderOnlyReportOptions,
                emptyInAnyOrderOnlyReportOptions
            )
        }.also { exception ->
            assertTrue(exception.message?.contains("a group of values cannot be empty") == true)
        }
    }

    @Test
    fun `throws IllegalArgumentException if third group is empty`() {
        assertFailsWith<IllegalArgumentException> {
            val (_, containsFun) = getContainsPair()
            containsFun(
                expect(listOf(1.0, 2.0, 3.0, 4.0)),
                context({ toEqual(1.2) }),
                context({ toEqual(4.3) }),
                arrayOf(context()),
                emptyInOrderOnlyReportOptions,
                emptyInAnyOrderOnlyReportOptions
            )
        }.also { exception ->
            assertTrue(exception.message?.contains("a group of values cannot be empty") == true)
        }
    }

    @Test
    fun `throws IllegalArgumentException if fourth group is empty`() {
        assertFailsWith<IllegalArgumentException> {
            val (_, containsFun) = getContainsPair()
            containsFun(
                expect(listOf(1.0, 2.0, 3.0, 4.0)),
                context({ toEqual(1.2) }),
                context({ toEqual(4.3) }),
                arrayOf(context({ toEqual(5.7) }), context()),
                emptyInOrderOnlyReportOptions,
                emptyInAnyOrderOnlyReportOptions
            )
        }.also { exception ->
            assertTrue(exception.message?.contains("a group of values cannot be empty") == true)
        }
    }

    // ===== Empty Iterable Tests =====

    @Test
    fun `empty iterable with expectations throws AssertionError`() {
        assertFailsWith<AssertionError> {
            expect(emptyList<Double?>()) toContain o inGiven order and only grouped entries within group inAny order(
                entry { toEqual(1.0) },
                entry { toEqual(1.2) }
            )
        }
    }

    // ===== Happy Path Tests =====

    @Test
    fun `(1_0), (2_0, 3_0), (4_0, 4_0) succeeds`() {
        expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
            entry { toEqual(1.0) },
            entries({ toEqual(2.0) }, { toEqual(3.0) }),
            entries({ toEqual(4.0) }, { toEqual(4.0) })
        )
    }

    @Test
    fun `(2_0, 1_0), (4_0, 3_0), (4_0) succeeds`() {
        expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ toEqual(2.0) }, { toEqual(1.0) }),
            entries({ toEqual(4.0) }, { toEqual(3.0) }),
            entry { toEqual(4.0) }
        )
    }

    @Test
    fun `(2_0, 3_0, 1_0), (4_0), (4_0) succeeds`() {
        expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ toEqual(2.0) }, { toEqual(3.0) }, { toEqual(1.0) }),
            entry { toEqual(4.0) },
            entry { toEqual(4.0) }
        )
    }

    @Test
    fun `(1_0, 2_0), (4_0, 3_0, 4_0) succeeds`() {
        expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ toEqual(1.0) }, { toEqual(2.0) }),
            entries({ toEqual(4.0) }, { toEqual(3.0) }, { toEqual(4.0) })
        )
    }

    @Test
    fun `range matchers with toBeLessThan and toBeGreaterThan succeed`() {
        expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
            entries(
                { toBeGreaterThan(1.0) and o toBeLessThan(2.1) },
                { toBeLessThan(2.0) }
            ),
            entries(
                { toBeGreaterThan(3.0) },
                { toBeGreaterThan(2.0) },
                { toBeGreaterThan(1.0) and o toBeLessThan(5.0) }
            )
        )
    }

    // ===== Error Cases =====

    @Test
    fun `(4_0, 1_0), (2_0, 3_0, 4_0) wrong order fails`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ toEqual(4.0) }, { toEqual(1.0) }),
                entries({ toEqual(2.0) }, { toEqual(3.0) }, { toEqual(4.0) })
            )
        }
    }

    @Test
    fun `first win applies - toBeLessThan(2_1) matches 1_0 not 2_0`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ toBeLessThan(2.1) }, { toBeLessThan(2.0) }),
                entries({ toEqual(4.0) }, { toEqual(3.0) }, { toEqual(4.0) })
            )
        }
    }

    @Test
    fun `(1_0), (4_0, 2_0, 3_0) - additional element 4_0 fails`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entry { toEqual(1.0) },
                entries({ toEqual(4.0) }, { toEqual(2.0) }, { toEqual(3.0) })
            )
        }
    }

    @Test
    fun `(1_0), (4_0) - missing 2_0, 3_0, 4_0 fails`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entry { toEqual(1.0) },
                entry { toEqual(4.0) }
            )
        }
    }

    @Test
    fun `(1_0, 3_0), (5_0) - wrong value and missing elements fails`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ toEqual(1.0) }, { toEqual(3.0) }),
                entry { toEqual(5.0) }
            )
        }
    }

    @Test
    fun `(4_0, 1_0, 3_0, 2_0), (5_0, 4_0) - too many expected elements fails`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ toEqual(4.0) }, { toEqual(1.0) }, { toEqual(3.0) }, { toEqual(2.0) }),
                entries({ toEqual(5.0) }, { toEqual(4.0) })
            )
        }
    }

    // ===== Report Options Tests =====

    @Test
    fun `showOnlyFailing report option shows only failing indices`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entry { toEqual(1.0) },
                entries({ toEqual(2.0) }, { toEqual(4.0) }),
                entries(
                    { toEqual(1.0) }, { toEqual(2.0) }, { toEqual(3.0) },
                    { toEqual(4.0) }, { toEqual(5.0) }, { toEqual(6.0) },
                    { toEqual(7.0) }, { toEqual(8.0) }, { toEqual(9.0) },
                    { toEqual(10.0) }, { toEqual(11.0) }
                ),
                report = { showOnlyFailing() }
            )
        }
    }

    @Test
    fun `showOnlyFailing for both report and reportInGroup`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ toEqual(1.0) }, { toEqual(2.0) }, { toEqual(3.0) }),
                entries({ toEqual(4.0) }, { toEqual(4.0) }, { toEqual(5.0) }),
                report = { showOnlyFailing() },
                reportInGroup = { showOnlyFailing() }
            )
        }
    }

    @Test
    fun `showOnlyFailingIfMoreExpectedElementsThan(3) shows only failing when threshold exceeded`() {
        assertFailsWith<AssertionError> {
            expect(listOf(1.0, 2.0, 3.0, 4.0, 4.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries({ toEqual(1.0) }, { toEqual(2.0) }, { toEqual(3.0) }),
                entries({ toEqual(4.0) }, { toEqual(4.0) }, { toEqual(5.0) }),
                report = { showOnlyFailingIfMoreExpectedElementsThan(3) }
            )
        }
    }

    // ===== Nullable Cases =====

    @Test
    fun `nullable - (toEqual(1_0), null), (null, toEqual(3_0)) succeeds`() {
        expect(listOf(null, 1.0, null, 3.0)) toContain o inGiven order and only grouped entries within group inAny order(
            entries({ toEqual(1.0) }, null),
            entries(null, { toEqual(3.0) })
        )
    }

    @Test
    fun `nullable - (null), (null, toBeGreaterThan(2_0), toBeLessThan(5_0)) succeeds`() {
        expect(listOf(null, 1.0, null, 3.0)) toContain o inGiven order and only grouped entries within group inAny order(
            entry(null),
            entries(null, { toBeGreaterThan(2.0) }, { toBeLessThan(5.0) })
        )
    }

    @Test
    fun `nullable - (null, null), (toBeLessThan(5_0), toBeGreaterThan(2_0)) wrong order fails`() {
        assertFailsWith<AssertionError> {
            expect(listOf(null, 1.0, null, 3.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries(null, null),
                entries({ toBeLessThan(5.0) }, { toBeGreaterThan(2.0) })
            )
        }
    }

    @Test
    fun `nullable - (null, toEqual(1_0)), (toEqual(3_0), null, null) too many nulls fails`() {
        assertFailsWith<AssertionError> {
            expect(listOf(null, 1.0, null, 3.0)) toContain o inGiven order and only grouped entries within group inAny order(
                entries(null, { toEqual(1.0) }),
                entries({ toEqual(3.0) }, null, null)
            )
        }
    }
}
