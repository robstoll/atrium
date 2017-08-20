package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.containsDefaultTranslationOf
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.message
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class ThrowableAssertionsSpec(
    verbs: IAssertionVerbFactory,
    messageTriple: Triple<
        String,
        IAssertionPlant<Throwable>.() -> IAssertionPlant<String>,
        IAssertionPlant<Throwable>.(createAssertions: IAssertionPlant<String>.() -> Unit) -> IAssertionPlant<String>
    >,
    messageContainsPair: Pair<
        IAssertionPlant<Throwable>.(String) -> IAssertionPlant<String>,
        IAssertionPlant<Throwable>.(String) -> IAssertionPlant<String>
    >
) : Spek({

    val expect = verbs::checkException
    val assert: (IllegalArgumentException) -> IAssertionPlant<IllegalArgumentException> = verbs::checkImmediately

    val (message, messageFun, messageLazyFun) = messageTriple
    val (messageContainsFun, messageContainsLazyFun) = messageContainsPair

    describe("fun `$message` (for Throwable)") {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionBasic.IS_NOT)
        }, { messageFun() }, { messageLazyFun {} })


        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>()
            }, { messageContainsFun("hello") }, { messageContainsLazyFun("hello") })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { messageContainsFun("oh") }, { messageContainsLazyFun("oh") })
        }
    }
})
