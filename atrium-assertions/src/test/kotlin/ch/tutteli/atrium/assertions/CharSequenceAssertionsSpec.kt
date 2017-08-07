package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

object CharSequenceAssertionsSpec : Spek({
    val text = "hello my name is robert"
    val fluent = assert(text)

    val containsDefaultTranslationOf = fluent::containsDefaultTranslationOf.name
    val containsNotDefaultTranslationOf = fluent::containsNotDefaultTranslationOf.name
    describe("fun $containsDefaultTranslationOf and $containsNotDefaultTranslationOf") {

        context("text '$text' and translatables ${TestTranslatable.HELLO} (${TestTranslatable.HELLO.getDefault()}) and ${TestTranslatable.WELCOME} (${TestTranslatable.WELCOME.getDefault()})") {
            test("$containsDefaultTranslationOf ${TestTranslatable.HELLO} does not throw") {
                fluent.containsDefaultTranslationOf(TestTranslatable.HELLO)
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.HELLO} throws AssertionError") {
                expect {
                    fluent.containsNotDefaultTranslationOf(TestTranslatable.HELLO)
                }.toThrow<AssertionError>()
            }

            test("$containsDefaultTranslationOf ${TestTranslatable.WELCOME} throws AssertionError") {
                expect {
                    fluent.containsDefaultTranslationOf(TestTranslatable.WELCOME)
                }.toThrow<AssertionError>()
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.WELCOME} does not throw") {
                fluent.containsNotDefaultTranslationOf(TestTranslatable.WELCOME)
            }

            test("$containsDefaultTranslationOf ${TestTranslatable.HELLO} and ${TestTranslatable.WELCOME}, throws AssertionError") {
                expect {
                    fluent.containsDefaultTranslationOf(TestTranslatable.HELLO, TestTranslatable.WELCOME)
                }.toThrow<AssertionError>().message {
                    contains(DescriptionCharSequenceAssertion.CONTAINS.getDefault() + ": \"" + TestTranslatable.WELCOME.getDefault() + "\"")
                    containsNot(DescriptionCharSequenceAssertion.CONTAINS.getDefault() + ": \"" + TestTranslatable.HELLO.getDefault() + "\"")
                }
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.HELLO} and ${TestTranslatable.WELCOME}, throws AssertionError") {
                expect {
                    fluent.containsNotDefaultTranslationOf(TestTranslatable.HELLO, TestTranslatable.WELCOME)
                }.toThrow<AssertionError>().message {
                    contains(DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault() + ": \"" + TestTranslatable.HELLO.getDefault() + "\"")
                    containsNot(DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault() + ": \"" + TestTranslatable.WELCOME.getDefault() + "\"")
                }
            }
        }
    }


    describe("fun ${fluent::isEmpty.name} and ${fluent::isNotEmpty.name}") {
        context("string is empty") {
            test("${fluent::isEmpty.name} does not throw") {
                assert("").isEmpty()
                assert(StringBuilder()).isEmpty()
                assert(StringBuffer()).isEmpty()
            }
            test("${fluent::isNotEmpty.name} throws an AssertionError") {
                expect {
                    assert("").isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
                expect {
                    assert(StringBuilder()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
                expect {
                    assert(StringBuffer()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
            }
        }
        context("string is not empty") {
            val notEmptyString = "not empty string"
            test("${fluent::isEmpty.name} throws an AssertionError") {
                expect {
                    assert(notEmptyString).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
                expect {
                    assert(StringBuilder(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
                expect {
                    assert(StringBuffer(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
            }
            test("${fluent::isNotEmpty.name} does not throw") {
                assert(notEmptyString).isNotEmpty()
                assert(StringBuilder(notEmptyString)).isNotEmpty()
                assert(StringBuffer(notEmptyString)).isNotEmpty()
            }
        }
    }

    describe("fun ${fluent::startsWith.name} and ${fluent::startsNotWith.name}") {
        context("text '$text'") {
            test("${fluent::startsWith.name} 'hello' does not throw") {
                fluent.startsWith("hello")
            }
            test("${fluent::startsNotWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWith("hello")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(STARTS_NOT_WITH)
            }

            test("${fluent::startsWith.name} 'robert' throws an AssertionError") {
                expect {
                    fluent.startsWith("goodbye")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(STARTS_WITH)
            }
            test("${fluent::startsNotWith.name} 'robert' does not throw") {
                fluent.startsNotWith("goodbye")
            }
        }
    }

    describe("fun ${fluent::endsWith.name} and ${fluent::endsNotWith.name}") {
        context("text '$text'") {
            test("${fluent::endsWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.endsWith("hello")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(ENDS_WITH)
            }
            test("${fluent::endsNotWith.name} 'hello' does not throw") {
                fluent.endsNotWith("hello")
            }

            test("${fluent::endsWith.name} 'robert' does not throw") {
                fluent.endsWith("robert")
            }
            test("${fluent::endsNotWith.name} 'robert' throws an AssertionError") {
                expect {
                    fluent.endsNotWith("robert")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(ENDS_NOT_WITH)
            }
        }
    }
}) {
    private enum class TestTranslatable(override val value: String) : ISimpleTranslatable {
        HELLO("hello"),
        WELCOME("welcome")
    }
}
