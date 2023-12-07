package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectGrouped
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass
import kotlin.test.Test

class KClassCastTest {

    @Test
    fun primitives() {
        expectGrouped {
            listOf(
                'a' to Char::class,
                true to Boolean::class,
                false to Boolean::class,
                1.toByte() to Byte::class,
                1.toShort() to Short::class,
                1 to Int::class,
                1L to Long::class,
                1.0f to Float::class,
                1.0 to Double::class
            ).forEach(castAndStaysSame())
        }
    }

    @Test
    fun reifiedTypes() {
        expectGrouped {
            listOf(
                'a' to type<Char>(),
                true to type<Boolean>(),
                false to type<Boolean>(),
                1.toByte() to type<Byte>(),
                1.toShort() to type<Short>(),
                1 to type<Int>(),
                1L to type<Long>(),
                1.0f to type<Float>(),
                1.0 to type<Double>(),
                listOf<Int>() to type<List<Int>>()
            ).forEach(castAndStaysSame())
        }
    }


    @Test
    fun classAndObject() {
        expectGrouped {
            listOf(
                "string" to String::class,
                RootAssertionGroupType to AssertionGroupType::class,
                objInterface to Assertion::class,
                objClass to ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup::class,
                objClass to AssertionGroup::class,
                objClass to Assertion::class,
                listOf<Int>() to List::class,
                listOf<Int>() to Collection::class
            ).forEach(castAndStaysSame())
        }
    }

    @Test
    fun functionTypes() {
        val f0: () -> Int = { 1 }
        val f1: (Int) -> Int = { 1 }
        val f2: (Int, String) -> Int = { _, _ -> 1 }
        expectGrouped {
            listOf(
                f0 to f0::class,
                f1 to f1::class,
                f2 to f2::class,
                { 1 } to type<() -> Int>(),
                { x: Int -> x } to type<(Int) -> Int>()
            ).forEach(castAndStaysSame())
        }
    }

    @Test
    fun illegalCasts_privateAndClass_throwsClassCastException() {
        expectGrouped {
            group("cast to primitive") {
                expect {
                    Int::class.cast(null)
                }.toThrow<ClassCastException> { message toEqual "Cannot cast null to Int." }

                expect {
                    Int::class.cast(1L)
                }.toThrow<ClassCastException> { message toEqual "Cannot cast Long to Int." }
            }

            group("cast to class") {
                expect {
                    Translatable::class.cast(objInterface)
                }.toThrow<ClassCastException> {
                    its messageToContain values(
                        // looks like the IR backend no longer carries the interface names in proto.constructor.$metadata$
                        // hence we cannot figure out the class/interface the object implemented
//                        "`object: ${Assertion::class.fullName}` (js: objInterface",
                        "`object: <unknown>` (js: objInterface",
                        Translatable::class.fullName
                    )
                }
            }
        }
    }

    @Test
    fun illegalCasts_functionTypes__throwsClassCastException() {
        expectGrouped {
            //TODO should throw but does not, see https://youtrack.jetbrains.com/issue/KT-27846
//        expect {
            val f: () -> Int = { 1 }
            f::class.cast({ "a" })
//        }.toThrow<ClassCastException> { messageContains("??? 1")  }

            //TODO should throw but does not, see https://youtrack.jetbrains.com/issue/KT-27846
//        expect{
            type<() -> Int>().cast({ "a " })
//        }.toThrow<ClassCastException> { messageContains("??? 2")  }

            expect {
                val f1: (Int) -> Int = { it }
                f1::class.cast({ "a" })
            }.toThrow<ClassCastException> { this messageToContain values(" Function0", " Function1") }

            expect {
                type<(Int) -> Int>().cast({ "a" })
            }.toThrow<ClassCastException> { this messageToContain values(" Function0", " Function1") }
        }
    }

    private fun ExpectGrouping.castAndStaysSame(): (Pair<Any, KClass<*>>) -> Unit = { (value, kClass) ->
        group("$value cast to ${kClass.simpleName} remains the same instance") {
            expect(kClass.cast(value)) toBeTheInstance value
        }
    }
}
