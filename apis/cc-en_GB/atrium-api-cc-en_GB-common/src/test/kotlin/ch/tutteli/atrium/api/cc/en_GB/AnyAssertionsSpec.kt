package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

//import kotlin.reflect.KFunction2
//import kotlin.reflect.KProperty1

class AnyAssertionsSpec : ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
    AssertionVerbFactory,
    Expect<Int>::toBe,
    Expect<DataClass>::toBe,
    Expect<Int>::toBe.name,
    "${Expect<Int?>::toBe.name}(null)" to Companion::toBeNull,
    "${Expect<Int?>::toBe.name} nullable" to Expect<Int?>::toBe,
    Expect<DataClass?>::toBe,
    Expect<Int?>::toBeNullIfNullGivenElse.name to Expect<Int?>::toBeNullIfNullGivenElse
//    Assert<Int>::notToBe.name
//    Assert<Int>::isSameAs.name,
//    Assert<Int>::isNotSameAs.name,
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
