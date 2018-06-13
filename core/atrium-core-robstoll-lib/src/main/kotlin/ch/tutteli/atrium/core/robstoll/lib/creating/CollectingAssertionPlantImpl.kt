package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant

class CollectingAssertionPlantImpl<out T : Any>(
    subjectProvider: () -> T
) : MutableListBasedAssertionPlant<T, AssertionPlant<T>>(subjectProvider),
    CollectingAssertionPlant<T> {

    override val self = this

    override fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): CollectingAssertionPlant<T> {
        this.assertionCreator()
        return this
    }
}
