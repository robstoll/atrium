package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class AssertionsOptionImpl<out T : AssertionGroupType, R>(
    override val groupType: T,
    override val description: Translatable,
    override val representation: Any,
    private val factory: (T, Translatable, Any, List<Assertion>) -> R
) : AssertionsOption<T, R> {

    override fun withAssertions(assertions: List<Assertion>): R =
        factory(groupType, description, representation, assertions)
}
