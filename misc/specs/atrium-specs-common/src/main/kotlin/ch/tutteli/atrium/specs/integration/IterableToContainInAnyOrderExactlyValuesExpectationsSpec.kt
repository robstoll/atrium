package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.EXACTLY
import org.spekframework.spek2.style.specification.Suite

abstract class IterableToContainInAnyOrderExactlyValuesExpectationsSpec(
    toContainExactlyPair: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    val toContainExactly = toContainExactlyPair.second

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainExactly.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<Iterable<Double>>.toContainExactlyFun(atLeast: Int, a: Double, vararg aX: Double) =
        toContainExactly.invoke(this, atLeast, a, aX.toTypedArray())

    val (notToContain, errorMsgContainsNot) = notToContainPair

    val toBeExactlyDescr = EXACTLY.getDefault()

    describeFun(toContainExactly) {
        context("throws an $illegalArgumentException") {
            it("for exactly -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).toContainExactlyFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for exactly 0 -- points to $notToContain") {
                expect {
                    expect(oneToSeven()).toContainExactlyFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case with $toContainExactly once") {
                it("${toContainExactlyPair.first("1.0", "once")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(1, 1.0)
                }
                it("${toContainExactlyPair.first("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(1, 1.0, 2.0, 3.0)
                }
                it("${toContainExactlyPair.first("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(1, 3.0, 1.0, 2.0)
                }
            }

            context("failing cases; search string at different positions with $toContainExactly once") {
                it("${toContainExactlyPair.first("4.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 4.0)
                    }.toThrow<AssertionError> { messageToContain("$toBeExactlyDescr: 1", "$anElementWhichEquals: 4.0") }
                }

                it("${toContainExactlyPair.first("1.0, 2.3", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 1.0, 2.3)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$toBeExactlyDescr: 1", "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.0")
                        }
                    }
                }

                it("${toContainExactlyPair.first("2.3, 1.0", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 2.3, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$toBeExactlyDescr: 1", "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.0")
                        }
                    }
                }

                it("${toContainExactlyPair.first("1.0 and 2.3 and 3.1", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 1.0, 2.3, 3.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(toBeExactlyDescr, 2.3, 3.1)
                            toContain.exactly(2).values(
                                "$numberOfSuchElements: 0",
                                "$toBeExactlyDescr: 1"
                            )
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 2.3",
                                "$anElementWhichEquals: 3.1"
                            )
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it("${toContainExactlyPair.first("5.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 5.0)
                    }.toThrow<AssertionError> { messageToContain(toBeExactlyDescr) }
                }
                it("${toContainExactlyPair.first("5.0", "twice")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(2, 5.0)
                }

                it(
                    "${toContainExactlyPair.first(
                        "5.0",
                        "3 times"
                    )} throws AssertionError and message toContain both, how many times we expected (3) and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(3, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.0",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$toBeExactlyDescr: 3")
                        }
                    }
                }

                it("${toContainExactlyPair.first("5.0 and 4.0", "twice")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 4.0",
                                "$numberOfSuchElements: 3$separator"
                            )
                            toEndWith("$toBeExactlyDescr: 2")
                            notToContain("$anElementWhichEquals: 5.0")
                        }
                    }
                }
                it("${toContainExactlyPair.first("4.0", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(3, 4.0)
                }
                it(
                    "${toContainExactlyPair.first(
                        "5.0 and 4.0",
                        "3 times"
                    )} throws AssertionError and message toContain both, how many times we expected (3) and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(3, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.0",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$toBeExactlyDescr: 3")
                            notToContain("$anElementWhichEquals: 4.0")
                        }
                    }
                }
            }
        }
    }
})
