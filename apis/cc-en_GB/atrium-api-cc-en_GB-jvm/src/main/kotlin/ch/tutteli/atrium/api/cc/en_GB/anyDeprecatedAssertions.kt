@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

@Deprecated("Use the extension function, will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.", ReplaceWith("plant.toBe(null)"))
@Suppress("UNUSED_PARAMETER")
fun <T : Any?> toBe(plant: AssertionPlantNullable<T>, void: Void?) {
    plant.addAssertion(AssertImpl.any.toBeNull(plant))
}
