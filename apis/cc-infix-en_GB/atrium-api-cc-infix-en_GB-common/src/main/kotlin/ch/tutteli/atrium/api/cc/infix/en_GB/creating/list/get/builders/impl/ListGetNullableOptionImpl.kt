package ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class ListGetNullableOptionImpl<T>(
    override val plant: Assert<List<T>>,
    override val index: Int
) : ListGetNullableOption<T> {

    override infix fun assertIt(assertionCreator: AssertionPlantNullable<T>.() -> Unit): Assert<List<T>>
        = plant.addAssertion(AssertImpl.list.getNullable(plant, index, assertionCreator))
}
