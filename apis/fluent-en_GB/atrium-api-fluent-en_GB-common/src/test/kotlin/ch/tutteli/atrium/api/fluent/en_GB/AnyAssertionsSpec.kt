package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

class AnyAssertionsSpec : ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
    AssertionVerbFactory,
    Expect<Int>::toBe.name to Expect<Int>::toBe,
    Expect<DataClass>::toBe.name to Expect<DataClass>::toBe,
    "${Expect<Int?>::toBe.name} nullable" to Expect<Int?>::toBe,
    "${Expect<DataClass?>::toBe.name} nullable" to Expect<DataClass?>::toBe,
    Expect<Int>::notToBe.name to Expect<Int>::notToBe,
    Expect<DataClass>::notToBe.name to Expect<DataClass>::notToBe,
    "${Expect<Int?>::notToBe.name} nullable" to Expect<Int?>::notToBe,
    "${Expect<DataClass?>::notToBe.name} nullable" to Expect<DataClass?>::notToBe,
    Expect<Int>::isSameAs.name to Expect<Int>::isSameAs,
    Expect<DataClass>::isSameAs.name to Expect<DataClass>::isSameAs,
    "${Expect<Int?>::isSameAs.name} nullable" to Expect<Int?>::isSameAs,
    "${Expect<DataClass?>::isSameAs.name} nullable" to Expect<DataClass?>::isSameAs,
    Expect<Int>::isNotSameAs.name to Expect<Int>::isNotSameAs,
    Expect<DataClass>::isNotSameAs.name to Expect<DataClass>::isNotSameAs,
    "${Expect<Int?>::isNotSameAs.name} nullable" to Expect<Int?>::isNotSameAs,
    "${Expect<DataClass?>::isNotSameAs.name} nullable" to Expect<DataClass?>::isNotSameAs,

    "${Expect<Int?>::toBe.name}(null)" to Companion::toBeNull,
    Expect<Int?>::toBeNullIfNullGivenElse.name to Expect<Int?>::toBeNullIfNullGivenElse,
    "isA" to Companion::isAFeature,
    "isA" to Companion::isAStringToInt,
    "isA" to Companion::isAStringToInt,
    "isA" to Companion::isAString,
    "isA" to Companion::isACharSequence,
    "isA" to Companion::isASubType,
    "isA" to Companion::isAIntLess,
    "notToBeNull" to Companion::notToBeNull,
    Companion::notToBeNullLess,
    Companion::notToBeNullGreaterAndLess,

    getAndImmediatePair(),
    getAndLazyPair()
) {

    companion object {
        private fun toBeNull(expect: Expect<Int?>) = expect.toBe(null)

        private fun isAFeature(expect: Expect<Int?>): Expect<Int> = expect.isA()

        private val andImmediate: KProperty1<Expect<Int>, Expect<Int>> = Expect<Int>::and
        fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> = andImmediate.name to Expect<Int>::and

        private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
        fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
            andLazyName.name to Expect<Int>::and

        private inline fun <reified TSub : Any> isA(
            expect: Expect<*>,
            noinline assertionCreator: Expect<TSub>.() -> Unit
        ) = expect.isA(assertionCreator)

        //TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
        private fun isAStringToInt(expect: Expect<*>, assertionCreator: Expect<Int>.() -> Unit) =
            isA(expect, assertionCreator)

        private fun isAString(expect: Expect<*>, assertionCreator: Expect<String>.() -> Unit) =
            isA(expect, assertionCreator)

        private fun isACharSequence(expect: Expect<*>, assertionCreator: Expect<CharSequence>.() -> Unit) =
            isA(expect, assertionCreator)

        private fun isASubType(expect: Expect<*>, assertionCreator: Expect<SubType>.() -> Unit) =
            isA(expect, assertionCreator)

        private fun isAIntLess(expect: Expect<Number>, number: Int) = expect.isA<Int> { isLessThan(number) }

        private fun notToBeNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
            expect.notToBeNull(assertionCreator)

        private fun notToBeNullLess(expect: Expect<Int?>, number: Int) =
            expect.notToBeNull { isLessThan(number) }

        private fun notToBeNullGreaterAndLess(expect: Expect<Int?>, lowerBound: Int, upperBound: Int) =
            expect.notToBeNull {
                isGreaterThan(lowerBound)
                isLessThan(upperBound)
            }
    }
}
