@file:Suppress("DEPRECATION" /*TODO remove with 1.0.0 */)

package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.reflect.KClass
import kotlin.test.Test

class KClassFullNameTest {

    private inline fun <reified T : Any> type() = T::class

    @Test
    fun property_primitive() {
        assert("dummy subject, see sub assertions") {
            listOf(
                'a'::class to "BoxedChar",
                true::class to "Boolean",
                1.toByte()::class to "Byte",
                1.toShort()::class to "Short",
                1::class to "Int",
                1L::class to "Long",
                1.0f::class to "Float",
                1.0::class to "Double",
                Char::class to "BoxedChar",
                Boolean::class to "Boolean",
                Byte::class to "Byte",
                Short::class to "Short",
                Int::class to "Int",
                Long::class to "Long",
                Float::class to "Float",
                Double::class to "Double",
                type<Char>() to "BoxedChar",
                type<Boolean>() to "Boolean",
                //TODO should be different, fix if https://youtrack.jetbrains.com/issue/KT-27828 is fixed
                type<Byte>() to "<unknown> (js: Number)", // "Byte",
                type<Short>() to "<unknown> (js: Number)", // "Short",
                type<Int>() to "<unknown> (js: Number)", // "Int",
                type<Long>() to "Long",
                type<Float>() to "<unknown> (js: Number)", // "Float",
                type<Double>() to "<unknown> (js: Number)" // "Double"
            ).forEach(fullNameIsExpected())
        }
    }

    @Test
    fun property_classObjectInterface() {
        assert("dummy subject, see sub assertions") {
            listOf(
                "string"::class to "String",
                RootAssertionGroupType::class to "${RootAssertionGroupType::class.simpleName}",
                objInterface::class to "<unknown> (js: objInterface\$ObjectLiteral)",
                objClass::class to "<unknown> (js: objClass\$ObjectLiteral)",
                listOf<Int>()::class to "EmptyList",
                listOf(1, 2)::class to "ArrayList",
                Collection::class to "Collection",
                Assertion::class to "Assertion"
            ).forEach(fullNameIsExpected())
        }
    }

    @Test
    fun fun_classObject() {
        assert("dummy subject, see sub assertions") {
            listOf(
                "string" to "String",
                RootAssertionGroupType to "${RootAssertionGroupType::class.simpleName}",
                objInterface to "`object: ${Assertion::class.simpleName}` (js: objInterface\$ObjectLiteral)",
                objClass to "`object: ${EmptyNameAndRepresentationAssertionGroup::class.simpleName}` (js: objClass\$ObjectLiteral)",
                listOf<Int>() to "EmptyList",
                listOf(1, 2) to "ArrayList"
            ).forEach { (value, expected) ->
                AssertImpl.feature.returnValueOf0(this, { value::class.fullName(value) }, "fullName") toBe expected
            }
        }
    }


    @Test
    fun property_functionTypes() {
        val f0: () -> Int = { 1 }
        val f1: (Int) -> Int = { 1 }
        val f2: (Int, String) -> Int = { _, _ -> 1 }
        assert("dummy subject, see sub assertions") {
            listOf(
                type<() -> Unit>() to "<unknown> (js: Function)",
                type<(Int) -> Unit>() to "<unknown> (js: Function)",
                type<(Int, String) -> Unit>() to "<unknown> (js: Function)",
                f0::class to "Function0",
                f1::class to "Function1",
                f2::class to "Function2"
            ).forEach(fullNameIsExpected())
        }
    }

    private fun Assert<String>.fullNameIsExpected(): (Pair<KClass<*>, String>) -> Unit {
        return { (kClass, expected) ->
            AssertImpl.feature.property(this, { kClass.fullName }, Untranslatable("fullName")) toBe expected
        }
    }
}

