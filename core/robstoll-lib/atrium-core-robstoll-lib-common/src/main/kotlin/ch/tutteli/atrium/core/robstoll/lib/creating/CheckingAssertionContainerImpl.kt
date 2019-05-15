package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.creating.CheckingAssertionContainer
import ch.tutteli.atrium.creating.Expect

class CheckingAssertionContainerImpl<T>(
    subjectProvider: () -> T
) : MutableListBasedAssertionContainer<T>(subjectProvider), CheckingAssertionContainer<T> {

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CheckingAssertionContainer<T> {
        this.assertionCreator()
        return this
    }

    override fun allAssertionsHold(): Boolean {
        val assertions = getCopyOfAssertions()
        check(assertions.isNotEmpty()) { "You need to create assertions first before checking whether they all hold." }

        try {
            return assertions.all { it.holds() }
        } finally {
            clearAssertions()
        }
    }
}
