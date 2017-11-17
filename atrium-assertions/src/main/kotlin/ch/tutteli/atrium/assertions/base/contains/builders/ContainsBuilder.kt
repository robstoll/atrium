package ch.tutteli.atrium.assertions.base.contains.builders

import ch.tutteli.atrium.assertions.base.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant

abstract class ContainsBuilder<out T : Any, D: IContains.IDecorator>(
    val plant: IAssertionPlant<T>, val decorator: D
)
