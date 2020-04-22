// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl

internal class MapGetOptionImpl<K, V : Any, T: Map<out K, V>>(
    override val plant: Assert<T>,
    override val key: K
) : MapGetOption<K, V, T> {

    @Suppress("DEPRECATION")
    override infix fun assertIt(assertionCreator: Assert<V>.() -> Unit): Assert<T>
        = plant.addAssertion(AssertImpl.map.getExisting(plant, key, assertionCreator))
}
