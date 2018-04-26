package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.integration.AnyAssertionsSpec
import kotlin.reflect.KFunction2

class AnyAssertionsSpec : ch.tutteli.atrium.spec.integration.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    Assert<Int>::toBe.name,
    Assert<Int>::notToBe.name,
    Assert<Int>::isSame.name,
    Assert<Int>::isNotSame.name,
    "${AssertionPlantNullable<Int?>::toBe.name} null" to Companion::toBeNull,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.AnyAssertionsSpecFunFactory<T> {
        override val toBeFun: Assert<T>.(T) -> Assert<T> = { this toBe it }
        override val notToBeFun: Assert<T>.(T) -> Assert<T> = { this notToBe it }
        override val isSameFun: Assert<T>.(T) -> Assert<T> = { this isSame it }
        override val isNotSameFun: Assert<T>.(T) -> Assert<T> = { this isNotSame it }
    }

    companion object {

        private fun toBeNull(plant: AssertionPlantNullable<Int?>){
            plant toBe null
        }

        fun getAndImmediatePair(): Pair<String, Assert<Int>.() -> Assert<Int>>
            = andLazyName() to Assert<Int>::and

        private fun andLazyName(): String {
            val f: KFunction2<Assert<Int>, Assert<Int>.() -> Unit, Assert<Int>> = Assert<Int>::and
            return f.name
        }

        fun getAndLazyPair(): Pair<String, Assert<Int>.(Assert<Int>.() -> Unit) -> Assert<Int>>
            = andLazyName() to Assert<Int>::and
    }
}

/**
 * not supported in the API, we provide it here so that we can still reuse AnyAssertionsSpec
 */
private val Assert<Int>.and: Assert<Int> get() = this
