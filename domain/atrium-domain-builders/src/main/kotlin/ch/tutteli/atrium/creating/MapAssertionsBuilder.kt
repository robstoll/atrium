@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

object MapAssertionsBuilder : IMapAssertions {
    override inline fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int)
        = MapAssertions.hasSize(plant, size)

    override inline fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>)
        = MapAssertions.isEmpty(plant)

    override inline fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>)
        = MapAssertions.isNotEmpty(plant)
}
