package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec
import kotlin.reflect.KProperty1

class AnyAssertionsSpec : ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    IAssertionPlant<Int>::ist.name,
    IAssertionPlant<Int>::istNicht.name,
    IAssertionPlant<Int>::istSelbeInstanzWie.name,
    IAssertionPlant<Int>::istNichtSelbeInstanzWie.name,
    IAssertionPlantNullable<Int?>::istNull.name to IAssertionPlantNullable<Int?>::istNull,
    getAndImmediatePair(),
    getAndLazyPair()
    ) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.IAnyAssertionsSpecFunFactory<T> {
        override val toBeFun = IAssertionPlant<T>::ist
        override val notToBeFun = IAssertionPlant<T>::istNicht
        override val isSameFun = IAssertionPlant<T>::istSelbeInstanzWie
        override val isNotSameFun = IAssertionPlant<T>::istNichtSelbeInstanzWie
    }

    companion object {

        private fun andImmediateName(): String {
            val f: KProperty1<IAssertionPlant<Int>, IAssertionPlant<Int>> = IAssertionPlant<Int>::und
            return f.name
        }

        fun getAndImmediatePair(): Pair<String, IAssertionPlant<Int>.() -> IAssertionPlant<Int>>
            = andImmediateName() to IAssertionPlant<Int>::und

        private fun andLazyName(): String {
            val f: KProperty1<IAssertionPlant<Int>, IAssertionPlant<Int>> = IAssertionPlant<Int>::und
            return f.name
        }

        fun getAndLazyPair(): Pair<String, IAssertionPlant<Int>.(IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>>
            = andLazyName() to IAssertionPlant<Int>::und
    }
}
