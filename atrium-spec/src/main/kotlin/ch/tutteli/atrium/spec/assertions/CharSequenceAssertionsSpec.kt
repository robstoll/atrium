package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class CharSequenceAssertionsSpec(
    verbs: IAssertionVerbFactory,
    containsDefaultTranslationOfPair: Pair<String, IAssertionPlant<CharSequence>.(ITranslatable, Array<out ITranslatable>) -> IAssertionPlant<CharSequence>>,
    containsNotDefaultTranslationOfPair: Pair<String, IAssertionPlant<CharSequence>.(ITranslatable, Array<out ITranslatable>) -> IAssertionPlant<CharSequence>>,
    isEmptyPair: Pair<String, IAssertionPlant<CharSequence>.() -> IAssertionPlant<CharSequence>>,
    isNotEmptyPair: Pair<String, IAssertionPlant<CharSequence>.() -> IAssertionPlant<CharSequence>>,
    startsWithPair: Pair<String, IAssertionPlant<CharSequence>.(CharSequence) -> IAssertionPlant<CharSequence>>,
    startsNotWithPair: Pair<String, IAssertionPlant<CharSequence>.(CharSequence) -> IAssertionPlant<CharSequence>>,
    endsWithPair: Pair<String, IAssertionPlant<CharSequence>.(CharSequence) -> IAssertionPlant<CharSequence>>,
    endsNotWithPair: Pair<String, IAssertionPlant<CharSequence>.(CharSequence) -> IAssertionPlant<CharSequence>>
) : Spek({

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val text = "hello my name is robert"
    val fluent = assert(text)

    val (containsDefaultTranslationOf, containsDefaultTranslationOfFunArr) = containsDefaultTranslationOfPair
    val (containsNotDefaultTranslationOf, containsNotDefaultTranslationOfFunArr) = containsNotDefaultTranslationOfPair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair
    val (startsWith, startsWithFun) = startsWithPair
    val (startsNotWith, startsNotWithFun) = startsNotWithPair
    val (endsWith, endsWithFun) = endsWithPair
    val (endsNotWith, endsNotWithFun) = endsNotWithPair

    fun IAssertionPlant<CharSequence>.containsDefaultTranslationOfFun(t: ITranslatable, vararg tX: ITranslatable)
        = containsDefaultTranslationOfFunArr(t, tX)

    fun IAssertionPlant<CharSequence>.containsNotDefaultTranslationOfFun(t: ITranslatable, vararg tX: ITranslatable)
        = containsNotDefaultTranslationOfFunArr(t, tX)

    describe("fun $containsDefaultTranslationOf and $containsNotDefaultTranslationOf") {

        context("text '$text' and translatables ${TestTranslatable.HELLO} (${TestTranslatable.HELLO.getDefault()}) and ${TestTranslatable.WELCOME} (${TestTranslatable.WELCOME.getDefault()})") {
            test("$containsDefaultTranslationOf ${TestTranslatable.HELLO} does not throw") {
                fluent.containsDefaultTranslationOfFun(TestTranslatable.HELLO)
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.HELLO} throws AssertionError") {
                expect {
                    fluent.containsNotDefaultTranslationOfFun(TestTranslatable.HELLO)
                }.toThrow<AssertionError>()
            }

            test("$containsDefaultTranslationOf ${TestTranslatable.WELCOME} throws AssertionError") {
                expect {
                    fluent.containsDefaultTranslationOfFun(TestTranslatable.WELCOME)
                }.toThrow<AssertionError>()
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.WELCOME} does not throw") {
                fluent.containsNotDefaultTranslationOfFun(TestTranslatable.WELCOME)
            }

            test("$containsDefaultTranslationOf ${TestTranslatable.HELLO} and ${TestTranslatable.WELCOME}, throws AssertionError") {
                expect {
                    fluent.containsDefaultTranslationOfFun(TestTranslatable.HELLO, TestTranslatable.WELCOME)
                }.toThrow<AssertionError>().message {
                    contains(DescriptionCharSequenceAssertion.CONTAINS.getDefault() + ": \"" + TestTranslatable.WELCOME.getDefault() + "\"")
                    containsNot(DescriptionCharSequenceAssertion.CONTAINS.getDefault() + ": \"" + TestTranslatable.HELLO.getDefault() + "\"")
                }
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.HELLO} and ${TestTranslatable.WELCOME}, throws AssertionError") {
                expect {
                    fluent.containsNotDefaultTranslationOfFun(TestTranslatable.HELLO, TestTranslatable.WELCOME)
                }.toThrow<AssertionError>().message {
                    contains(DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault() + ": \"" + TestTranslatable.HELLO.getDefault() + "\"")
                    containsNot(DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault() + ": \"" + TestTranslatable.WELCOME.getDefault() + "\"")
                }
            }
        }
    }


    describe("fun $isEmpty and $isNotEmpty") {
        context("string is empty") {
            test("$isEmpty does not throw") {
                assert("").isEmptyFun()
                assert(StringBuilder()).isEmptyFun()
                assert(StringBuffer()).isEmptyFun()
            }
            test("$isNotEmpty throws an AssertionError") {
                expect {
                    assert("").isNotEmptyFun()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
                expect {
                    assert(StringBuilder()).isNotEmptyFun()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
                expect {
                    assert(StringBuffer()).isNotEmptyFun()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
            }
        }
        context("string is not empty") {
            val notEmptyString = "not empty string"
            test("$isEmpty throws an AssertionError") {
                expect {
                    assert(notEmptyString).isEmptyFun()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
                expect {
                    assert(StringBuilder(notEmptyString)).isEmptyFun()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
                expect {
                    assert(StringBuffer(notEmptyString)).isEmptyFun()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
            }
            test("$isNotEmpty does not throw") {
                assert(notEmptyString).isNotEmptyFun()
                assert(StringBuilder(notEmptyString)).isNotEmptyFun()
                assert(StringBuffer(notEmptyString)).isNotEmptyFun()
            }
        }
    }

    describe("fun $startsWith and $startsNotWith") {
        context("text '$text'") {
            test("$startsWith 'hello' does not throw") {
                fluent.startsWithFun("hello")
            }
            test("$startsNotWith 'hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWithFun("hello")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(STARTS_NOT_WITH)
            }

            test("$startsWith 'robert' throws an AssertionError") {
                expect {
                    fluent.startsWithFun("goodbye")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(STARTS_WITH)
            }
            test("$startsNotWith 'robert' does not throw") {
                fluent.startsNotWithFun("goodbye")
            }
        }
    }

    describe("fun $endsWith and $endsNotWith") {
        context("text '$text'") {
            test("$endsWith 'hello' throws an AssertionError") {
                expect {
                    fluent.endsWithFun("hello")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(ENDS_WITH)
            }
            test("$endsNotWith 'hello' does not throw") {
                fluent.endsNotWithFun("hello")
            }

            test("$endsWith 'robert' does not throw") {
                fluent.endsWithFun("robert")
            }
            test("$endsNotWith 'robert' throws an AssertionError") {
                expect {
                    fluent.endsNotWithFun("robert")
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
