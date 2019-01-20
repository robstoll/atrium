package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.integration.AnyAssertionsSpec
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
    AssertionPlantNullable<Int?>::toBeNullable.name to Companion::toBeNullable,
    AssertionPlantNullable<Int?>::toBeNullIfNullElse.name to Companion::toBeNullIfNullElse,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.AnyAssertionsSpecFunFactory<T> {
        override val toBeFun: Assert<T>.(T) -> Assert<T> = { o toBe it }
        override val notToBeFun: Assert<T>.(T) -> Assert<T> = { o notToBe it }
        override val isSameFun: Assert<T>.(T) -> Assert<T> = { o isSameAs it }
        override val isNotSameFun: Assert<T>.(T) -> Assert<T> = { o isNotSameAs it }
    }

    companion object {

        fun toBeName(): String{
            val f : KFunction2<Assert<Int>, Int, Assert<Int>> = Assert<Int>::toBe
            return f.name
        }

        fun noToBeName(): String{
            val f : KFunction2<Assert<Int>, Int, Assert<Int>> = Assert<Int>::notToBe
            return f.name
        }

        private fun toBeNull(plant: AssertionPlantNullable<Int?>){
            plant toBe null
        }

        private fun toBeNullable(plant: AssertionPlantNullable<Int?>, expected: Int?)
            = plant toBeNullable expected

        private fun toBeNullIfNullElse(plant: AssertionPlantNullable<Int?>, assertionCreator: (Assert<Int>.() -> Unit)?)
            = plant toBeNullIfNullElse assertionCreator

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
