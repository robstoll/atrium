package ch.tutteli.atrium.creating

class CollectingAssertionPlant<out T : Any>(
    private val subjectProvider: () -> T
) : BaseAssertionPlant<T>(), ICollectingAssertionPlant<T> {

    override val subject get() = subjectProvider()
}
