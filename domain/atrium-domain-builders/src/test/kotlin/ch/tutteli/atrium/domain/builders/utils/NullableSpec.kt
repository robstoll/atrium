//package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.domain.builders.utils.nullable
import ch.tutteli.atrium.domain.builders.utils.nullableContainer
import ch.tutteli.atrium.domain.builders.utils.nullableValueMap
import ch.tutteli.atrium.domani.builders.utils.Test
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.verbs.internal.assert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KFunction1

object NullableSpec : Spek({
    //TODO use function reference directly once https://youtrack.jetbrains.com/issue/KT-24606 is fixed
    val t: (Int) -> Int? = ::nullable
    val nullableFun: KFunction1<Int, Int?> = t as KFunction1<Int, Int?>
    val t2: (Iterable<Int>) -> Iterable<Int?> = ::nullableContainer
    val nullableContainerFun: KFunction1<Iterable<Int>, Iterable<Int?>> = t2 as KFunction1<Iterable<Int>, Iterable<Int?>>
    val t3: (Map<Int, String>) -> Map<Int, String?> = ::nullableValueMap
    val nullableValueMapFun: KFunction1<Map<Int, String>, Map<Int, String?>> = t3 as KFunction1<Map<Int, String>, Map<Int, String?>>

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) = describeFun("", funName, body = body)


    val testee = Test()

    describeFun(nullableFun.name) {
        it("can be applied to String!") {
            assert(nullable(testee.string)).toBe(null)
        }

        context("can be usd in feature assertions") {
            test("for a method with 0 arguments") {
                assert(testee) {
                    returnValueOf(nullable(testee::arg0)).notToBeNullBut(0)
                }
            }
            test("for a method with 1 arguments") {
                assert(testee) {
                    returnValueOf(nullable(testee::arg1), 1).notToBeNullBut(1)
                }
            }
            test("for a method with 2 arguments") {
                assert(testee) {
                    returnValueOf(nullable(testee::arg2), 1, 2).notToBeNullBut(2)
                }
            }
            test("for a method with 3 arguments") {
                assert(testee) {
                    returnValueOf(nullable(testee::arg3), 1, 2, 3).notToBeNullBut(3)
                }
            }
            test("for a method with 4 arguments") {
                assert(testee) {
                    returnValueOf(nullable(testee::arg4), 1, 2, 3, 4).notToBeNullBut(4)
                }
            }
            test("for a method with 5 arguments") {
                assert(testee) {
                    returnValueOf(nullable(testee::arg5), 1, 2, 3, 4, 5).notToBeNullBut(5)
                }
            }
        }
    }

    describeFun(nullableContainerFun.name) {
        context("(Mutable)List<String!>!") {
            it("can be applied to it") {
                assert(nullableContainer(testee.strings)).containsNullableValue(null)
            }
            it("can be combined with ${nullableFun.name}") {
                assert(nullable(nullableContainer(testee.strings))).notToBeNull {
                    containsStrictlyNullableValues(null, "hello")
                }
            }
        }

        context("Array<out String!>!") {
            it("can be applied to it") {
                //TODO simplify once Atrium adds helper for Array
                assert(nullableContainer(testee.stringArray).asIterable()).containsNullableValue(null)
            }
            it("can be combined with ${nullableFun.name}") {
                assert(nullable(nullableContainer(testee.stringArray))).notToBeNull {
                    //TODO simplify once Atrium adds helper for Array
                    returnValueOf(subject::asIterable).containsStrictlyNullableValues(null, "hello")
                }
            }
        }
    }

    describeFun(nullableValueMapFun.name) {
        it("can be applied to a (Mutable)Map<Int!, String!>!") {
            assert(nullableValueMap(testee.numbersWithString)[1]).toBe(null)
        }
        it("can be combined with ${nullableFun.name}") {
            assert(nullable(nullableValueMap(testee.numbersWithString))).notToBeNull {
                returnValueOf(subject::get, 0).toBe(null)
            }
        }
    }
})
