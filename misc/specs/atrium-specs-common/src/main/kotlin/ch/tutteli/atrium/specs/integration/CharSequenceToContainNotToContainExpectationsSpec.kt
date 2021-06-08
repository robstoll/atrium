package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS_NOT
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceToContainNotToContainExpectationsSpec(
    toContain: Fun2<CharSequence, Any, Array<out Any>>,
    notToContain: Fun2<CharSequence, Any, Array<out Any>>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        toContain.forSubjectLess("hello", arrayOf()),
        notToContain.forSubjectLess("hello", arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(text as CharSequence)

    fun Expect<CharSequence>.toContainFun(t: String, vararg tX: String) = toContain.invoke(this, t, tX)

    fun Expect<CharSequence>.notToContainFun(t: String, vararg tX: String) = notToContain.invoke(this, t, tX)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"
    val notToContainDescr = CONTAINS_NOT.getDefault()

    describeFun(toContain.name, notToContain.name) {
        context("empty string") {
            val fluentEmptyString = expect("" as CharSequence)
            it("${toContain.name} 'Hello' throws AssertionError") {
                expect {
                    fluentEmptyString.toContainFun("Hello")
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$toContainDescr: $separator" +
                            "$valueWithIndent: \"Hello\"",
                            noSuchItemFoundDescr
                    )
                }
            }
            it("${notToContain.name} 'Hello' does not throw") {
                fluentEmptyString.notToContainFun("Hello")
            }
        }

        context("text '$text'") {

            context("search for 'Hello' and 'Robert'") {
                it("${toContain.name} 'Hello' does not throw") {
                    fluent.toContainFun("Hello")
                }
                it("${notToContain.name} 'Hello' throws AssertionError") {
                    expect {
                        fluent.notToContainFun("Hello")
                    }.toThrow<AssertionError> { messageToContain(notToContainDescr, "$valueWithIndent: \"Hello\"") }
                }

                it("${toContain.name} 'Hello' and 'Robert' does not throw") {
                    fluent.toContainFun("Hello", "Robert")
                }
                it("${notToContain.name} 'Hello' and 'Robert' throws AssertionError") {
                    expect {
                        fluent.notToContainFun("Hello", "Robert")
                    }.toThrow<AssertionError> {
                        messageToContain(
                            notToContainDescr,
                            "$valueWithIndent: \"Hello\"",
                            "$valueWithIndent: \"Robert\""
                        )
                    }
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                it("${toContain.name} 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.toContainFun("notInThere", "neitherInThere")
                    }.toThrow<AssertionError> {
                        messageToContain(
                            toContainDescr,
                            "$valueWithIndent: \"notInThere\"",
                            "$valueWithIndent: \"neitherInThere\""
                        )
                    }
                }
                it("${notToContain.name} 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.notToContainFun("notInThere", "neitherInThere")
                }
            }

            context("search for 'hello' and 'robert'") {
                it("${toContain.name} 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.toContainFun("hello", "robert")
                    }.toThrow<AssertionError> {
                        message {
                            this.toContain.exactly(2).value(
                                noSuchItemFoundDescr
                            )
                            this.toContain.exactly(1).values(
                                "$rootBulletPoint$toContainDescr: $separator",
                                "$valueWithIndent: \"hello\"",
                                "$valueWithIndent: \"robert\""
                            )
                        }
                    }
                }
                it("${notToContain.name} 'hello' and 'robert' does not throw") {
                    fluent.notToContainFun("hello", "robert")
                }
            }

            context("search for 'Hello' and 'notInThere'") {
                it("${toContain.name} 'notInThere' throws AssertionError") {
                    expect {
                        fluent.toContainFun("notInThere")
                    }.toThrow<AssertionError> { messageToContain(toContainDescr, "$valueWithIndent: \"notInThere\"") }
                }
                it("${notToContain.name} 'notInThere' does not throw") {
                    fluent.notToContainFun("notInThere")
                }

                it("${toContain.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'Hello'") {
                    expect {
                        fluent.toContainFun("Hello", "notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            toContain(toContainDescr, "$valueWithIndent: \"notInThere\"")
                            notToContain("$valueWithIndent: \"Hello\"")
                        }
                    }
                }
                it("${notToContain.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'notInThere'") {
                    expect {
                        fluent.notToContainFun("Hello", "notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            toContain(notToContainDescr, "$valueWithIndent: \"Hello\"")
                            notToContain("$valueWithIndent: \"notInThere\"")
                        }
                    }
                }
            }

            it("${toContain.name} 'Hello' and 'Hello' (searching twice in the same assertion) does not throw") {
                fluent.toContainFun("Hello", "Hello")
            }

            it("${notToContain.name} 'notInThere' and 'notInThere' does not throw") {
                fluent.notToContainFun("notInThere", "notInThere")
            }
        }

        context("error message") {
            context("feature assertion about a Person's name 'Robert Stoll'") {
                data class Person(val name: CharSequence)

                val person = Person("Robert Stoll")

                val nameWithArrow = "${featureArrow}name"
                it("${toContain.name} 'treboR' and 'llotS' - error message toContain '$nameWithArrow' exactly once") {
                    expect {
                        expect(person)._logic.appendAsGroup {
                            feature(Person::name).toContainFun("treboR", "llotS")
                        }
                    }.toThrow<AssertionError> {
                        message { this.toContain.exactly(1).value(nameWithArrow) }
                    }
                }
                it("${notToContain.name} 'Robert' and 'Stoll' - error message toContain '$nameWithArrow' exactly once") {
                    expect {
                        expect(person)._logic.appendAsGroup {
                            feature(Person::name).notToContainFun("Robert", "Stoll")
                        }
                    }.toThrow<AssertionError> {
                        message { this.toContain.exactly(1).value(nameWithArrow) }
                    }
                }
            }
        }
    }
})
