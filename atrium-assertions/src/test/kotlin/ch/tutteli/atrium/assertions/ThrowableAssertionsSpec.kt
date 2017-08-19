package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

object ThrowableAssertionsSpec : Spek({

    describe("fun `message` (for Throwable)") {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionBasic.IS_NOT)
        }, { message }, { message {} })


        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>()
            }, { message.contains("hello") }, { message { contains("hello") } })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { message.contains("oh") }, { message { contains("oh") } })
        }
    }
})
