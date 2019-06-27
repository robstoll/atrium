package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

//import kotlin.reflect.KFunction2
//import kotlin.reflect.KProperty1

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
    Expect<Int?>::toBeNullIfNullGivenElse.name to Expect<Int?>::toBeNullIfNullGivenElse
//    getAndImmediatePair(),
//    getAndLazyPair()
) {

    companion object {
        private fun toBeNull(plant: Expect<Int?>) = plant.toBe(null)
//
//        private val andImmediate: KProperty1<Assert<Int>, Assert<Int>> = Assert<Int>::and
//        fun getAndImmediatePair(): Pair<String, Assert<Int>.() -> Assert<Int>> = andImmediate.name to Assert<Int>::and
//
//        private val andLazyName: KFunction2<Assert<Int>, Assert<Int>.() -> Unit, Assert<Int>> = Assert<Int>::and
//        fun getAndLazyPair(): Pair<String, Assert<Int>.(Assert<Int>.() -> Unit) -> Assert<Int>> =
//            andLazyName.name to Assert<Int>::and
    }
}
