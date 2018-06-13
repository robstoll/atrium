package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CheckingAssertionPlant

class CheckingAssertionPlantImpl<out T : Any>(
    subjectProvider: () -> T
) : MutableListBasedAssertionPlant<T, AssertionPlant<T>>(),
    CheckingAssertionPlant<T> {

    override val self = this
    override val subject by lazy { subjectProvider() }

    override fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): CheckingAssertionPlant<T> {
        this.assertionCreator()
        return this
    }

    override fun allAssertionsHold(): Boolean {
        val assertions = getAssertions()
        check(!assertions.isEmpty()) { "You need to create assertions first before checking whether they all hold." }

        try {
            return assertions.all { it.holds() }
        } finally {
            clearAssertions()
        }
    }

}

