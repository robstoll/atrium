package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.ExpectGroupingInternal
import ch.tutteli.atrium.creating.RootExpect

class DefaultExpectGroupingImpl(
    private val rootExpectCreator: () -> RootExpect<*>,
    private val collectingExpect: CollectingExpect<Nothing>
) : CollectingExpect<Nothing> by collectingExpect,
    ExpectGroupingInternal {

    override fun evaluate() {
        val assertions = collectingExpect.getAssertions()
        (rootExpectCreator() as AssertionContainer<*>).appendAsGroup(assertions)
    }
}
