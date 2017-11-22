package ch.tutteli.atrium.assertions.basic.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant

abstract class ContainsBuilder<out T : Any, D: IContains.IDecorator>(
    val plant: IAssertionPlant<T>, val decorator: D
)
