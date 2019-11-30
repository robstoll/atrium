@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ThrowableAssertionsSpec(
    messageFeature: Feature0<Throwable, String>,
    message: Fun1<Throwable, Expect<String>.() -> Unit>,
    messageContains: Fun2<Throwable, Any, Array<out Any>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Throwable>(
        "$describePrefix[nullable Key] ",
        messageFeature.forSubjectLess(),
        message.forSubjectLess { toBe("hello") },
        messageContains.forSubjectLess("hello", arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Throwable>(
        describePrefix, RuntimeException("hello"),
        message.forAssertionCreatorSpec("$toBeDescr: hello") { toBe("hello") }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    describeFun("${messageFeature.name} feature") {
        val messageFun = messageFeature.lambda
        checkNarrowingAssertion<Throwable>(
            "it throws an AssertionError if the ${Throwable::message.name} is null",
            { message ->
                val throwable: Throwable = IllegalArgumentException()
                expect {
                    expect(throwable).message()
                }.toThrow<AssertionError> {
                    messageContains(
                        DescriptionAnyAssertion.IS_A.getDefault(),
                        String::class.fullName
                    )
                }
            },
            { messageFun().toBe("hello") }
        )

        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable: Throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>(
                "it throws an AssertionError if the assertion does not hold",
                { messageWithCheck ->
                    expect {
                        expect(throwable).messageWithCheck()
                    }.toThrow<AssertionError>()
                },
                { messageFun().contains("hello") }
            )

            checkNarrowingAssertion<Throwable>(
                "it does not throw an exception if the assertion holds",
                { messageWithCheck -> expect(throwable).messageWithCheck() }, { messageFun().contains("oh") }
            )
        }
    }

    describeFun(message.name) {
        val messageFun = message.lambda
        checkNarrowingAssertion<Throwable>(
            "it throws an AssertionError if the ${Throwable::message.name} is null",
            { message ->
                val throwable: Throwable = IllegalArgumentException()
                expect {
                    expect(throwable).message()
                }.toThrow<AssertionError> {
                    messageContains(
                        DescriptionAnyAssertion.IS_A.getDefault(),
                        String::class.fullName
                    )
                }
            },
            { messageFun { toBe("hello") } }
        )

        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable: Throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>(
                "it throws an AssertionError if the assertion does not hold",
                { messageWithCheck ->
                    expect {
                        expect(throwable).messageWithCheck()
                    }.toThrow<AssertionError>()
                },
                { messageFun { contains("hello") } }
            )

            checkNarrowingAssertion<Throwable>(
                "it does not throw an exception if the assertion holds",
                { messageWithCheck -> expect(throwable).messageWithCheck() },
                { messageFun { contains("oh") } }
            )
        }
    }

    describeFun(messageContains.name) {

        checkNarrowingAssertion<Throwable>(
            "it throws an AssertionError if the ${Throwable::message.name} is null",
            { messageContainsFun ->
                val throwable: Throwable = IllegalArgumentException()
                expect {
                    expect(throwable).messageContainsFun()
                }.toThrow<AssertionError> {
                    messageContains(DescriptionAnyAssertion.IS_A.getDefault(), String::class.fullName)
                }
            },
            { (messageContains.lambda)(1, arrayOf(2.3, 'z', "hello")) })


        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable: Throwable = IllegalArgumentException("1 2.3 z hello")
            checkNarrowingAssertion<Throwable>(
                "it throws an AssertionError if the assertion does not hold",
                { messageContains ->
                    expect {
                        expect(throwable).messageContains()
                    }.toThrow<AssertionError>()
                },
                { (messageContains.lambda)("nada", arrayOf()) }
            )

            checkNarrowingAssertion<Throwable>(
                "it does not throw an exception if the assertion holds",
                { messageWithCheck ->
                    expect(throwable).messageWithCheck()
                },
                { (messageContains.lambda)(1, arrayOf(2.3, 'z', "hello")) }
            )


            checkNarrowingAssertion<Throwable>(
                "it throws an IllegalArgumentException if an object is passed",
                { messageContains ->
                    expect {
                        expect(throwable).messageContains()
                    }.toThrow<IllegalArgumentException>()
                },
                { (messageContains.lambda)(Pair(1, 2), arrayOf()) }
            )
        }
    }
})
