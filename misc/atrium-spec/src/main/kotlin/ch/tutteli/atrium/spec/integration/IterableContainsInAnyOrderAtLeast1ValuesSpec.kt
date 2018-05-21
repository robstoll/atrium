package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderAtLeast1ValuesSpec(
    verbs: AssertionVerbFactory,
    containsInAnyOrderNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double?>>(describePrefix,
        containsInAnyOrderNullableValuesPair.first to mapToCreateAssertion { containsInAnyOrderNullableValuesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double?>>(verbs, describePrefix,
        checkingTriple(containsInAnyOrderNullableValuesPair.first, { containsInAnyOrderNullableValuesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double?>) -> Assert<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    val list = listOf(null, 1.0, null, 3.0)
    val fluent = assert(list)

    val (containsInAnyOrderNullableValues, containsInAnyOrderNullableValuesFunArr) = containsInAnyOrderNullableValuesPair
    fun Assert<Iterable<Double?>>.containsFun(t: Double?, vararg tX: Double?)
        = containsInAnyOrderNullableValuesFunArr(t, tX)

    describeFun(containsInAnyOrderNullableValues) {
        context("iterable $list") {
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
                    test("$containsInAnyOrderNullableValues $first$restText does not throw") {
                        fluent.containsFun(first, *rest)
                    }
                }

            }

            context("search for 2.5") {
                test("$containsInAnyOrderNullableValues 2.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(2.5)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CONTAINS) } }
                }
            }
        }
    }
})
