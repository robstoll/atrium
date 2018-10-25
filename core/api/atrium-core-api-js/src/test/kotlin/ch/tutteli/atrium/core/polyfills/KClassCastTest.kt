@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.Values
import ch.tutteli.atrium.api.cc.infix.en_GB.isSameAs
import ch.tutteli.atrium.api.cc.infix.en_GB.messageContains
import ch.tutteli.atrium.api.cc.infix.en_GB.toThrow
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import kotlin.reflect.KClass
import kotlin.test.Test

class KClassCastTest {

    private val objInterface = object : Assertion {
        override fun holds() = true
    }
    private val objClass = object : EmptyNameAndRepresentationAssertionGroup(RootAssertionGroupType, listOf()) {}

    private inline fun <reified T: Any> type() = T::class

    @Test
    fun primitives() {
        assert("dummy subject, see sub assertions") {
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

        assert("dummy subject, see sub assertions") {
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
        assert("dummy subject, see sub assertions") {
            listOf(
                "string" to String::class,
                objInterface to Assertion::class,
                objClass to EmptyNameAndRepresentationAssertionGroup::class,
                objClass to AssertionGroup::class,
                objClass to Assertion::class,
                listOf<Int>() to List::class,
                listOf<Int>() to Collection::class
            ).forEach(castAndStaysSame())
        }
    }

    @Test
    fun illegalCasts_throwsClassCastException() {
        expect {
            Int::class.cast(null)
        }.toThrow<ClassCastException> { this messageContains Values("null", "Int") }
        expect {
            Int::class.cast(1L)
        }.toThrow<ClassCastException> { this messageContains Values("Int", "Long") }
        expect {
            Translatable::class.cast(objInterface)
        }.toThrow<ClassCastException> {
            this messageContains Values(Assertion::class.fullName, Translatable::class.fullName)
        }
        expect {
            Translatable::class.cast(objClass)
        }.toThrow<ClassCastException> {
            this messageContains Values(
                EmptyNameAndRepresentationAssertionGroup::class.fullName,
                Translatable::class.fullName
            )
        }
    }

    private fun Assert<String>.castAndStaysSame(): (Pair<Any, KClass<*>>) -> Unit {
        return { (value, klass) ->
            AssertImpl.feature.property(this, { klass.cast(value) }, Untranslatable("value")) isSameAs value
        }
    }
}
