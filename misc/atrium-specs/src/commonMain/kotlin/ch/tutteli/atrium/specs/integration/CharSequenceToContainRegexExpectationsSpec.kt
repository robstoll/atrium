package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.*
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.Companion.IGNORING_CASE
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceToContainRegexExpectationsSpec(
    toContainRegex: String,
    toContainAtLeastPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    toContainAtLeastIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    //TODO usually the shortcut is tested individually on a level lower and not expected as we did it here (search for ShortcutSpec or similar)
    toContainShortcutPair: Pair<(String, String) -> String, Fun2<CharSequence, String, Array<out String>>>,
    toContainAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    toContainAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    val toContainAtLeast = toContainAtLeastPair.second
    val toContainAtLeastIgnoringCase = toContainAtLeastIgnoringCasePair.second
    val toContainShortcut = toContainShortcutPair.second
    val toContainAtMost = toContainAtMostPair.second
    val toContainAtMostIgnoringCase = toContainAtMostIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        toContainAtLeast.forSubjectLessTest(2, "a|b", arrayOf()),
        toContainAtLeastIgnoringCase.forSubjectLessTest(2, "a|b", arrayOf()),
        toContainShortcut.forSubjectLessTest("a|b", arrayOf()),
        toContainAtMost.forSubjectLessTest(2, "a|b", arrayOf()),
        toContainAtMostIgnoringCase.forSubjectLessTest(2, "a|b", arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)


    val text: CharSequence = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val roberto = "Roberto?"

    fun Expect<CharSequence>.toContainAtLeastFun(atLeast: Int, a: String, vararg aX: String) =
        toContainAtLeast(this, atLeast, a, aX)

    fun Expect<CharSequence>.toContainAtLeastIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String) =
        toContainAtLeastIgnoringCase(this, atLeast, a, aX)


    fun Expect<CharSequence>.toContainShortcutFun(a: String, vararg aX: String) = toContainShortcut(this, a, aX)

    fun Expect<CharSequence>.toContainAtMostFun(atLeast: Int, a: String, vararg aX: String) =
        toContainAtMost(this, atLeast, a, aX)

    fun Expect<CharSequence>.toContainAtMostIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String) =
        toContainAtMostIgnoringCase(this, atLeast, a, aX)

    describeFun(toContainRegex) {
        context("throws an ${IllegalArgumentException::class.simpleName}") {

            it("if an erroneous pattern is passed to `${toContainAtLeast.name}` as first argument") {
                expect {
                    expect("a" as CharSequence).toContainAtLeastFun(1, "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }
            it("if an erroneous pattern is passed to `${toContainAtLeast.name}` as second argument") {
                expect {
                    expect("a" as CharSequence).toContainAtLeastFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }
            it("if an erroneous pattern is passed to `${toContainAtLeastIgnoringCase.name}` as first argument") {
                expect {
                    expect("a" as CharSequence).toContainAtLeastIgnoringCaseFun(1, "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }
            it("if an erroneous pattern is passed to `${toContainAtLeastIgnoringCase.name}` as second argument") {
                expect {
                    expect("a" as CharSequence).toContainAtLeastIgnoringCaseFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }

            it("if an erroneous pattern is passed to `${toContainShortcut.name}` as first argument") {
                expect {
                    expect("a" as CharSequence).toContainShortcutFun("notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }
            it("if an erroneous pattern is passed to `${toContainShortcut.name}` as second argument") {
                expect {
                    expect("a" as CharSequence).toContainShortcutFun("h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }

            it("if an erroneous pattern is passed to `${toContainAtMost.name}` as first argument") {
                expect {
                    expect("a" as CharSequence).toContainAtMostFun(2, "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }
            it("if an erroneous pattern is passed to `${toContainAtMost.name}` as second argument") {
                expect {
                    expect("a" as CharSequence).toContainAtMostFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }

            it("if an erroneous pattern is passed to `${toContainAtMostIgnoringCase.name}` as first argument") {
                expect {
                    expect("a" as CharSequence).toContainAtMostIgnoringCaseFun(2, "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }
            it("if an erroneous pattern is passed to `${toContainAtMostIgnoringCase.name}` as second argument") {
                expect {
                    expect("a" as CharSequence).toContainAtMostIgnoringCaseFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException> {
                    messageToContain("Unclosed group")
                }
            }
        }

        context("text $text") {
            it("${toContainAtLeastPair.first("'$hello'", "once")} does not throw") {
                expect(text).toContainAtLeastFun(1, hello)
            }
            it("${toContainAtLeastPair.first("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                expect(text).toContainAtLeastFun(1, hello, hello, hello)
            }
            it("${toContainAtLeastPair.first("'$hello' and '$roberto'", "once")} does not throw") {
                expect(text).toContainAtLeastFun(1, hello, roberto)
            }
            it("${toContainAtLeastPair.first("'${roberto.toLowerCase()}'", "once")} throws AssertionError") {
                expect {
                    expect(text).toContainAtLeastFun(1, roberto.toLowerCase())
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject("\"$text\"")
                        toContainDescr(TO_CONTAIN, "")
                        //TODO 1.3.0 regex should not be wrapped into ""
                        toContainDescr(STRING_MATCHING_REGEX, roberto.toLowerCase())
                        //TODO 1.3.0 expect that it starts with ❗❗ (right now it starts with »)
                        toContain(NOT_FOUND.string)
                    }
                }
            }
            it("${toContainAtLeastIgnoringCasePair.first("'${roberto.toLowerCase()}'", "once")} does not throw") {
                expect(text).toContainAtLeastIgnoringCaseFun(1, roberto.toLowerCase())
            }

            it("${toContainShortcutPair.first("'$hello'", "once")} does not throw") {
                expect(text).toContainShortcutFun(hello)
            }
            it("${toContainShortcutPair.first("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                expect(text).toContainShortcutFun(hello, hello, hello)
            }
            it("${toContainShortcutPair.first("'$hello' and '$roberto'", "once")} does not throw") {
                expect(text).toContainShortcutFun(hello, roberto)
            }

            it("${toContainAtMostPair.first("'[a-z]'", "17 times")} does not throw") {
                expect(text).toContainAtMostFun(17, "[a-z]")
            }
            it("${toContainAtMostIgnoringCasePair.first("'[a-z]'", "19 times")} does not throw") {
                expect(text).toContainAtMostIgnoringCaseFun(19, "[a-z]")
            }
            it("${toContainAtMostIgnoringCasePair.first("'[a-z]' and '[A-Z]'", "19 times")} does not throw") {
                expect(text).toContainAtMostIgnoringCaseFun(19, "[a-z]", "[A-Z]")
            }

            it("${toContainAtMostPair.first("'[a-z]'", "16 times")} throws AssertionError") {
                expect {
                    expect(text).toContainAtMostFun(16, "[a-z]")
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject("\"$text\"")
                        toContainDescr(TO_CONTAIN, "")
                        toContainDescr(STRING_MATCHING_REGEX, "[a-z]")
                        toContainNumberOfMatches(17)
                        notToContain(AT_LEAST.string)
                        toContainDescr(AT_MOST, 16)
                    }
                }
            }
            it("${toContainAtMostIgnoringCasePair.first("'[a-z]'", "18 times")} throws AssertionError") {
                expect {
                    expect(text).toContainAtMostIgnoringCaseFun(18, "[a-z]")
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject("\"$text\"")
                        toContainDescr(TO_CONTAIN.IGNORING_CASE, "")
                        //TODO 1.4.0 would be nice if we don't show the quotes here
                        toContainDescr(STRING_MATCHING_REGEX, "\"[a-z]\"")
                        toContainNumberOfMatches(19)
                        notToContain(AT_LEAST.string)
                        toContainDescr(AT_MOST, 18)
                    }
                }
            }
        }
    }
})
