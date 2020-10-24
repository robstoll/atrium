package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.specs.withFeatureSuffix
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction2

class AnyAssertionsSpec : ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
    fun1<Int, Int>(Expect<Int>::toBe),
    fun1<DataClass, DataClass>(Expect<DataClass>::toBe),
    fun1<Int?, Int?>(Expect<Int?>::toBe).withNullableSuffix(),
    fun1<DataClass?, DataClass?>(Expect<DataClass?>::toBe).withNullableSuffix(),
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
    fun2(Companion::isNoneOfInt),
    fun2(Companion::isNoneOfDataClass),
    fun2(Companion::isNoneOfIntNullable).withNullableSuffix(),
    fun2(Companion::isNoneOfDataClassNullable).withNullableSuffix(),
    fun1(Expect<Int>::isNotIn),
    fun1(Expect<DataClass>::isNotIn),
    fun1(Expect<Int?>::isNotIn).withNullableSuffix(),
    fun1(Expect<DataClass?>::isNotIn).withNullableSuffix(),
    fun2<String, String, Expect<String>.() -> Unit>(Companion::because),

    "${Expect<Int?>::toBe.name}(null)" to Companion::toBeNull,
    fun1(Expect<Int?>::toBeNullIfNullGivenElse),
    ("isA" to Companion::isAIntFeature).withFeatureSuffix(),
    "isA" to Companion::isAInt,
    ("isA" to Companion::isASuperTypeFeature).withFeatureSuffix(),
    "isA" to Companion::isASuperType,
    ("isA" to Companion::isASubTypeFeature).withFeatureSuffix(),
    "isA" to Companion::isASubType,

    ("notToBeNull" to Companion::notToBeNullFeature).withFeatureSuffix(),
    "notToBeNull" to Companion::notToBeNull,

    getAndImmediatePair(),
    getAndLazyPair(),
    "- "
) {

    companion object : WithAsciiReporter() {
        private fun toBeNull(expect: Expect<Int?>) = expect toBe null

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

        private fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> =
            "non existing in infix" to { e: Expect<Int> -> e }

        private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
        private fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
            andLazyName.name to Expect<Int>::and

        private fun notToBeNullFeature(expect: Expect<Int?>) =
            expect notToBeNull o

        private fun notToBeNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
            expect notToBeNull assertionCreator

        private fun isNoneOfInt(expect: Expect<Int>, expected: Int, otherValues: Array<out Int>): Expect<Int> =
            expect isNoneOf values(expected, *otherValues)

        private fun isNoneOfIntNullable(expect: Expect<Int?>, expected: Int?, otherValues: Array<out Int?>): Expect<Int?> =
            expect isNoneOf values(expected, *otherValues)

        private fun isNoneOfDataClass(expect: Expect<DataClass>, expected: DataClass, otherValues: Array<out DataClass>): Expect<DataClass> =
            expect isNoneOf values(expected, *otherValues)

        private fun isNoneOfDataClassNullable(expect: Expect<DataClass?>, expected: DataClass?, otherValues: Array<out DataClass?>): Expect<DataClass?> =
            expect isNoneOf values(expected, *otherValues)

        private fun because(expect: Expect<String>, reason: String, assertionCreator: Expect<String>.() -> Unit) =
           expect because of(reason) { assertionCreator() }
    }

    @Suppress("unused")
    fun ambiguityTest() {
        val a1: Expect<Number> = notImplemented()
        val a1b: Expect<Number?> = notImplemented()

        a1 toBe 1
        a1 toBe 1.2
        a1 notToBe 1
        a1 notToBe 1.2
        a1 isSameAs 1
        a1 isSameAs 1.2
        a1 isNotSameAs 1
        a1 isNotSameAs 1.2
        a1.isA<Int>()
        a1.isA<Int> {}
        a1 isNoneOf values(1, 2)
        a1 isNotIn listOf(1, 1.2)
        a1 because of ("hello") { toBe(1) }

        a1b toBe 1
        a1b toBe 1.2
        a1b notToBe 1
        a1b notToBe 1.2
        a1b isSameAs 1
        a1b isSameAs 1.2
        a1b isNotSameAs 1
        a1b isNotSameAs 1.2
        a1b.isA<Int>()
        a1b.isA<Int> {}
        a1b isNoneOf values(1, 2)
        a1b isNotIn listOf(1, 1.2)

        a1b notToBeNull o toBe 1
        a1b notToBeNull {}

        a1 and o toBe 1
        a1 and { it toBe 1 }

    }

    //regression for #298, should compile without the need for E : Any or List<E?>
    @Suppress("unused")
    fun <E> Expect<List<E>>.firstIs(value: E) = it get index(0) { it toBe value }
}
