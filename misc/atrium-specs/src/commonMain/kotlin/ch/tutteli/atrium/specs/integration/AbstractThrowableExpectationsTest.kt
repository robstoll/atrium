package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_PROPERTIES

@Suppress("FunctionName")
abstract class AbstractThrowableExpectationsTest(
    private val messageFeatureSpec: Feature0<Throwable, String>,
    private val messageSpec: Fun1<Throwable, Expect<String>.() -> Unit>,
    private val messageToContainSpec: Fun2<Throwable, Any, Array<out Any>>,

    private val causeFeatureSpec: Feature0<Throwable, IllegalArgumentException>,
    private val causeSpec: Feature1<Throwable, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>
) : ExpectationFunctionBaseTest() {

    private val valueDescr = DescriptionCharSequenceExpectation.VALUE.getDefault()

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            messageFeatureSpec.forSubjectLessTest(),
            messageSpec.forSubjectLessTest { toEqual("hello") },
            messageToContainSpec.forSubjectLessTest("hello", arrayOf())
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            RuntimeException("hello"),
            messageSpec.forExpectationCreatorTest("$toEqualDescr: hello") { toEqual("hello") }
        ),
        ExpectationCreatorTestData<Throwable>(
            RuntimeException("Outer exception message", IllegalArgumentException("Hello from the Clause")),
            ExpectationCreatorTriple(
                causeSpec.name,
                "$valueDescr: \"Hello from the Clause\"",
                { apply { causeSpec.invoke(this) { messageToContain("Hello from the Clause") } } },
                { apply { causeSpec.invoke(this) {} } }
            )
        )
    )

    @TestFactory
    fun message_and_messageToContain() = testFactory(messageFeatureSpec, messageSpec, messageToContainSpec) {
        val messageFunctions = unifySignatures(messageFeatureSpec, messageSpec)
        val messageContainsFun = messageToContainSpec.lambda

        describe("Throwable.message is null") {
            val throwable: Throwable = IllegalArgumentException()

            messageFunctions.forEach { (name, messageFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubExpectationIf(hasExtraHint)) {
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

            it("${messageToContainSpec.name} - throws an AssertionError which shows intended sub assertion") {

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

        describe("Throwable.message is empty") {
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
            it("${messageToContainSpec.name} - throws an AssertionError if the assertion does not hold") {
                expect {
                    expect(throwable).messageContainsFun("nada", arrayOf())
                }.toThrow<AssertionError> { messageToContain("$valueDescr: \"nada\"") }
            }
            it("${messageToContainSpec.name} - throws IllegalArgumentException if empty string is passed") {
                expect {
                    expect(throwable).messageContainsFun("", arrayOf())
                }.toThrow<IllegalArgumentException>()
            }
        }

        describe("Throwable.message is blank") {
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
            it("${messageToContainSpec.name} - throws an AssertionError if the assertion does not hold") {
                expect {
                    expect(throwable).messageContainsFun("nada", arrayOf())
                }.toThrow<AssertionError> { messageToContain("$valueDescr: \"nada\"") }
            }
            it("${messageToContainSpec.name} - does not throw if the assertion holds") {
                expect(throwable).messageContainsFun(" ", arrayOf())
            }
        }

        describe("Throwable.message is not empty/blank") {
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

            it("${messageToContainSpec.name} - throws an AssertionError if the assertion does not hold") {
                expect {
                    expect(throwable).messageContainsFun("nada", arrayOf())
                }.toThrow<AssertionError> { messageToContain("$valueDescr: \"nada\"") }
            }
            it("${messageToContainSpec.name} - does not throw if the assertion holds") {
                expect(throwable).messageContainsFun(1, arrayOf(2.3, 'z', "hello"))
            }
        }

        it("${messageToContainSpec.name} - throws an IllegalArgumentException if an object is passed") {
            val throwable: Throwable = IndexOutOfBoundsException()
            expect {
                expect(throwable).messageContainsFun(Pair(1, 2), arrayOf())
            }.toThrow<IllegalArgumentException>()
        }
    }

    @TestFactory
    fun cause() = testFactoryForFeatureNonFeature(causeFeatureSpec, causeSpec) { name, causeFun, _ ->
        describe("Throwable.cause is not null") {
            val exceptionCause = IllegalArgumentException("Hello from the Clause")
            val throwable: Throwable =
                RuntimeException("Outer exception message", exceptionCause)

            it("$name - does not throw if the assertion holds") {
                expect(throwable).causeFun { toEqual(exceptionCause) }
            }

            it("$name - throws an AssertionError") {
                expect {
                    expect(throwable).causeFun { messageToContain("WRONG message") }
                }.toThrow<AssertionError> {
                    messageToContain(
                        DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE.getDefault() + ": " + IllegalArgumentException::class.fullName,
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

        describe("Throwable.cause is null") {
            val throwable: Throwable = RuntimeException("Outer exception message")
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
