package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloMyNameIsRobert
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.noMatchFoundDescr
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

@Suppress("FunctionName")
abstract class AbstractCharSequenceToContainNotToContainExpectationsTest(
    private val toContainSpec: Fun2<CharSequence, Any, Array<out Any>>,
    private val notToContainSpec: Fun2<CharSequence, Any, Array<out Any>>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toContainSpec.forSubjectLessTest("hello", arrayOf()),
        notToContainSpec.forSubjectLessTest("hello", arrayOf())
    )

    fun Expect<CharSequence>.toContainFun(t: String, vararg tX: String) = toContainSpec.invoke(this, t, tX)

    fun Expect<CharSequence>.notToContainFun(t: String, vararg tX: String) = notToContainSpec.invoke(this, t, tX)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    @TestFactory
    fun toContain_notToContain__empty_string() = testFactory(toContainSpec, notToContainSpec) {
        itFun(toContainSpec, "'Hello' throws AssertionError") {
            expect {
                expect("" as CharSequence).toContainFun("Hello")
            }.toThrow<AssertionError> {
                messageToContain(
                    "$rootBulletPoint$toContainDescr: $separator" +
                        "$valueWithIndent: \"Hello\"",
                    noMatchFoundDescr
                )
            }
        }

        itFun(notToContainSpec, "'Hello' does not throw") {
            expect("" as CharSequence).notToContainFun("Hello")
        }
    }

    @TestFactory
    fun toContain_notToContain() = testFactory(toContainSpec, notToContainSpec) {
        describe("text '$helloMyNameIsRobert'") {
            itFun(toContainSpec, "'Hello' does not throw") {
                expect(helloMyNameIsRobert).toContainFun("Hello")
            }
            itFun(notToContainSpec, "'Hello' throws AssertionError") {
                expect {
                    expect(helloMyNameIsRobert).notToContainFun("Hello")
                }.toThrow<AssertionError> { messageToContain(notToContainDescr, "$valueWithIndent: \"Hello\"") }
            }

            itFun(toContainSpec, "'Hello' and 'Robert' does not throw") {
                expect(helloMyNameIsRobert).toContainFun("Hello", "Robert")
            }
            itFun(notToContainSpec, "'Hello' and 'Robert' throws AssertionError") {
                expect {
                    expect(helloMyNameIsRobert).notToContainFun("Hello", "Robert")
                }.toThrow<AssertionError> {
                    messageToContain(
                        notToContainDescr,
                        "$valueWithIndent: \"Hello\"",
                        "$valueWithIndent: \"Robert\""
                    )
                }
            }

            itFun(toContainSpec, "'hello' and 'robert' throws AssertionError") {
                expect {
                    expect(helloMyNameIsRobert).toContainFun("hello", "robert")
                }.toThrow<AssertionError> {
                    message {
                        this.toContain.exactly(2).value(
                            noMatchFoundDescr
                        )
                        this.toContain.exactly(1).values(
                            "$rootBulletPoint$toContainDescr: $separator",
                            "$valueWithIndent: \"hello\"",
                            "$valueWithIndent: \"robert\""
                        )
                    }
                }
            }
            itFun(notToContainSpec, "'hello' and 'robert' does not throw") {
                expect(helloMyNameIsRobert).notToContainFun("hello", "robert")
            }

            itFun(toContainSpec, "'notInThere' throws AssertionError") {
                expect {
                    expect(helloMyNameIsRobert).toContainFun("notInThere")
                }.toThrow<AssertionError> { messageToContain(toContainDescr, "$valueWithIndent: \"notInThere\"") }
            }
            itFun(notToContainSpec, "'notInThere' does not throw") {
                expect(helloMyNameIsRobert).notToContainFun("notInThere")
            }

            itFun(toContainSpec, "'notInThere' and 'neitherInThere' throws AssertionError") {
                expect {
                    expect(helloMyNameIsRobert).toContainFun("notInThere", "neitherInThere")
                }.toThrow<AssertionError> {
                    messageToContain(
                        toContainDescr,
                        "$valueWithIndent: \"notInThere\"",
                        "$valueWithIndent: \"neitherInThere\""
                    )
                }
            }
            itFun(notToContainSpec, "'notInThere' and 'neitherInThere' does not throw") {
                expect(helloMyNameIsRobert).notToContainFun("notInThere", "neitherInThere")
            }

            itFun(toContainSpec, "'Hello' and 'notInThere' throws AssertionError mentioning only 'Hello'") {
                expect {
                    expect(helloMyNameIsRobert).toContainFun("Hello", "notInThere")
                }.toThrow<AssertionError> {
                    message {
                        toContain(toContainDescr, "$valueWithIndent: \"notInThere\"")
                        notToContain("$valueWithIndent: \"Hello\"")
                    }
                }
            }
            itFun(notToContainSpec, "'Hello' and 'notInThere' throws AssertionError mentioning only 'notInThere'") {
                expect {
                    expect(helloMyNameIsRobert).notToContainFun("Hello", "notInThere")
                }.toThrow<AssertionError> {
                    message {
                        toContain(notToContainDescr, "$valueWithIndent: \"Hello\"")
                        notToContain("$valueWithIndent: \"notInThere\"")
                    }
                }
            }
        }
    }


    @TestFactory
    fun toContain_notToContain__search_twice_for_the_same_search_string() = testFactory(
        toContainSpec, notToContainSpec
    ) {
        itFun(toContainSpec, "'Hello' and 'Hello' does not throw") {
            expect(helloMyNameIsRobert).toContainFun("Hello", "Hello")
        }

        itFun(notToContainSpec, "'notInThere' and 'notInThere' does not throw") {
            expect(helloMyNameIsRobert).notToContainFun("notInThere", "notInThere")
        }
    }

    @TestFactory
    fun toContain_notToContain__err_msg_feature_assertion_about_a_persons_name() = testFactory(
        toContainSpec, notToContainSpec
    ) {
        data class Person(val name: CharSequence)

        val person = Person("Robert Stoll")
        val nameWithArrow = "${featureArrow}name"

        itFun(toContainSpec, "'treboR' and 'llotS' - error message toContain '$nameWithArrow' exactly once") {
            expect {
                expect(person)._logic.appendAsGroup {
                    feature(Person::name).toContainFun("treboR", "llotS")
                }
            }.toThrow<AssertionError> {
                message { this.toContain.exactly(1).value(nameWithArrow) }
            }
        }

        itFun(notToContainSpec, "'Robert' and 'Stoll' - error message toContain '$nameWithArrow' exactly once") {
            expect {
                expect(person)._logic.appendAsGroup {
                    feature(Person::name).notToContainFun("Robert", "Stoll")
                }
            }.toThrow<AssertionError> {
                message { this.toContain.exactly(1).value(nameWithArrow) }
            }
        }
    }

    companion object {
        val exactly = DescriptionCharSequenceExpectation.EXACTLY.getDefault()
        val atLeast = DescriptionCharSequenceExpectation.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceExpectation.AT_MOST.getDefault()
        val value = DescriptionCharSequenceExpectation.VALUE.getDefault()
        val toContainDescr = DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault()
        val notToContainDescr = DescriptionCharSequenceExpectation.NOT_TO_CONTAIN.getDefault()
        val separator = lineSeparator
    }
}
