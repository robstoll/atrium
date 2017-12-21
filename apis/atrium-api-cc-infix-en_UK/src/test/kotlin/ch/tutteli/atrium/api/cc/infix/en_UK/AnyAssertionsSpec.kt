package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec
import kotlin.reflect.KFunction2

class AnyAssertionsSpec : ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    AssertionPlant<Int>::toBe.name,
    AssertionPlant<Int>::notToBe.name,
    AssertionPlant<Int>::isSame.name,
    AssertionPlant<Int>::isNotSame.name,
    "${AssertionPlantNullable<Int?>::toBe.name} null" to Companion::isNull,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.AnyAssertionsSpecFunFactory<T> {
        override val toBeFun: AssertionPlant<T>.(T) -> AssertionPlant<T> = { this toBe it }
        override val notToBeFun: AssertionPlant<T>.(T) -> AssertionPlant<T> = { this notToBe it }
        override val isSameFun: AssertionPlant<T>.(T) -> AssertionPlant<T> = { this isSame it }
        override val isNotSameFun: AssertionPlant<T>.(T) -> AssertionPlant<T> = { this isNotSame it }
    }

    companion object {

        private fun isNull(plant: AssertionPlantNullable<Int?>){
            plant toBe null
        }

        fun getAndImmediatePair(): Pair<String, AssertionPlant<Int>.() -> AssertionPlant<Int>>
            = andLazyName() to AssertionPlant<Int>::and

        private fun andLazyName(): String {
            val f: KFunction2<AssertionPlant<Int>, AssertionPlant<Int>.() -> Unit, AssertionPlant<Int>> = AssertionPlant<Int>::and
            return f.name
        }

        fun getAndLazyPair(): Pair<String, AssertionPlant<Int>.(AssertionPlant<Int>.() -> Unit) -> AssertionPlant<Int>>
            = andLazyName() to AssertionPlant<Int>::and
    }
}

/**
 * not supported in the API, we provide it here so that we can still reuse AnyAssertionsSpec
 */
private val AssertionPlant<Int>.and: AssertionPlant<Int> get() = this
