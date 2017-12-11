package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS_NOT
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsNullSpec(
    verbs: IAssertionVerbFactory,
    containsPair: Pair<String, IAssertionPlant<Iterable<Double?>>.(Double?, Array<out Double?>) -> IAssertionPlant<Iterable<Double?>>>,
    containsNotPair: Pair<String, IAssertionPlant<Iterable<Double?>>.(Double?, Array<out Double?>) -> IAssertionPlant<Iterable<Double?>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double?>>(
        containsPair.first to mapToCreateAssertion { containsPair.second(this, null, arrayOf()) },
        containsNotPair.first to mapToCreateAssertion { containsNotPair.second(this, null, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double?>>(verbs,
        checkingTriple(containsPair.first, { containsPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsNotPair.first, { containsNotPair.second(this, null, arrayOf()) }, listOf(1.2) as Iterable<Double?>, listOf(null))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double?>) -> IAssertionPlant<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    val list = listOf(null, 1.0, null, 3.0)
    val fluent = assert(list)

    val (contains, containsFunArr) = containsPair
    fun IAssertionPlant<Iterable<Double?>>.containsFun(t: Double?, vararg tX: Double?)
        = containsFunArr(t, tX)

    val (containsNot, containsNotFunArr) = containsNotPair
    fun IAssertionPlant<Iterable<Double?>>.containsNotFun(t: Double?, vararg tX: Double?)
        = containsNotFunArr(t, tX)

    describeFun(contains, containsNot) {

        context("iterable '$list'") {
            listOf(
                1.0 to arrayOf<Double>(),
                3.0 to arrayOf<Double>(),
                null to arrayOf<Double>(),
                null to arrayOf(3.0, null),
                null to arrayOf(1.0),
                1.0 to arrayOf(3.0, null)
            ).forEach { (first, rest) ->
                val restText = if (rest.isEmpty()) {
                    ""
                } else {
                    ", ${rest.joinToString()}"
                }
                context("search for $first$restText") {
                    test("$contains $first$restText  does not throw") {
                        fluent.containsFun(first, *rest)
                    }
                    test("$containsNot $first$restText throws AssertionError") {
                        expect {
                            fluent.containsNotFun(first, *rest)
                        }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS_NOT) } }
                    }
                }

            }

            context("search for 2.5") {
                test("$contains 2.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(2.5)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS) } }
                }
                test("$containsNot 2.5 throws AssertionError") {
                    fluent.containsNotFun(2.5)
                }
            }
        }
    }
})
