package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2

class AnyAssertionsSpec : ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    IAssertionPlant<Int>::toBe.name,
    IAssertionPlant<Int>::notToBe.name,
    IAssertionPlant<Int>::isSame.name,
    IAssertionPlant<Int>::isNotSame.name,
    IAssertionPlantNullable<Int?>::isNull.name to IAssertionPlantNullable<Int?>::isNull,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.IAnyAssertionsSpecFunFactory<T> {
        override val toBeFun = IAssertionPlant<T>::toBe
        override val notToBeFun = IAssertionPlant<T>::notToBe
        override val isSameFun = IAssertionPlant<T>::isSame
        override val isNotSameFun = IAssertionPlant<T>::isNotSame
    }

    companion object {

        private fun andImmediateName(): String {
            val f: KProperty1<IAssertionPlant<Int>, IAssertionPlant<Int>> = IAssertionPlant<Int>::and
            return f.name
        }

        fun getAndImmediatePair(): Pair<String, IAssertionPlant<Int>.() -> IAssertionPlant<Int>>
            = andImmediateName() to IAssertionPlant<Int>::and

        private fun andLazyName(): String {
            val f: KFunction2<IAssertionPlant<Int>, IAssertionPlant<Int>.() -> Unit, IAssertionPlant<Int>> = IAssertionPlant<Int>::and
            return f.name
        }

        fun getAndLazyPair(): Pair<String, IAssertionPlant<Int>.(IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>>
            = andLazyName() to IAssertionPlant<Int>::and
    }
}
