package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.integration.AnyAssertionsSpec
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

class AnyAssertionsSpec : ch.tutteli.atrium.spec.integration.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    Assert<Int>::toBe.name,
    Assert<Int>::notToBe.name,
    Assert<Int>::isSame.name,
    Assert<Int>::isNotSame.name,
    "${AssertionPlantNullable<Int?>::toBe.name}(null)" to Companion::toBeNull,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.AnyAssertionsSpecFunFactory<T> {
        override val toBeFun = Assert<T>::toBe
        override val notToBeFun = Assert<T>::notToBe
        override val isSameFun = Assert<T>::isSame
        override val isNotSameFun = Assert<T>::isNotSame
    }

    companion object {

        private fun toBeNull(plant: AssertionPlantNullable<Int?>){
            plant.toBe(null)
        }

        private val andImmediate : KProperty1<Assert<Int>, Assert<Int>> = Assert<Int>::and
        fun getAndImmediatePair(): Pair<String, Assert<Int>.() -> Assert<Int>>
            = andImmediate.name to Assert<Int>::and

        private val andLazyName : KFunction2<Assert<Int>, Assert<Int>.() -> Unit, Assert<Int>> = Assert<Int>::and
        fun getAndLazyPair(): Pair<String, Assert<Int>.(Assert<Int>.() -> Unit) -> Assert<Int>> =
            andLazyName.name to Assert<Int>::and
    }
}
