package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withFeatureSuffix
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction2

class AnyExpectationsSpec : ch.tutteli.atrium.specs.integration.AnyExpectationsSpec(
    fun1<Int, Int>(Expect<Int>::toEqual),
    fun1<DataClass, DataClass>(Expect<DataClass>::toEqual),
    fun1<Int?, Int?>(Expect<Int?>::toEqual).withNullableSuffix(),
    fun1<DataClass?, DataClass?>(Expect<DataClass?>::toEqual).withNullableSuffix(),
    fun1(Expect<Int>::notToEqual),
    fun1(Expect<DataClass>::notToEqual),
    fun1(Expect<Int?>::notToEqual).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToEqual).withNullableSuffix(),
    fun1(Expect<Int>::isSameAs),
    fun1(Expect<DataClass>::isSameAs),
    fun1(Expect<Int?>::isSameAs).withNullableSuffix(),
    fun1(Expect<DataClass?>::isSameAs).withNullableSuffix(),
    fun1(Expect<Int>::isNotSameAs),
    fun1(Expect<DataClass>::isNotSameAs),
    fun1(Expect<Int?>::isNotSameAs).withNullableSuffix(),
    fun1(Expect<DataClass?>::isNotSameAs).withNullableSuffix(),
    fun2(Companion::isNoneOfInt),
    fun2(Companion::isNoneOfDataClass),
    fun2(Companion::isNoneOfIntNullable).withNullableSuffix(),
    fun2(Companion::isNoneOfDataClassNullable).withNullableSuffix(),
    fun1(Expect<Int>::isNotIn),
    fun1(Expect<DataClass>::isNotIn),
    fun1(Expect<Int?>::isNotIn).withNullableSuffix(),
    fun1(Expect<DataClass?>::isNotIn).withNullableSuffix(),
    fun2<String, String, Expect<String>.() -> Unit>(Companion::because),
    fun2<Int, String, Expect<Int>.() -> Unit>(Companion::becauseOfInt),

    "${Expect<Int?>::toEqual.name}(null)" to Companion::toEqualNull,
    fun1(Expect<Int?>::toBeNullIfNullGivenElse),
    ("isA" to Companion::isAIntFeature).withFeatureSuffix(),
    "isA" to Companion::isAInt,
    ("isA" to Companion::isASuperTypeFeature).withFeatureSuffix(),
    "isA" to Companion::isASuperType,
    ("isA" to Companion::isASubTypeFeature).withFeatureSuffix(),
    "isA" to Companion::isASubType,

    ("notToEqualNull" to Companion::notToEqualNullFeature).withFeatureSuffix(),
    "notToEqualNull" to Companion::notToEqualNull,

    getAndImmediatePair(),
    getAndLazyPair()
) {

    companion object  {
        private fun toEqualNull(expect: Expect<Int?>) = expect toEqual null

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
        private fun isASuperType(
            expect: Expect<out Any?>,
            assertionCreator: Expect<SuperType>.() -> Unit
        ): Expect<SuperType> =
            expect.isA<SuperType> { assertionCreator() }

        @Suppress("RemoveExplicitTypeArguments")
        private fun isASubTypeFeature(expect: Expect<out Any?>): Expect<SubType> =
            expect.isA<SubType>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun isASubType(
            expect: Expect<out Any?>,
            assertionCreator: Expect<SubType>.() -> Unit
        ): Expect<SubType> =
            expect.isA<SubType> { assertionCreator() }

        private fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> =
            "and o" to { e: Expect<Int> ->
                e and o
            }

        private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
        private fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
            andLazyName.name to { e: Expect<Int>, assertionCreator: Expect<Int>.() -> Unit ->
                e and { assertionCreator() }
            }

        private fun notToEqualNullFeature(expect: Expect<Int?>) =
            expect notToEqualNull o

        private fun notToEqualNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
            expect notToEqualNull assertionCreator

        private fun isNoneOfInt(expect: Expect<Int>, expected: Int, otherValues: Array<out Int>): Expect<Int> =
            expect isNoneOf values(expected, *otherValues)

        private fun isNoneOfIntNullable(
            expect: Expect<Int?>,
            expected: Int?,
            otherValues: Array<out Int?>
        ): Expect<Int?> =
            expect isNoneOf values(expected, *otherValues)

        private fun isNoneOfDataClass(
            expect: Expect<DataClass>,
            expected: DataClass,
            otherValues: Array<out DataClass>
        ): Expect<DataClass> =
            expect isNoneOf values(expected, *otherValues)

        private fun isNoneOfDataClassNullable(
            expect: Expect<DataClass?>,
            expected: DataClass?,
            otherValues: Array<out DataClass?>
        ): Expect<DataClass?> =
            expect isNoneOf values(expected, *otherValues)

        private fun because(expect: Expect<String>, reason: String, assertionCreator: Expect<String>.() -> Unit) =
            expect because of(reason) { assertionCreator() }

        private fun becauseOfInt(expect: Expect<Int>, reason: String, assertionCreator: Expect<Int>.() -> Unit) =
            expect because of(reason) { assertionCreator() }
    }

    @Suppress("unused")
    fun ambiguityTest() {
        val a1: Expect<Number> = notImplemented()
        val a1b: Expect<Number?> = notImplemented()

        a1 toEqual 1
        a1 toEqual 1.2
        a1 notToEqual 1
        a1 notToEqual 1.2
        a1 isSameAs 1
        a1 isSameAs 1.2
        a1 isNotSameAs 1
        a1 isNotSameAs 1.2
        a1.isA<Int>()
        a1.isA<Int> {}
        a1 isNoneOf values(1, 2)
        a1 isNotIn listOf(1, 1.2)
        a1 because of("hello") { toEqual(1) }

        a1b toEqual 1
        a1b toEqual 1.2
        a1b notToEqual 1
        a1b notToEqual 1.2
        a1b isSameAs 1
        a1b isSameAs 1.2
        a1b isNotSameAs 1
        a1b isNotSameAs 1.2
        a1b.isA<Int>()
        a1b.isA<Int> {}
        a1b isNoneOf values(1, 2)
        a1b isNotIn listOf(1, 1.2)

        a1b notToBeNull o toEqual 1
        a1b notToBeNull {}

        a1 and o toEqual 1
        a1 and { it toEqual 1 }

    }

    //regression for #298, should compile without the need for E : Any or List<E?>
    @Suppress("unused")
    fun <E> Expect<List<E>>.firstIs(value: E) = it get index(0) { it toEqual value }
}
