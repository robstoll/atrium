@file:Suppress("FunctionName")

package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

abstract class GroupingTest(
    group: Fun3<Int, String, () -> Any?, Expect<Int>.() -> Unit>
) {
    val groupFun: Expect<Int>.(String, () -> Any?, Expect<Int>.() -> Unit) -> Expect<Int> = group.lambda

    //TODO 1.3.0 add subjectLess and assertionCreator tests

    @Test
    fun all_sub_expectation_hold__does_not_throw() {
        expect(1).groupFun("my group name", {}) {
            toEqual(1)
            toBeLessThan(10)
            toBeGreaterThan(0)
        }
    }

    @Test
    fun sub_expectations_fail__reports_only_failing() {
        expect {
            expect(1).groupFun("my group name", { 123 }) {
                toEqual(2)
                toBeLessThan(10)
                toBeGreaterThan(4)
            }
        }.toThrow<AssertionError> {
            message {
                //TODO 1.3.0 should use groupingIcon
                toContainRegex(
                    "${g}my group name\\s+: 123.*$lineSeparator" +
                        "$indentG$x$toEqualDescr\\s+: 2$lineSeparator" +
                        "$indentG$x$toBeGreaterThanDescr : 4"
                )
                notToContain.regex("$x$toBeLessThanDescr\\s+: 10")
            }
        }
    }

    @Test
    fun sub_group_holds__does_not_throw() {
        expect(1).groupFun("my group name", { 123 }) {
            groupFun("other name", { "hello" }) {
                toEqual(1)
                toBeLessThan(10)
                toBeGreaterThan(0)
            }
        }
    }


    @Test
    fun sub_group_fails__only_reports_failing() {
        expect {
            expect(1).groupFun("my group name", { 123 }) {
                toEqual(2)
                toBeLessThan(10)

                groupFun("other name", { "hello" }) {
                    toBeLessThan(10)
                    toBeGreaterThan(4)
                }
            }
        }.toThrow<AssertionError> {
            message {
                toContainRegex(
                    "${g}my group name\\s+: 123.*$lineSeparator" +
                        "$indentG$x$toEqualDescr\\s+: 2$lineSeparator" +
                        "$indentG${g}other name\\s+: \"hello\".*$lineSeparator" +
                        "$indentG$indentG$x$toBeGreaterThanDescr : 4"
                )
                notToContain.regex("$rootBulletPoint$toBeLessThanDescr\\s+: 10")
            }
        }
    }
}
