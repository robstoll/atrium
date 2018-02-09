@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

object CollectionAssertionsBuilder : ICollectionAssertions {
    override inline fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int)
        = CollectionAssertions.hasSize(plant, size)

    override inline fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>)
        = CollectionAssertions.isEmpty(plant)

    override inline fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>)
        = CollectionAssertions.isNotEmpty(plant)
}
