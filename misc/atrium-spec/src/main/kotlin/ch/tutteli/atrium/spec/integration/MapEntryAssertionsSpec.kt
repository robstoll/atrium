@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.containsNot
import ch.tutteli.atrium.api.cc.en_GB.message
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class MapEntryAssertionsSpec(
    verbs: AssertionVerbFactory,
    isKeyValuePair: Pair<String, Assert<Map.Entry<String, Int>>.(String, Int) -> Assert<Map.Entry<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Map.Entry<String, Int>>(describePrefix,
        isKeyValuePair.first to mapToCreateAssertion { isKeyValuePair.second(this, "key", 1) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Map.Entry<String, Int>>(verbs, describePrefix,
        checkingTriple(isKeyValuePair.first, { isKeyValuePair.second(this, "a", 1) }, mapEntry("a", 1), mapEntry("b", 1))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assert: (Map.Entry<String, Int>) -> Assert<Map.Entry<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val mapEntry = mapEntry("a", 1)
    val fluent = assert(mapEntry)

    val (isKeyValue, isKeyValueFun) = isKeyValuePair

    val toBeDescr = TO_BE.getDefault()

    describeFun(isKeyValue) {
        context("map $mapEntry") {
            test("a to 1 does not throw") {
                fluent.isKeyValueFun("a", 1)
            }

            test("a to 2 throws AssertionError") {
                expect {
                    fluent.isKeyValueFun("a", 2)
                }.toThrow<AssertionError> {
                    message {
                        contains("value: 1", "$toBeDescr: 2")
                        containsNot("key")
                    }
                }
            }
            test("b to 1 throws AssertionError") {
                expect {
                    fluent.isKeyValueFun("b", 1)
                }.toThrow<AssertionError> {
                    message {
                        contains("key: \"a\"", "$toBeDescr: \"b\"")
                        containsNot("value")
                    }
                }
            }
        }
    }
})
