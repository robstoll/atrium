package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.text
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.noMatchFoundDescr

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
    fun toContain__subject_empty_string() = testFactory(toContainSpec) {
        val fluentEmptyString = expect("" as CharSequence)

        it("${toContainSpec.name} 'Hello' throws AssertionError") {
            expect {
                fluentEmptyString.toContainFun("Hello")
            }.toThrow<AssertionError> {
                messageToContain(
                    "$rootBulletPoint$toContainDescr: $separator" +
                        "$valueWithIndent: \"Hello\"",
                    noMatchFoundDescr
                )
            }
        }
    }

    @TestFactory
    fun notToContain__subject_empty_string() = testFactory(notToContainSpec) {
        val fluentEmptyString = expect("" as CharSequence)

        it("${notToContainSpec.name} 'Hello' does not throw") {
            fluentEmptyString.notToContainFun("Hello")
        }
    }

    @TestFactory
    fun toContain__subject_text_search_for_Hello() = testFactory(toContainSpec) {
        it("${toContainSpec.name} 'Hello' does not throw") {
            expect(text).toContainFun("Hello")
        }
    }

    @TestFactory
    fun toContain__subject_text_search_for_Hello_and_Robert() = testFactory(toContainSpec) {
        it("${toContainSpec.name} 'Hello' and 'Robert' does not throw") {
            expect(text).toContainFun("Hello", "Robert")
        }
        it("${toContainSpec.name} 'hello' and 'robert' throws AssertionError") {
            expect {
                expect(text).toContainFun("hello", "robert")
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
    }

    @TestFactory
    fun notToContain__subject_text_search_for_Hello() = testFactory(notToContainSpec) {
        it("${notToContainSpec.name} 'Hello' throws AssertionError") {
            expect {
                expect(text).notToContainFun("Hello")
            }.toThrow<AssertionError> { messageToContain(notToContainDescr, "$valueWithIndent: \"Hello\"") }
        }
    }

    @TestFactory
    fun notToContain__subject_text_search_for_Hello_and_Robert() = testFactory(notToContainSpec) {
        it("${notToContainSpec.name} 'Hello' and 'Robert' throws AssertionError") {
            expect {
                expect(text).notToContainFun("Hello", "Robert")
            }.toThrow<AssertionError> {
                messageToContain(
                    notToContainDescr,
                    "$valueWithIndent: \"Hello\"",
                    "$valueWithIndent: \"Robert\""
                )
            }
        }
        it("${notToContainSpec.name} 'hello' and 'robert' does not throw") {
            expect(text).notToContainFun("hello", "robert")
        }
    }

    @TestFactory
    fun toContain__subject_text_search_for_notInThere_and_neitherInThere() = testFactory(toContainSpec) {
        it("${toContainSpec.name} 'notInThere' and 'neitherInThere' throws AssertionError") {
            expect {
                expect(text).toContainFun("notInThere", "neitherInThere")
            }.toThrow<AssertionError> {
                messageToContain(
                    toContainDescr,
                    "$valueWithIndent: \"notInThere\"",
                    "$valueWithIndent: \"neitherInThere\""
                )
            }
        }
    }

    @TestFactory
    fun notToContain__subject_text_search_for_notInThere_and_neitherInThere() = testFactory(notToContainSpec) {
        it("${notToContainSpec.name} 'notInThere' and 'neitherInThere' does not throw") {
            expect(text).notToContainFun("notInThere", "neitherInThere")
        }
    }

    @TestFactory
    fun toContain__subject_text_search_for_notInThere() = testFactory(toContainSpec) {
        it("${toContainSpec.name} 'notInThere' throws AssertionError") {
            expect {
                expect(text).toContainFun("notInThere")
            }.toThrow<AssertionError> { messageToContain(toContainDescr, "$valueWithIndent: \"notInThere\"") }
        }
    }

    @TestFactory
    fun toContain__subject_text_search_for_Hello_and_notInThere() = testFactory(toContainSpec) {
        it("${toContainSpec.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'Hello'") {
            expect {
                expect(text).toContainFun("Hello", "notInThere")
            }.toThrow<AssertionError> {
                message {
                    toContain(toContainDescr, "$valueWithIndent: \"notInThere\"")
                    notToContain("$valueWithIndent: \"Hello\"")
                }
            }
        }
    }

    @TestFactory
    fun notToContain__subject_text_search_for_notInThere() = testFactory(notToContainSpec) {
        it("${notToContainSpec.name} 'notInThere' does not throw") {
            expect(text).notToContainFun("notInThere")
        }
    }

    @TestFactory
    fun notToContain__subject_text_search_for_Hello_and_notInThere() = testFactory(notToContainSpec) {
        it("${notToContainSpec.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'notInThere'") {
            expect {
                expect(text).notToContainFun("Hello", "notInThere")
            }.toThrow<AssertionError> {
                message {
                    toContain(notToContainDescr, "$valueWithIndent: \"Hello\"")
                    notToContain("$valueWithIndent: \"notInThere\"")
                }
            }
        }
    }

    @TestFactory
    fun toContain__subject_text_search_for_Hello_and_Hello() = testFactory(toContainSpec) {
        it("${toContainSpec.name} 'Hello' and 'Hello' (searching twice in the same assertion) does not throw") {
            expect(text).toContainFun("Hello", "Hello")
        }
    }

    @TestFactory
    fun notToContain__subject_text_search_for_notInThere_and_notInThere() = testFactory(notToContainSpec) {
        it("${notToContainSpec.name} 'notInThere' and 'notInThere' does not throw") {
            expect(text).notToContainFun("notInThere", "notInThere")
        }
    }

    @TestFactory
    fun toContain__error_message_feature_assertion_about_a_persons_name() = testFactory(toContainSpec) {
        data class Person(val name: CharSequence)

        val person = Person("Robert Stoll")

        val nameWithArrow = "${featureArrow}name"
        it("${toContainSpec.name} 'treboR' and 'llotS' - error message toContain '$nameWithArrow' exactly once") {
            expect {
                expect(person)._logic.appendAsGroup {
                    feature(Person::name).toContainFun("treboR", "llotS")
                }
            }.toThrow<AssertionError> {
                message { this.toContain.exactly(1).value(nameWithArrow) }
            }
        }
    }

    @TestFactory
    fun notToContain__error_message_feature_assertion_about_a_persons_name() = testFactory(notToContainSpec) {
        data class Person(val name: CharSequence)

        val person = Person("Robert Stoll")

        val nameWithArrow = "${featureArrow}name"
        it("${notToContainSpec.name} 'Robert' and 'Stoll' - error message toContain '$nameWithArrow' exactly once") {
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
