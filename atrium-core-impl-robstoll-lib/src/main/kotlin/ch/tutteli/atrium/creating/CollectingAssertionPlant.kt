package ch.tutteli.atrium.creating

class CollectingAssertionPlant<out T : Any>(
    private val subjectProvider: () -> T
) : BaseAssertionPlant<T, IAssertionPlant<T>>(), ICollectingAssertionPlant<T> {

    override val self = this

    override val subject get() = subjectProvider()
}
