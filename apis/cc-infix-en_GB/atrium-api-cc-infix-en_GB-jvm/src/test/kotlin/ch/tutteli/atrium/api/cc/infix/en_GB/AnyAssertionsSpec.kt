@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.KFunction2

class AnyAssertionsSpec : ch.tutteli.atrium.spec.integration.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    toBeName(),
    noToBeName(),
    Assert<Int>::isSameAs.name,
    Assert<Int>::isNotSameAs.name,
    "${AssertionPlantNullable<Int?>::toBe.name} null" to Companion::toBeNull,
    toBeNullableFun.name to Companion::toBeNullable,
    "${toBeNullableCreatorFun.name} with creator" to Companion::toBeNullIfNullGivenElse,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : ch.tutteli.atrium.spec.integration.AnyAssertionsSpec.AnyAssertionsSpecFunFactory<T> {
        override val toBeFun: Assert<T>.(T) -> Assert<T> = { o toBe it }
        override val notToBeFun: Assert<T>.(T) -> Assert<T> = { o notToBe it }
        override val isSameFun: Assert<T>.(T) -> Assert<T> = { o isSameAs it }
        override val isNotSameFun: Assert<T>.(T) -> Assert<T> = { o isNotSameAs it }
    }

    companion object {
        private val toBeNullableFun: KFunction2<AssertionPlantNullable<Int?>, Int?, Unit> = AssertionPlantNullable<Int?>::toBe
        private val toBeNullableCreatorFun: KFunction2<AssertionPlantNullable<Int?>, (Assert<Int>.() -> Unit)?, Unit> = AssertionPlantNullable<Int?>::toBeNullIfNullGivenElse

        fun toBeName(): String{
            val f : KFunction2<Assert<Int>, Int, Assert<Int>> = Assert<Int>::toBe
            return f.name
        }

        fun noToBeName(): String{
            val f : KFunction2<Assert<Int>, Int, Assert<Int>> = Assert<Int>::notToBe
            return f.name
        }

        private fun toBeNull(plant: AssertionPlantNullable<Int?>) {
            plant toBe null
        }

        private fun toBeNullable(plant: AssertionPlantNullable<Int?>, expected: Int?)
            = plant toBe expected

        private fun toBeNullIfNullGivenElse(plant: AssertionPlantNullable<Int?>, assertionCreator: (Assert<Int>.() -> Unit)?)
            = plant toBeNullIfNullGivenElse assertionCreator

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
