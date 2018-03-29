package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.messageContains
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsNotAssertionSpec(
    verbs: AssertionVerbFactory,
    containsNotTriple: Triple<String, (String) -> String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsNotTriple.first to mapToCreateAssertion { containsNotTriple.third(this, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsNotTriple.first, { containsNotTriple.third(this, 2.3, arrayOf()) }, listOf(2.1) as Iterable<Double>, listOf(2.1, 2.3))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsNot, containsNotTest, containsNotFunArr) = containsNotTriple
    fun Assert<Iterable<Double>>.containsNotFun(a: Double, vararg aX: Double)
        = containsNotFunArr(a, aX.toTypedArray())

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()

    describeFun(containsNot) {

        context("iterable $oneToSeven") {
            group("happy case with $containsNot once") {
                test("${containsNotTest("1.1")} does not throw") {
                    fluent.containsNotFun(1.1)
                }
                test("${containsNotTest("1.1 and 2.2 and 3.3")} does not throw") {
                    fluent.containsNotFun(1.1, 2.2, 3.3)
                }
                test("${containsNotTest("3.3 and 1.1 and 2.2")} does not throw") {
                    fluent.containsNotFun(3.3, 1.1, 2.2)
                }
            }

            group("failing assertions; search string at different positions") {
                test("${containsNotTest("4.0")} throws AssertionError") {
                    expect {
                        fluent.containsNotFun(4.0)
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$containsNotDescr: 4.0",
                            "${CharSequenceContainsSpecBase.numberOfOccurrences}: 3",
                            "${DescriptionBasic.IS.getDefault()}: 0"
                        )
                    }
                }
                test("${containsNotTest("1.0, 4.0")} throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.0, 4.0)
                    }.toThrow<AssertionError> { messageContains(4.0) }
                }
                test("${containsNotTest("4.0, 1.0")} once throws AssertionError") {
                    expect {
                        fluent.containsNotFun(4.0, 1.0)
                    }.toThrow<AssertionError> { messageContains(4.0) }
                }
            }
        }
    }
})
