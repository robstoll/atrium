package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.CollectingAssertionContainer
import ch.tutteli.atrium.creating.Expect

class CollectingAssertionContainerImpl<T>(
    subjectProvider: () -> T
) : MutableListBasedAssertionContainer<T>(subjectProvider), CollectingAssertionContainer<T> {

    override fun getAssertions(): List<Assertion> = getCopyOfAssertions()

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CollectingAssertionContainer<T> {
        this.assertionCreator()
        return this
    }
}
