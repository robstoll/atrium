package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.withFeatureSuffix
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

class AnyAssertionsSpec : ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
    fun1<Int, Int>(Expect<Int>::toBe),
    fun1<DataClass, DataClass>(Expect<DataClass>::toBe),
    fun1<Int?, Int?>(Expect<Int?>::toBe),
    fun1<DataClass?, DataClass?>(Expect<DataClass?>::toBe),
    fun1(Expect<Int>::notToBe),
    fun1(Expect<DataClass>::notToBe),
    fun1(Expect<Int?>::notToBe).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToBe).withNullableSuffix(),
    fun1(Expect<Int>::isSameAs),
    fun1(Expect<DataClass>::isSameAs),
    fun1(Expect<Int?>::isSameAs).withNullableSuffix(),
    fun1(Expect<DataClass?>::isSameAs).withNullableSuffix(),
    fun1(Expect<Int>::isNotSameAs),
    fun1(Expect<DataClass>::isNotSameAs),
    fun1(Expect<Int?>::isNotSameAs).withNullableSuffix(),
    fun1(Expect<DataClass?>::isNotSameAs).withNullableSuffix(),
    fun2(Expect<Int>::isNoneOf),
    fun2(Expect<DataClass>::isNoneOf),
    fun2(Expect<Int?>::isNoneOf).withNullableSuffix(),
    fun2(Expect<DataClass?>::isNoneOf).withNullableSuffix(),
    fun1(Expect<Int>::isNotIn),
    fun1(Expect<DataClass>::isNotIn),
    fun1(Expect<Int?>::isNotIn).withNullableSuffix(),
    fun1(Expect<DataClass?>::isNotIn).withNullableSuffix(),

    "${Expect<Int?>::toBe.name}(null)" to Companion::toBeNull,
    fun1(Expect<Int?>::toBeNullIfNullGivenElse),
    ("isA" to Companion::isAIntFeature).withFeatureSuffix(),
    "isA" to Companion::isAInt,
    ("isA" to Companion::isASuperTypeFeature).withFeatureSuffix(),
    "isA" to Companion::isASuperType,
    ("isA" to Companion::isASubTypeFeature).withFeatureSuffix(),
    "isA" to Companion::isASubType,

    feature0<Int?, Int>(Expect<Int?>::notToBeNull),
    "notToBeNull" to Companion::notToBeNull,

    getAndImmediatePair(),
    getAndLazyPair()
) {

    companion object {
        private fun toBeNull(expect: Expect<Int?>) = expect.toBe(null)

        @Suppress("RemoveExplicitTypeArguments")
        private fun isAIntFeature(expect: Expect<out Any?>): Expect<Int> =
            expect.isA<Int>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun isAInt(expect: Expect<out Any?>, assertionCreator: Expect<Int>.() -> Unit): Expect<Int> =
            expect.isA<Int> { assertionCreator() }

        @Suppress("RemoveExplicitTypeArguments")
        private fun isASuperTypeFeature(expect: Expect<out Any?>): Expect<SuperType> =
            expect.isA<SuperType>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun isASuperType(expect: Expect<out Any?>, assertionCreator: Expect<SuperType>.() -> Unit): Expect<SuperType> =
            expect.isA<SuperType> { assertionCreator() }

        @Suppress("RemoveExplicitTypeArguments")
        private fun isASubTypeFeature(expect: Expect<out Any?>): Expect<SubType> =
            expect.isA<SubType>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun isASubType(expect: Expect<out Any?>, assertionCreator: Expect<SubType>.() -> Unit): Expect<SubType> =
            expect.isA<SubType> { assertionCreator() }

        private val andImmediate: KProperty1<Expect<Int>, Expect<Int>> = Expect<Int>::and
        fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> = andImmediate.name to Expect<Int>::and

        private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
        fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
            andLazyName.name to Expect<Int>::and

        private fun notToBeNullFeature(expect: Expect<Int?>) =
            expect.notToBeNull()

        private fun notToBeNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
            expect.notToBeNull(assertionCreator)
    }

    //regression for #298, should compile without the need for E : Any or List<E?>
    @Suppress("unused")
    fun <E> Expect<List<E>>.firstIs(value: E) = get(0) { toBe(value) }
}
