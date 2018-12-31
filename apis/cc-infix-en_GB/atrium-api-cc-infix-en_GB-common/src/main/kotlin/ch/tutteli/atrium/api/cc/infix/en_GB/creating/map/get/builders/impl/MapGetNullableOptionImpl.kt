package ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetNullableOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class MapGetNullableOptionImpl<K, V>(
    override val plant: Assert<Map<K, V>>,
    override val key: K
) : MapGetNullableOption<K, V> {

    override infix fun assertIt(assertionCreator: AssertionPlantNullable<V>.() -> Unit): Assert<Map<K, V>>
        = plant.addAssertion(AssertImpl.map.getExistingNullable(plant, key, assertionCreator))
}
