package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.withFeatureSuffix
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

class AnyExpectationsSpec : ch.tutteli.atrium.specs.integration.AnyExpectationsSpec(
    fun1<Int, Int>(Expect<Int>::toEqual),
    fun1<DataClass, DataClass>(Expect<DataClass>::toEqual),
    fun1<Int?, Int?>(Expect<Int?>::toEqual).withNullableSuffix(),
    fun1<DataClass?, DataClass?>(Expect<DataClass?>::toEqual).withNullableSuffix(),
    fun1(Expect<Int>::notToEqual),
    fun1(Expect<DataClass>::notToEqual),
    fun1(Expect<Int?>::notToEqual).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToEqual).withNullableSuffix(),
    fun1(Expect<Int>::toBeTheInstance),
    fun1(Expect<DataClass>::toBeTheInstance),
    fun1(Expect<Int?>::toBeTheInstance).withNullableSuffix(),
    fun1(Expect<DataClass?>::toBeTheInstance).withNullableSuffix(),
    fun1(Expect<Int>::notToBeTheInstance),
    fun1(Expect<DataClass>::notToBeTheInstance),
    fun1(Expect<Int?>::notToBeTheInstance).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToBeTheInstance).withNullableSuffix(),
    fun2(Expect<Int>::notToEqualOneOf),
    fun2(Expect<DataClass>::notToEqualOneOf),
    fun2(Expect<Int?>::notToEqualOneOf).withNullableSuffix(),
    fun2(Expect<DataClass?>::notToEqualOneOf).withNullableSuffix(),
    fun1(Expect<Int>::notToEqualOneIn),
    fun1(Expect<DataClass>::notToEqualOneIn),
    fun1(Expect<Int?>::notToEqualOneIn).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToEqualOneIn).withNullableSuffix(),
    fun2(Expect<String>::because),
    fun2(Expect<Int>::because),

    "${Expect<Int?>::toEqual.name}(null)" to Companion::toEqualNull,
    fun1(Expect<Int?>::toEqualNullIfNullGivenElse),
    ("toBeAnInstanceOf" to Companion::toBeAnInstanceOfIntFeature).withFeatureSuffix(),
    "toBeAnInstanceOf" to Companion::toBeAnInstanceOfInt,
    ("toBeAnInstanceOf" to Companion::toBeAnInstanceOfSuperTypeFeature).withFeatureSuffix(),
    "toBeAnInstanceOf" to Companion::toBeAnInstanceOfSuperType,
    ("toBeAnInstanceOf" to Companion::toBeAnInstanceOfSubTypeFeature).withFeatureSuffix(),
    "toBeAnInstanceOf" to Companion::toBeAnInstanceOfSubType,

    feature0<Int?, Int>(Expect<Int?>::notToEqualNull),
    "notToEqualNull" to Companion::notToEqualNull,

    getAndImmediatePair(),
    getAndLazyPair()
) {

    companion object {
        private fun toEqualNull(expect: Expect<Int?>) = expect.toEqual(null)

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfIntFeature(expect: Expect<out Any?>): Expect<Int> =
            expect.toBeAnInstanceOf<Int>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfInt(expect: Expect<out Any?>, assertionCreator: Expect<Int>.() -> Unit): Expect<Int> =
            expect.toBeAnInstanceOf<Int> { assertionCreator() }

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfSuperTypeFeature(expect: Expect<out Any?>): Expect<SuperType> =
            expect.toBeAnInstanceOf<SuperType>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfSuperType(
            expect: Expect<out Any?>,
            assertionCreator: Expect<SuperType>.() -> Unit
        ): Expect<SuperType> =
            expect.toBeAnInstanceOf<SuperType> { assertionCreator() }

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfSubTypeFeature(expect: Expect<out Any?>): Expect<SubType> =
            expect.toBeAnInstanceOf<SubType>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfSubType(
            expect: Expect<out Any?>,
            assertionCreator: Expect<SubType>.() -> Unit
        ): Expect<SubType> =
            expect.toBeAnInstanceOf<SubType> { assertionCreator() }

        private val andImmediate: KProperty1<Expect<Int>, Expect<Int>> = Expect<Int>::and
        fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> = andImmediate.name to Expect<Int>::and

        private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
        fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
            andLazyName.name to Expect<Int>::and

        private fun notToEqualNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
            expect.notToEqualNull(assertionCreator)
    }

    //regression for #298, should compile without the need for E : Any or List<E?>
    @Suppress("unused")
    fun <E> Expect<List<E>>.firstIs(value: E) = get(0) { toEqual(value) }
}
