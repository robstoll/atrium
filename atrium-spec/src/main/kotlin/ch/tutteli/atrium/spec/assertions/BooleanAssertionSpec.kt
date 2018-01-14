package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.include

abstract class BooleanAssertionsSpec(
    verbs: AssertionVerbFactory,
    isTruePair: Pair<String, Assert<Boolean>.() -> Assert<Boolean>>,
    isFalsePair: Pair<String, Assert<Boolean>.() -> Assert<Boolean>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Boolean>(describePrefix,
        isTruePair.first to mapToCreateAssertion { isTruePair.second(this) },
        isFalsePair.first to mapToCreateAssertion { isFalsePair.second(this) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Boolean>(verbs, describePrefix,
        checkingTriple(isTruePair.first, { isTruePair.second(this) }, true, false),
        checkingTriple(isFalsePair.first, { isFalsePair.second(this) }, false, true)
    ) {})

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assert: (Boolean) -> Assert<Boolean> = verbs::checkImmediately
    val expect = verbs::checkException
    val (isTrue, isTrueFun) = isTruePair
    val (isFalse, isFalseFun) = isFalsePair

    prefixedDescribe("subject is `true`") {
        val fluent = assert(true)
        test("$isTrue does not throw") {
            fluent.isTrueFun()
        }
        test("$isFalse throws an AssertionError containing ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} and `: false`") {
            expect {
                fluent.isFalseFun()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE)
                    contains(": false")
                }
            }
        }
    }

    prefixedDescribe("subject is `false`") {
        val fluent = assert(false)

        test("$isTrue throws an AssertionError containing ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} and `: true`") {
            expect {
                fluent.isTrueFun()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE)
                    contains(": true")
                }
            }
        }
        test("$isFalse does not throw") {
            fluent.isFalseFun()
        }
    }
})
