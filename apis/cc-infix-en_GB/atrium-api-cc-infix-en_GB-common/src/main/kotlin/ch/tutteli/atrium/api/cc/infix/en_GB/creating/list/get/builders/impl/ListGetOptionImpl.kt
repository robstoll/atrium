package ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class ListGetOptionImpl<T : Any>(
    override val plant: Assert<List<T>>,
    override val index: Int
) : ListGetOption<T> {

    override infix fun assertIt(assertionCreator: Assert<T>.() -> Unit): Assert<List<T>> =
        plant.addAssertion(AssertImpl.list.get(plant, index, assertionCreator))
}
