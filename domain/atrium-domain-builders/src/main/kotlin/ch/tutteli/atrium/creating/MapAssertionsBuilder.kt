@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

object MapAssertionsBuilder : MapAssertions {
    override inline fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int)
        = mapAssertions.hasSize(plant, size)

    override inline fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>)
        = mapAssertions.isEmpty(plant)

    override inline fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>)
        = mapAssertions.isNotEmpty(plant)
}
