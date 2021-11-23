package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*

@ExperimentalComponentFactoryContainer
@ExperimentalNewExpectTypes
internal class DelegatingExpectImpl<T>(private val container: AssertionContainer<*>, maybeSubject: Option<T>) :
    BaseExpectImpl<T>(maybeSubject), DelegatingExpect<T> {

    override val components: ComponentFactoryContainer
        get() = container.components


    override fun append(assertion: Assertion): Expect<T> =
        apply { container.append(assertion) }
}
