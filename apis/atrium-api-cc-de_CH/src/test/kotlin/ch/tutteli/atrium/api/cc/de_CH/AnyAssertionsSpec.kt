package ch.tutteli.atrium.api.cc.de_CH

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
    AssertionPlant<Int>::ist.name,
    AssertionPlant<Int>::istNicht.name,
    AssertionPlant<Int>::istSelbeInstanzWie.name,
    AssertionPlant<Int>::istNichtSelbeInstanzWie.name,
    AssertionPlantNullable<Int?>::istNull.name to AssertionPlantNullable<Int?>::istNull,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.AnyAssertionsSpecFunFactory<T> {
        override val toBeFun = AssertionPlant<T>::ist
        override val notToBeFun = AssertionPlant<T>::istNicht
        override val isSameFun = AssertionPlant<T>::istSelbeInstanzWie
        override val isNotSameFun = AssertionPlant<T>::istNichtSelbeInstanzWie
    }

    companion object {

        private fun andImmediateName(): String {
            val f: KProperty1<AssertionPlant<Int>, AssertionPlant<Int>> = AssertionPlant<Int>::und
            return f.name
        }

        fun getAndImmediatePair(): Pair<String, AssertionPlant<Int>.() -> AssertionPlant<Int>>
            = andImmediateName() to AssertionPlant<Int>::und

        private fun andLazyName(): String {
            val f: KFunction2<AssertionPlant<Int>, AssertionPlant<Int>.() -> Unit, AssertionPlant<Int>> = AssertionPlant<Int>::und
            return f.name
        }

        fun getAndLazyPair(): Pair<String, AssertionPlant<Int>.(AssertionPlant<Int>.() -> Unit) -> AssertionPlant<Int>>
            = andLazyName() to AssertionPlant<Int>::und
    }
}
