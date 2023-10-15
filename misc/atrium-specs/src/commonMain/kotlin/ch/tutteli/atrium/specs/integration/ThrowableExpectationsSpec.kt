package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_PROPERTIES
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ThrowableExpectationsSpec(
    messageFeature: Feature0<Throwable, String>,
    message: Fun1<Throwable, Expect<String>.() -> Unit>,
    messageToContain: Fun2<Throwable, Any, Array<out Any>>,

    causeFeature: Feature0<Throwable, IllegalArgumentException>,
    cause: Feature1<Throwable, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,

    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Throwable>(
        describePrefix,
        messageFeature.forSubjectLess(),
        message.forSubjectLess { toEqual("hello") },
        messageToContain.forSubjectLess("hello", arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Throwable>(
        describePrefix, RuntimeException("hello"),
        message.forAssertionCreatorSpec("$toEqualDescr: hello") { toEqual("hello") }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val valueDescr = DescriptionCharSequenceExpectation.VALUE.getDefault()

    describeFun(messageFeature, message, messageToContain) {
        val messageFunctions = unifySignatures(messageFeature, message)
        val messageContainsFun = messageToContain.lambda

        context("Throwable.message is null") {
            val throwable: Throwable = IllegalArgumentException()

            messageFunctions.forEach { (name, messageFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect(throwable).messageFun { toEqual("hello") }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            notToEqualNullButToBeInstanceOfDescr,
                            String::class.fullName
                        )
                        if (hasExtraHint) messageToContain("$toEqualDescr: \"hello\"")
                    }
                }
            }

            it("${messageToContain.name} - throws an AssertionError which shows intended sub assertion") {

                expect {
                    expect(throwable).messageContainsFun(1, arrayOf(2.3, 'z', "hello"))
                }.toThrow<AssertionError> {
                    messageToContain(
                        notToEqualNullButToBeInstanceOfDescr, String::class.fullName,
                        DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault(),
                        "$valueDescr: 1",
                        "$valueDescr: 2.3",
                        "$valueDescr: 'z'",
                        "$valueDescr: \"hello\""
                    )
                }
            }
        }

        context("Throwable.message is empty") {
            val throwable: Throwable = IllegalArgumentException("")
            messageFunctions.forEach { (name, messageFun, _) ->
                it("$name - throws an AssertionError if the assertion does not hold") {
                    expect {
                        expect(throwable).messageFun { toEqual("hello") }
                    }.toThrow<AssertionError> { messageToContain("$toEqualDescr: \"hello\"") }
                }

                it("$name - does not throw if the assertion holds") {
                    expect(throwable).messageFun { toBeEmpty() }
                }
            }
            it("${messageToContain.name} - throws an AssertionError if the assertion does not hold") {
                expect {
                    expect(throwable).messageContainsFun("nada", arrayOf())
                }.toThrow<AssertionError> { messageToContain("$valueDescr: \"nada\"") }
            }
            it("${messageToContain.name} - throws IllegalArgumentException if empty string is passed") {
                expect {
                    expect(throwable).messageContainsFun("", arrayOf())
                }.toThrow<IllegalArgumentException>()
            }
        }

        context("Throwable.message is blank") {
            val throwable: Throwable = IllegalArgumentException("   ")
            messageFunctions.forEach { (name, messageFun, _) ->
                it("$name - throws an AssertionError if the assertion does not hold") {
                    expect {
                        expect(throwable).messageFun { toEqual("hello") }
                    }.toThrow<AssertionError> { messageToContain("$toEqualDescr: \"hello\"") }
                }

                it("$name - does not throw if the assertion holds") {
                    expect(throwable).messageFun { toEqual("   ") }
                }
            }
            it("${messageToContain.name} - throws an AssertionError if the assertion does not hold") {
                expect {
                    expect(throwable).messageContainsFun("nada", arrayOf())
                }.toThrow<AssertionError> { messageToContain("$valueDescr: \"nada\"") }
            }
            it("${messageToContain.name} - does not throw if the assertion holds") {
                expect(throwable).messageContainsFun(" ", arrayOf())
            }
        }

        context("Throwable.message is not empty/blank") {
            val throwable: Throwable = IllegalArgumentException("1 and 2.3 with extra z results in hello")

            messageFunctions.forEach { (name, messageFun, _) ->
                it("$name - throws an AssertionError if the assertion does not hold") {
                    expect {
                        expect(throwable).messageFun { toEqual("hello") }
                    }.toThrow<AssertionError> { messageToContain("$toEqualDescr: \"hello\"") }
                }

                it("$name - does not throw if the assertion holds") {
                    expect(throwable).messageFun { toContain("hello") }
                }
            }

            it("${messageToContain.name} - throws an AssertionError if the assertion does not hold") {
                expect {
                    expect(throwable).messageContainsFun("nada", arrayOf())
                }.toThrow<AssertionError> { messageToContain("$valueDescr: \"nada\"") }
            }
            it("${messageToContain.name} - does not throw if the assertion holds") {
                expect(throwable).messageContainsFun(1, arrayOf(2.3, 'z', "hello"))
            }
        }

        it("${messageToContain.name} - throws an IllegalArgumentException if an object is passed") {
            val throwable: Throwable = IndexOutOfBoundsException()
            expect {
                expect(throwable).messageContainsFun(Pair(1, 2), arrayOf())
            }.toThrow<IllegalArgumentException>()
        }
    }

    describeFun(causeFeature, cause) {
        val causeFunctions = unifySignatures(causeFeature, cause)

        context("Throwable.cause is not null") {
            val exceptionCause = IllegalArgumentException("Hello from the Clause")
            val throwable: Throwable =
                RuntimeException("Outer exception message", exceptionCause)

            causeFunctions.forEach { (name, causeFun) ->
                it("$name - does not throw if the assertion holds") {
                    expect(throwable).causeFun { toEqual(exceptionCause) }
                }

                it("$name - throws an AssertionError") {
                    expect {
                        expect(throwable).causeFun { messageToContain("WRONG message") }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE.getDefault() + ": java.lang.IllegalArgumentException",
                            "$valueDescr: \"WRONG message\""
                        )
                    }
                }

                it("$name  - throws if wrong type is expected") {
                    val throwableWithDifferentCauseType: Throwable =
                        RuntimeException(
                            "Outer exception message",
                            UnsupportedOperationException("Cause exception: UNSUPPORTED OPERATION")
                        )
                    expect {
                        expect(throwableWithDifferentCauseType).causeFun { messageToContain("Cause exception") }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            OCCURRED_EXCEPTION_PROPERTIES.getDefault().format(
                                UnsupportedOperationException::class.simpleName!!
                            )
                        )
                    }
                }
            }
        }

        context("Throwable.cause is null") {
            val throwable: Throwable = RuntimeException("Outer exception message")
            causeFunctions.forEach { (name, causeFun) ->
                it("$name - throws an AssertionError") {
                    expect {
                        expect(throwable).causeFun { messageToContain("Hello") }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            DescriptionThrowableExpectation.HAS_NO_CAUSE.getDefault(),
                            IllegalArgumentException::class.fullName
                        )
                    }
                }
            }
        }

    }
})
