@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class CollectionFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    sizeValPair: Pair<String, Assert<Collection<String>>.() -> Assert<Int>>,
    sizeFunPair: Pair<String, Assert<Collection<String>>.(assertionCreator: Assert<Int>.() -> Unit) -> Assert<Collection<String>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //@formatter:off
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Collection<String>>(describePrefix,
        "val ${sizeValPair.first}" to mapToCreateAssertion { sizeValPair.second(this).toBe(1) },
        "fun ${sizeFunPair.first}" to mapToCreateAssertion { sizeFunPair.second(this) { isGreaterThan(2) } }
    ){})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Collection<String>>(verbs, describePrefix,
        checkingTriple("val ${sizeValPair.first}", { sizeValPair.second(this).toBe(1) }, listOf("a") as Collection<String>, listOf("a", "B")),
        checkingTriple("fun ${sizeFunPair.first}", { sizeFunPair.second(this) { isLessThan(2) } }, listOf("a")as Collection<String>, listOf("a", "B"))
    ) {})
    //@formatter:on

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assert: (Collection<String>) -> Assert<Collection<String>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(listOf("a", "b"))

    val (sizeValName, sizeVal) = sizeValPair
    val (sizeFunName, sizeFun) = sizeFunPair

    describeFun("val $sizeValName") {
        context("list with two entries") {
            test("toBe(2) holds") {
                fluent.sizeVal().toBe(2)
            }
            test("toBe(1) fails") {
                expect {
                    fluent.sizeVal().toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("size: 2")
                }
            }
        }
    }

    describeFun("fun $sizeFunName") {
        context("map with two entries") {
            test("is greater than 1 holds") {
                fluent.sizeFun { isGreaterThan(1) }
            }
            test("is less than 1 fails") {
                expect {
                    fluent.sizeFun { isLessThan(1) }
                }.toThrow<AssertionError> {
                    messageContains("size: 2")
                }
            }
            //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.sizeFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
        }
    }
})
