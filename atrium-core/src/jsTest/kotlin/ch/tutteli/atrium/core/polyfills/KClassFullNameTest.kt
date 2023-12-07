package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectGrouped
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.creating.ExpectGrouping
import kotlin.reflect.KClass
import kotlin.test.Test

class KClassFullNameTest {

    private inline fun <reified T : Any> type() = T::class

    @Test
    fun property_primitive() {
        expectGrouped {
            group("values") {
                listOf(
                    'a' to "Char",
                    true to "Boolean",
                    1.toByte() to "Int", // should be "Byte" but https://youtrack.jetbrains.com/issue/KT-32484
                    1.toShort() to "Int", // should be "Short" but https://youtrack.jetbrains.com/issue/KT-32484
                    1 to "Int",
                    1L to "Long",
                    1.0f to "Int", // should be "Float" but https://youtrack.jetbrains.com/issue/KT-32484
                    1.0 to "Int",  // should be "Double" but https://youtrack.jetbrains.com/issue/KT-32484
                ).forEach { (subject, expected) ->
                    expect(subject) feature { f("kclass.fullName", it::class.fullName) } toEqual expected
                }

                // this works as expected because the type is always known (no intermediate step where Any is expected)
                // see https://youtrack.jetbrains.com/issue/KT-32484
                expect(1.toByte()) feature { f("kclass.fullName", it::class.fullName) } toEqual "Byte"
                expect(1.0f) feature { f("kclass.fullName", it::class.fullName) } toEqual "Float"
                expect(1.0) feature { f("kclass.fullName", it::class.fullName) } toEqual "Double"
            }

            group("types") {
                listOf(
                    Char::class to "Char",
                    Boolean::class to "Boolean",
                    Byte::class to "Byte",
                    Short::class to "Short",
                    Int::class to "Int",
                    Long::class to "Long",
                    Float::class to "Float",
                    Double::class to "Double",
                ).forEach(fullNameIsExpected())
            }

            group("type inferred") {
                listOf(
                    type<Char>() to "Char",
                    type<Boolean>() to "Boolean",
                    type<Byte>() to "Byte",
                    type<Short>() to "Short",
                    type<Int>() to "Int",
                    type<Long>() to "Long",
                    type<Float>() to "Float",
                    type<Double>() to "Double"
                ).forEach(fullNameIsExpected())
            }
        }
    }

    @Test
    fun property_classObjectInterface() {
        expectGrouped {
            listOf(
                "string"::class to "String",
                RootAssertionGroupType::class to "${RootAssertionGroupType::class.simpleName}",
                objInterface::class to "<unknown> (js: objInterface\$1)",
                objClass::class to "<unknown> (js: objClass\$1)",
                listOf<Int>()::class to "EmptyList",
                listOf(1, 2)::class to "ArrayList",
                Collection::class to "Collection",
                Assertion::class to "Assertion"
            ).forEach(fullNameIsExpected())
        }
    }

    @Test
    fun test(){

        println(object: T(1){})
            println(objInterface.toString())
            println(objClass.toString())

        println(objInterface2.toString())
        println(objClass2.toString())

    }

    @Test
    fun fun_classObject() {
        expectGrouped {
            println(objClass.toString())
            expect(objClass as Any) feature { f(it::toString) } toEqual "[object Object]"
            listOf(
                "string" to "String",
                RootAssertionGroupType to "${RootAssertionGroupType::class.simpleName}",
                // looks like the IR backend no longer carries the interface names in proto.constructor.$metadata$
                // hence we cannot figure out the class/interface the object implemented
//                objInterface to "`object: ${Assertion::class.simpleName}` (js: objInterface\$1)",
//                objClass to "`object: ${ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup::class.simpleName}` (js: objClass\$1)",
                objInterface to "`object: <unknown2>` (js: objInterface\$1)",
                objClass to "`object: <unknown2>` (js: objClass\$1)",
                listOf<Int>() to "EmptyList",
                listOf(1, 2) to "ArrayList"
            ).forEach(fullNameFunIsExpected())
        }
    }

    @Test
    fun property_functionTypes() {
        val f0: () -> Int = { 1 }
        val f1: (Int) -> Int = { 1 }
        val f2: (Int, String) -> Int = { _, _ -> 1 }
        expectGrouped {
            listOf(
                type<() -> Unit>() to "Function0",
                type<(Int) -> Unit>() to "Function1",
                type<(Int, String) -> Unit>() to "Function2",
                f0::class to "Function0",
                f1::class to "Function1",
                f2::class to "Function2"
            ).forEach(fullNameIsExpected())
        }
    }

    @Test
    fun fun_functionTypes() {
        val f0: () -> Int = { 1 }
        val f1: (Int) -> Int = { 1 }
        val f2: (Int, String) -> Int = { _, _ -> 1 }
        expectGrouped {
            listOf(
                f0 to "Function0 (js: KClassFullNameTest\$fun_functionTypes\$lambda)",
                f1 to "Function1 (js: KClassFullNameTest\$fun_functionTypes\$lambda_0)",
                f2 to "Function2 (js: KClassFullNameTest\$fun_functionTypes\$lambda_1)"
            ).forEach(fullNameFunIsExpected())
        }
    }

    private fun ExpectGrouping.fullNameFunIsExpected(): (Pair<Any, String>) -> Unit = { (value, expected) ->
        expect(value) feature { f("kClass.fullName", it::class.fullName(it)) } toEqual expected
    }

    private fun ExpectGrouping.fullNameIsExpected(): (Pair<KClass<*>, String>) -> Unit = { (kClass, expected) ->
        expect(kClass.fullName) toEqual expected
    }
}

interface I { fun h(): Boolean }

interface IG: I{
    override fun h() = true
}
interface B
object Impl: B

open class C(val b: B, l: List<Int>): IG

fun foo(a: Any) = a.toString()

val objInterface2 = object:I{ override fun h() = true }
val objClass2 = object:C(Impl, emptyList()){}

interface A { val i: Int }
open class T(override val i: Int): A
