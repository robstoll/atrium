package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class BooleanAssertionsSpec(
    verbs: IAssertionVerbFactory,
    isTruePair: Pair<String, IAssertionPlant<Boolean>.() -> IAssertionPlant<Boolean>>,
    isFalsePair: Pair<String, IAssertionPlant<Boolean>.() -> IAssertionPlant<Boolean>>
) : Spek({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Boolean>(
        isTruePair.first to mapToCreateAssertion { isTruePair.second(this) },
        isFalsePair.first to mapToCreateAssertion { isFalsePair.second(this) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Boolean>(verbs,
        checkingTriple(isTruePair.first, { isTruePair.second(this) }, true, false),
        checkingTriple(isFalsePair.first, { isFalsePair.second(this) }, false, true)
    ) {})

    val assert: (Boolean) -> IAssertionPlant<Boolean> = verbs::checkImmediately
    val expect = verbs::checkException
    val (isTrue, isTrueFun) = isTruePair
    val (isFalse, isFalseFun) = isFalsePair

    describe("subject is `true`") {
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

    describe("subject is `false`") {
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
