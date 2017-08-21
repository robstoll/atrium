package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec

object AnyAssertionsSpec : ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    IAssertionPlant<Int>::ist.name,
    IAssertionPlant<Int>::istNicht.name,
    IAssertionPlant<Int>::istSelbeInstanzWie.name,
    IAssertionPlant<Int>::istNichtSelbeInstanzWie.name
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.IAnyAssertionsSpecFunFactory<T> {
        override val toBe = IAssertionPlant<T>::ist
        override val notToBe = IAssertionPlant<T>::istNicht
        override val isSame = IAssertionPlant<T>::istSelbeInstanzWie
        override val isNotSame = IAssertionPlant<T>::istNichtSelbeInstanzWie
    }
}
