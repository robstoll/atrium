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

abstract class BooleanAssertionsSpec(
    verbs: IAssertionVerbFactory,
    isTruePair: Pair<String, IAssertionPlant<Boolean>.() -> IAssertionPlant<Boolean>>,
    isFalsePair: Pair<String, IAssertionPlant<Boolean>.() -> IAssertionPlant<Boolean>>
) : Spek({

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
            }.toThrow<AssertionError>().and.message {
                containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE)
                contains(": false")
            }
        }
    }

    describe("subject is `false`") {
        val fluent = assert(false)

        test("$isTrue throws an AssertionError containing ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} and `: true`") {
            expect {
                fluent.isTrueFun()
            }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE).and.contains(": true")
        }
        test("$isFalse does not throw") {
            fluent.isFalseFun()
        }
    }
})
