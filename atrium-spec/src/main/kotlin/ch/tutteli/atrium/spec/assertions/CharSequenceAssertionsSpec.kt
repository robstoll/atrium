package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

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
) : CharSequenceContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<CharSequence>(
        containsDefaultTranslationOfPair.first to mapToCreateAssertion { containsDefaultTranslationOfPair.second(this, Untranslatable(""), arrayOf()) },
        containsNotDefaultTranslationOfPair.first to mapToCreateAssertion { containsNotDefaultTranslationOfPair.second(this, Untranslatable(""), arrayOf()) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) },
        startsWithPair.first to mapToCreateAssertion { startsWithPair.second(this, "") },
        startsNotWithPair.first to mapToCreateAssertion { startsNotWithPair.second(this, "") },
        endsWithPair.first to mapToCreateAssertion { endsWithPair.second(this, "") },
        endsNotWithPair.first to mapToCreateAssertion { endsNotWithPair.second(this, "") }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<String>(verbs,
        checkingTriple(containsDefaultTranslationOfPair.first, { containsDefaultTranslationOfPair.second(this, Untranslatable("a"), arrayOf()) }, "a", "b"),
        checkingTriple(containsNotDefaultTranslationOfPair.first, { containsNotDefaultTranslationOfPair.second(this, Untranslatable("a"), arrayOf()) }, "b", "a"),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, "", "not empty"),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, "not empty", ""),
        checkingTriple(startsWithPair.first, { startsWithPair.second(this, "a") }, "abc", "xyz"),
        checkingTriple(startsNotWithPair.first, { startsNotWithPair.second(this, "a") }, "xyz", "abc"),
        checkingTriple(endsWithPair.first, { endsWithPair.second(this, "c") }, "abc", "xyz"),
        checkingTriple(endsNotWithPair.first, { endsNotWithPair.second(this, "c") }, "xyz", "abc")
    ) {})

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
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

    val containsNot = DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault()
    val hello = TestTranslatable.HELLO.getDefault()
    val welcome = TestTranslatable.WELCOME.getDefault()
    describe("fun $containsDefaultTranslationOf and $containsNotDefaultTranslationOf") {

        context("text '$text' and translatables ${TestTranslatable.HELLO} ($hello) and ${TestTranslatable.WELCOME} ($welcome)") {
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
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsDescr: \"$welcome\"")
                        containsNot("$containsDescr: \"$hello\"")
                    }
                }
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.HELLO} and ${TestTranslatable.WELCOME}, throws AssertionError") {
                expect {
                    fluent.containsNotDefaultTranslationOfFun(TestTranslatable.HELLO, TestTranslatable.WELCOME)
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsNot: \"$hello\"")
                        containsNot("$containsNot: \"$welcome\"")
                    }
                }
            }
        }
    }


    val isNot = DescriptionBasic.IS_NOT.getDefault()
    val itIs = DescriptionBasic.IS.getDefault()
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
                }.toThrow<AssertionError> { message { endsWith("$isNot: empty") } }
                expect {
                    assert(StringBuilder()).isNotEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$isNot: empty") } }
                expect {
                    assert(StringBuffer()).isNotEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$isNot: empty") } }
            }
        }
        context("string is not empty") {
            val notEmptyString = "not empty string"
            test("$isEmpty throws an AssertionError") {
                expect {
                    assert(notEmptyString).isEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$itIs: empty") } }
                expect {
                    assert(StringBuilder(notEmptyString)).isEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$itIs: empty") } }
                expect {
                    assert(StringBuffer(notEmptyString)).isEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$itIs: empty") } }
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
            test("$startsWith 'Hello' does not throw") {
                fluent.startsWithFun("Hello")
            }
            test("$startsNotWith 'Hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWithFun("Hello")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(STARTS_NOT_WITH) } }
            }

            test("$startsWith 'Robert' throws an AssertionError") {
                expect {
                    fluent.startsWithFun("goodbye")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(STARTS_WITH) } }
            }
            test("$startsNotWith 'Robert' does not throw") {
                fluent.startsNotWithFun("goodbye")
            }
        }
    }

    describe("fun $endsWith and $endsNotWith") {
        context("text '$text'") {
            test("$endsWith 'Hello' throws an AssertionError") {
                expect {
                    fluent.endsWithFun("Hello")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(ENDS_WITH) } }
            }
            test("$endsNotWith 'Hello' does not throw") {
                fluent.endsNotWithFun("Hello")
            }

            test("$endsWith 'Robert' does not throw") {
                fluent.endsWithFun("Robert")
            }
            test("$endsNotWith 'Robert' throws an AssertionError") {
                expect {
                    fluent.endsNotWithFun("Robert")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(ENDS_NOT_WITH) } }
            }
        }
    }
}) {
    private enum class TestTranslatable(override val value: String) : ISimpleTranslatable {
        HELLO("Hello"),
        WELCOME("Welcome")
    }
}
