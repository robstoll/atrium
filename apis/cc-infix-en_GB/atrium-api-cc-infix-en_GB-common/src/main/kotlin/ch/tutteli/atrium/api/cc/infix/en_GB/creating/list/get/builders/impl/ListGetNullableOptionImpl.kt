// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class ListGetNullableOptionImpl<E, T: List<E>>(
    override val plant: Assert<T>,
    override val index: Int
) : ListGetNullableOption<E, T> {

    @Suppress("DEPRECATION")
    override infix fun assertIt(assertionCreator: AssertionPlantNullable<E>.() -> Unit): Assert<T>
        = plant.addAssertion(AssertImpl.list.getNullable(plant, index, assertionCreator))
}
