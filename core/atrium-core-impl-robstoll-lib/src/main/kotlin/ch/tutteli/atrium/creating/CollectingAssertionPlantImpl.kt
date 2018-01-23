package ch.tutteli.atrium.creating

class CollectingAssertionPlantImpl<out T : Any>(
    private val subjectProvider: () -> T
) : MutableListBasedAssertionPlant<T, AssertionPlant<T>>(), CollectingAssertionPlant<T> {
    override val self = this

    override fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): CollectingAssertionPlant<T> {
        this.assertionCreator()
        return this
    }

    override val subject get() = subjectProvider()
}
