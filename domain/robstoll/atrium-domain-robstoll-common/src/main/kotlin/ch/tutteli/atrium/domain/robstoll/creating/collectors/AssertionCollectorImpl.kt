package ch.tutteli.atrium.domain.robstoll.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.CollectingAssertionContainer
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.domain.robstoll.lib.creating.collectors._collect

class AssertionCollectorImpl : AssertionCollector, AssertionCollectorDeprecatedImpl() {

    override fun <T> collect(
        maybeSubject: Option<T>,
        assertionCreator: CollectingAssertionContainer<T>.() -> Unit
    ): AssertionGroup = _collect(maybeSubject, assertionCreator)
}
