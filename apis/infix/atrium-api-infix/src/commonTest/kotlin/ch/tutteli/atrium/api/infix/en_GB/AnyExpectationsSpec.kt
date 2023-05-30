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
    fun1(Expect<Int>::toBeTheInstance),
    fun1(Expect<DataClass>::toBeTheInstance),
    fun1(Expect<Int?>::toBeTheInstance).withNullableSuffix(),
    fun1(Expect<DataClass?>::toBeTheInstance).withNullableSuffix(),
    fun1(Expect<Int>::notToBeTheInstance),
    fun1(Expect<DataClass>::notToBeTheInstance),
    fun1(Expect<Int?>::notToBeTheInstance).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToBeTheInstance).withNullableSuffix(),
    fun2(Companion::notToEqualOneOfInt),
    fun2(Companion::notToEqualOneOfDataClass),
    fun2(Companion::notToEqualOneOfIntNullable).withNullableSuffix(),
    fun2(Companion::notToEqualOneOfDataClassNullable).withNullableSuffix(),
    fun1(Expect<Int>::notToEqualOneIn),
    fun1(Expect<DataClass>::notToEqualOneIn),
    fun1(Expect<Int?>::notToEqualOneIn).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToEqualOneIn).withNullableSuffix(),
    fun2<String, String, Expect<String>.() -> Unit>(Companion::because),
    fun2<Int, String, Expect<Int>.() -> Unit>(Companion::becauseOfInt),

    "${Expect<Int?>::toEqual.name}(null)" to Companion::toEqualNull,
    fun1(Expect<Int?>::toEqualNullIfNullGivenElse),
    ("toBeAnInstanceOf" to Companion::toBeAnInstanceOfIntFeature).withFeatureSuffix(),
    "toBeAnInstanceOf" to Companion::toBeAnInstanceOfInt,
    ("toBeAnInstanceOf" to Companion::toBeAnInstanceOfSuperTypeFeature).withFeatureSuffix(),
    "toBeAnInstanceOf" to Companion::toBeAnInstanceOfSuperType,
    ("toBeAnInstanceOf" to Companion::toBeAnInstanceOfSubTypeFeature).withFeatureSuffix(),
    "toBeAnInstanceOf" to Companion::toBeAnInstanceOfSubType,
    ("notToBeAnInstanceOf" to Companion::notToBeAnInstanceOfFeature).withFeatureSuffix(),
    "notToBeAnInstanceOf" to Companion::notToBeAnInstanceOf,

    ("notToEqualNull" to Companion::notToEqualNullFeature).withFeatureSuffix(),
    "notToEqualNull" to Companion::notToEqualNull,

    getAndImmediatePair(),
    getAndLazyPair()
) {

    companion object {
        private fun toEqualNull(expect: Expect<Int?>) = expect toEqual null

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfIntFeature(expect: Expect<out Any?>): Expect<Int> =
            expect.toBeAnInstanceOf()

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
        private fun notToBeAnInstanceOfFeature(expect: Expect<out Any?>): Expect<Boolean> =
            expect.notToBeAnInstanceOf<SuperType>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun toBeAnInstanceOfSubType(
            expect: Expect<out Any?>,
            assertionCreator: Expect<SubType>.() -> Unit
        ): Expect<SubType> =
            expect.toBeAnInstanceOf<SubType> { assertionCreator() }

        private fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> =
            "and o" to { e: Expect<Int> ->
                e and o
            }
        @Suppress("RemoveExplicitTypeArguments")
        private fun notToBeAnInstanceOf(
            expect: Expect<out Any?>,
            assertionCreator: Expect<SuperType>.() -> Unit
        ): Expect<Boolean> =
            expect.notToBeAnInstanceOf<SuperType> { assertionCreator() }

        private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
        private fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
            andLazyName.name to { e: Expect<Int>, assertionCreator: Expect<Int>.() -> Unit ->
                e and { assertionCreator() }
            }

        private fun notToEqualNullFeature(expect: Expect<Int?>) =
            expect notToEqualNull o

        private fun notToEqualNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
            expect notToEqualNull assertionCreator

        private fun notToEqualOneOfInt(expect: Expect<Int>, expected: Int, otherValues: Array<out Int>): Expect<Int> =
            expect notToEqualOneOf values(expected, *otherValues)

        private fun notToEqualOneOfIntNullable(
            expect: Expect<Int?>,
            expected: Int?,
            otherValues: Array<out Int?>
        ): Expect<Int?> =
            expect notToEqualOneOf values(expected, *otherValues)

        private fun notToEqualOneOfDataClass(
            expect: Expect<DataClass>,
            expected: DataClass,
            otherValues: Array<out DataClass>
        ): Expect<DataClass> =
            expect notToEqualOneOf values(expected, *otherValues)

        private fun notToEqualOneOfDataClassNullable(
            expect: Expect<DataClass?>,
            expected: DataClass?,
            otherValues: Array<out DataClass?>
        ): Expect<DataClass?> =
            expect notToEqualOneOf values(expected, *otherValues)

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
        a1 toBeTheInstance 1
        a1 toBeTheInstance 1.2
        a1 notToBeTheInstance 1
        a1 notToBeTheInstance 1.2
        a1.toBeAnInstanceOf<Int>()
        a1.toBeAnInstanceOf<Int> {}
        a1 notToEqualOneOf values(1, 2)
        a1 notToEqualOneIn listOf(1, 1.2)
        a1 because of("hello") { toEqual(1) }

        a1b toEqual 1
        a1b toEqual 1.2
        a1b notToEqual 1
        a1b notToEqual 1.2
        a1b toBeTheInstance 1
        a1b toBeTheInstance 1.2
        a1b notToBeTheInstance 1
        a1b notToBeTheInstance 1.2
        a1b.toBeAnInstanceOf<Int>()
        a1b.toBeAnInstanceOf<Int> {}
        a1b notToEqualOneOf values(1, 2)
        a1b notToEqualOneIn listOf(1, 1.2)

        a1b notToEqualNull o toEqual 1
        a1b notToEqualNull { }

        a1 and o toEqual 1
        a1 and { it toEqual 1 }

    }

    //regression for #298, should compile without the need for E : Any or List<E?>
    @Suppress("unused")
    fun <E> Expect<List<E>>.firstIs(value: E) = it get index(0) { it toEqual value }
}
