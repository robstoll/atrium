package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class ThrowableAssertionsSpec(
    verbs: IAssertionVerbFactory,
    //TODO move toThrow specs located in ThrowableFluentSpec to this place
//    toThrowTriple: Triple<String,
//        IThrowableFluent.() -> IAssertionPlant<Throwable>,
//        IThrowableFluent.(createAssertions: IAssertionPlant<Throwable>.() -> Unit) -> IAssertionPlant<Throwable>
//        >,
    messagePair: Pair<String, IAssertionPlant<Throwable>.(createAssertions: IAssertionPlant<String>.() -> Unit) -> Unit>,
    messageContainsFun: IAssertionPlant<Throwable>.(String) -> Unit
) : Spek({

    val expect = verbs::checkException
    val assert: (IllegalArgumentException) -> IAssertionPlant<IllegalArgumentException> = verbs::checkImmediately

    val (message, messageFun) = messagePair

    describe("fun `$message` (for Throwable)") {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                    contains(String::class.java.name)
                }
            }
        }, { messageFun {} })


        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>()
            }, { messageContainsFun("hello") })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { messageContainsFun("oh") })
        }
    }
})
