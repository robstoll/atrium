package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

class AnyAssertionsSpec : ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    AssertionPlant<Int>::toBe.name,
    AssertionPlant<Int>::notToBe.name,
    AssertionPlant<Int>::isSame.name,
    AssertionPlant<Int>::isNotSame.name,
    AssertionPlantNullable<Int?>::isNull.name to AssertionPlantNullable<Int?>::isNull,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.AnyAssertionsSpecFunFactory<T> {
        override val toBeFun = AssertionPlant<T>::toBe
        override val notToBeFun = AssertionPlant<T>::notToBe
        override val isSameFun = AssertionPlant<T>::isSame
        override val isNotSameFun = AssertionPlant<T>::isNotSame
    }

    companion object {

        private fun andImmediateName(): String {
            val f: KProperty1<AssertionPlant<Int>, AssertionPlant<Int>> = AssertionPlant<Int>::and
            return f.name
        }

        fun getAndImmediatePair(): Pair<String, AssertionPlant<Int>.() -> AssertionPlant<Int>>
            = andImmediateName() to AssertionPlant<Int>::and

        private fun andLazyName(): String {
            val f: KFunction2<AssertionPlant<Int>, AssertionPlant<Int>.() -> Unit, AssertionPlant<Int>> = AssertionPlant<Int>::and
            return f.name
        }

        fun getAndLazyPair(): Pair<String, AssertionPlant<Int>.(AssertionPlant<Int>.() -> Unit) -> AssertionPlant<Int>>
            = andLazyName() to AssertionPlant<Int>::and
    }
}
