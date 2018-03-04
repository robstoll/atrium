package ch.tutteli.atrium.creating

/**
 * Robstoll's implementation of [ICollectionAssertions].
 */
object CollectionAssertions : ICollectionAssertions {

    override fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int)
        = _hasSize(plant, size)

    override fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>)
        = _isEmpty(plant)

    override fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>)
        = _isNotEmpty(plant)
}
