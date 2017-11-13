package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec

object AnyAssertionsSpec : ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    IAssertionPlant<Int>::toBe.name,
    IAssertionPlant<Int>::notToBe.name,
    IAssertionPlant<Int>::isSame.name,
    IAssertionPlant<Int>::isNotSame.name,
    IAssertionPlantNullable<Int?>::isNull.name to IAssertionPlantNullable<Int?>::isNull,
    IAssertionPlant<Int>::and.name to IAssertionPlant<Int>::and
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.IAnyAssertionsSpecFunFactory<T> {
        override val toBe = IAssertionPlant<T>::toBe
        override val notToBe = IAssertionPlant<T>::notToBe
        override val isSame = IAssertionPlant<T>::isSame
        override val isNotSame = IAssertionPlant<T>::isNotSame
    }
}
