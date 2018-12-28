package ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class MapGetOptionImpl<K, V : Any>(
    override val plant: Assert<Map<K, V>>,
    override val key: K
) : MapGetOption<K, V> {

    override infix fun assertIt(assertionCreator: Assert<V>.() -> Unit): Assert<Map<K, V>> =
        plant.addAssertion(AssertImpl.map.getExisting(plant, key, assertionCreator))
}
