@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.CollectingAssertionPlantNullable

class CollectingAssertionPlantNullableImpl<out T>(
    subjectProvider: () -> T
) : MutableListBasedAssertionPlant<T, AssertionPlantNullable<T>>(subjectProvider),
    CollectingAssertionPlantNullable<T> {

    override val self = this
}
