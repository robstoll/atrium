package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec
import kotlin.reflect.KProperty1

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
        override val toBe = IAssertionPlant<T>::toBe
        override val notToBe = IAssertionPlant<T>::notToBe
        override val isSame = IAssertionPlant<T>::isSame
        override val isNotSame = IAssertionPlant<T>::isNotSame
    }

    companion object {

        private fun andImmediateName(): String {
            val f: KProperty1<IAssertionPlant<Int>, IAssertionPlant<Int>> = IAssertionPlant<Int>::and
            return f.name
        }

        fun getAndImmediatePair(): Pair<String, IAssertionPlant<Int>.() -> IAssertionPlant<Int>>
            = andImmediateName() to IAssertionPlant<Int>::and

        private fun andLazyName(): String {
            val f: KProperty1<IAssertionPlant<Int>, IAssertionPlant<Int>> = IAssertionPlant<Int>::and
            return f.name
        }

        fun getAndLazyPair(): Pair<String, IAssertionPlant<Int>.(IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>>
            = andLazyName() to IAssertionPlant<Int>::and
    }
}
