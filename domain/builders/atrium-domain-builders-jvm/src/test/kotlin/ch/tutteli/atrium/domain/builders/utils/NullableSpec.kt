package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.toBeDescr
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KFunction1

object NullableSpec : Spek({
    //TODO use function reference directly once https://youtrack.jetbrains.com/issue/KT-24606 is fixed
    val t: (Int) -> Int? = ::nullable
    val nullableFun: KFunction1<Int, Int?> = t as KFunction1<Int, Int?>
    val t2: (Iterable<Int>) -> Iterable<Int?> = ::nullableContainer
    val nullableContainerFun: KFunction1<Iterable<Int>, Iterable<Int?>> =
        t2 as KFunction1<Iterable<Int>, Iterable<Int?>>
    val t3: (Map<Int, String>) -> Map<Int, String?> = ::nullableValueMap
    val nullableValueMapFun: KFunction1<Map<Int, String>, Map<Int, String?>> =
        t3 as KFunction1<Map<Int, String>, Map<Int, String?>>
    val t4: (Map<out Int, String>) -> Map<out Int?, String> = ::nullableKeyMap
    val nullableKeyMapFun: KFunction1<Map<out Int, String>, Map<out Int?, String>> =
        t4 as KFunction1<Map<out Int, String>, Map<out Int?, String>>
    val t5: (Map<out Int, String>) -> Map<out Int?, String?> = ::nullableKeyValueMap
    val nullableKeyValueMapFun: KFunction1<Map<out Int, String>, Map<out Int?, String?>> =
        t5 as KFunction1<Map<out Int, String>, Map<out Int?, String?>>

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate("", funName, body = body)

    val testee = Test()

    describeFun(nullableFun.name) {
        it("can be applied to String!") {
            expect(nullable(testee.string)).toBe(null)
        }

        context("can be usd in feature assertions") {
            it("for a method with 0 arguments") {
                expect(testee) {
                    feature { f(nullable(it::arg0)) }.toBe(0)
                }
            }
            it("for a method with 1 arguments") {
                expect(testee) {
                    feature { f(nullable(testee::arg1), 1) }.toBe(1)
                }
            }
            it("for a method with 2 arguments") {
                expect(testee) {
                    feature { f(nullable(testee::arg2), 1, 2) }.toBe(2)
                }
            }
            it("for a method with 3 arguments") {
                expect(testee) {
                    feature { f(nullable(testee::arg3), 1, 2, 3) }.toBe(3)
                }
            }
            it("for a method with 4 arguments") {
                expect(testee) {
                    feature { f(nullable(testee::arg4), 1, 2, 3, 4) }.toBe(4)
                }
            }
            it("for a method with 5 arguments") {
                expect(testee) {
                    feature { f(nullable(testee::arg5), 1, 2, 3, 4, 5) }.toBe(5)
                }
            }
        }
    }

    describeFun(nullableContainerFun.name) {
        context("(Mutable)List<String!>!") {
            it("can be applied to it") {
                expect(nullableContainer(testee.strings)).contains(null)
            }
            it("can be combined with ${nullableFun.name}") {
                expect(nullable(nullableContainer(testee.strings))).notToBeNull {
                    contains(null, "hello")
                }
            }
        }

        context("Array<out String!>!") {
            it("can be applied to it") {
                expect(nullableContainer(testee.stringArray)).asList().contains(null)
            }
            it("can be combined with ${nullableFun.name}") {
                expect(nullable(nullableContainer(testee.stringArray))).notToBeNull {
                    asList().contains(null, "hello")
                }
            }
        }
    }

    describeFun(nullableKeyMapFun.name) {
        it("can be applied to a (Mutable)Map<Int!, String!>!") {
            expect(nullableKeyMap(testee.numbersWithString)).getExisting(2) {
                toBe("hello")
            }
        }
        it("throws if the value was actually null") {
            expect {
                expect(nullableKeyMap(testee.numbersWithString)).getExisting(1).toBe("a")
            }.toThrow<AssertionError> { messageContains("get(1): null", "to be: \"a\"") }
        }
        it("can pass `null` as key") {
            expect(nullableKeyMap(testee.numbersWithString)[null]).toBe("tada")
        }

        it("can be combined with ${nullableFun.name}") {
            expect(nullable(nullableKeyMap(testee.numbersWithString))).notToBeNull {
                feature { f<Int, String?>(it::get, 0) }.toBe(null)
            }
        }
    }

    describeFun(nullableValueMapFun.name) {
        it("can be applied to a (Mutable)Map<Int!, String!>!") {
            expect(nullableValueMap(testee.numbersWithString)).getExisting(1).toBe(null)
            expect {
                expect(nullableValueMap(testee.numbersWithString)).getExisting(1).toBe("test")
            }.toThrow<AssertionError> {
                messageContains(
                    "get(1): null",
                    "$toBeDescr: \"test\""
                )
            }

        }
        it("can be combined with ${nullableFun.name}") {
            expect(nullable(nullableValueMap(testee.numbersWithString))).notToBeNull {
                feature { f(it::get, 0) }.toBe(null)
            }
        }
    }

    describeFun(nullableKeyValueMapFun.name) {
        it("can be applied to a (Mutable)Map<Int!, String!>!") {
            expect(nullableKeyValueMap(testee.numbersWithString)).getExisting(1).toBe(null)
            expect {
                expect(nullableKeyValueMap(testee.numbersWithString)).getExisting(1).toBe("hello")
            }.toThrow<AssertionError> {
                messageContains(
                    "get(1): null",
                    "$toBeDescr: \"hello\""
                )
            }
        }

        it("can pass `null` as key") {
            expect(nullableKeyMap(testee.numbersWithString)).getExisting(null).toBe("tada")
        }

        it("can be combined with ${nullableFun.name}") {
            expect(nullable(nullableKeyValueMap(testee.numbersWithString))).notToBeNull {
                feature { f<Int?, String?>(it::get, 0) }.toBe(null)
            }
        }
    }
})
