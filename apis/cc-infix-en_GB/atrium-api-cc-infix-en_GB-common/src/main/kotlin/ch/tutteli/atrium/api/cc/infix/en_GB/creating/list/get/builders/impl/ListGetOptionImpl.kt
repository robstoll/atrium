package ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class ListGetOptionImpl<E : Any, T: List<E>>(
    override val plant: Assert<T>,
    override val index: Int
) : ListGetOption<E, T> {

    @Suppress("DEPRECATION")
    override infix fun assertIt(assertionCreator: Assert<E>.() -> Unit): Assert<T>
        = plant.addAssertion(AssertImpl.list.get(plant, index, assertionCreator))
}
