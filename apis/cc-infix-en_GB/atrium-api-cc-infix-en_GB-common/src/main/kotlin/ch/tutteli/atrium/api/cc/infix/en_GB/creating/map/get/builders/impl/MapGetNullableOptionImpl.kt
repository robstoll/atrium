package ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetNullableOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class MapGetNullableOptionImpl<K, V, T: Map<out K, V>>(
    override val plant: Assert<T>,
    override val key: K
) : MapGetNullableOption<K, V, T> {

    override infix fun assertIt(assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assert<T>
        = plant.addAssertion(AssertImpl.map.getExistingNullable(plant, key, assertionCreator))
}
