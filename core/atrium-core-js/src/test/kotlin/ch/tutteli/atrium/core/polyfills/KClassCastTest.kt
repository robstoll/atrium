package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass
import kotlin.test.Test

class KClassCastTest {

    @Test
    fun primitives() {
        expect("dummy subject, see sub assertions") {
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

        expect("dummy subject, see sub assertions") {
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


    @Suppress(/* TODO remove with 1.0.0 and import EmptyNameAndRepresentationAssertionGroup  */ "DEPRECATION")
    @Test
    fun classAndObject() {
        expect("dummy subject, see sub assertions") {
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
        expect("dummy subject, see sub assertions") {
            listOf(
                f0 to f0::class,
                f1 to f1::class,
                f2 to f2::class,
                { 1 } to type<() -> Int>(),
                { x: Int -> x } to type<(Int) -> Int>()
            ).forEach(castAndStaysSame())
        }
    }

    @Suppress(/* TODO remove with 1.0.0 and import EmptyNameAndRepresentationAssertionGroup  */ "DEPRECATION")
    @Test
    fun illegalCasts_privateAndClass_throwsClassCastException() {
        expect {
            Int::class.cast(null)
        }.toThrow<ClassCastException> { this messageContains values("null", "Int") }
        expect {
            Int::class.cast(1L)
        }.toThrow<ClassCastException> { this messageContains values("Int", "Long") }
        expect {
            Translatable::class.cast(objInterface)
        }.toThrow<ClassCastException> {
            this messageContains values(
                "`object: ${Assertion::class.fullName}` (js: objInterface",
                Translatable::class.fullName
            )
        }
        expect {
            Translatable::class.cast(objClass)
        }.toThrow<ClassCastException> {
            this messageContains values(
                "`object: ${ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup::class.fullName}` (js: objClass",
                Translatable::class.fullName
            )
        }
    }

    @Test
    fun illegalCasts_functionTypes__throwsClassCastException() {
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
        }.toThrow<ClassCastException> { this messageContains values(" Function0", " Function1") }

        expect {
            type<(Int) -> Int>().cast({ "a" })
        }.toThrow<ClassCastException> { this messageContains values(" Function0", " Function1") }
    }

    private fun Expect<String>.castAndStaysSame(): (Pair<Any, KClass<*>>) -> Unit {
        return { (value, kClass) ->
            it feature { f("value", kClass.cast(value)) } isSameAs value
        }
    }
}
