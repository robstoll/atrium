package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup
import ch.tutteli.atrium.assertions.builders.EmptyNameAndRepresentationAssertionGroupBuilder

internal class EmptyNameAndRepresentationAssertionGroupBuilderImpl<out T: AssertionGroupType>(
    override val groupType: T
) : EmptyNameAndRepresentationAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = EmptyNameAndRepresentationAssertionGroup(groupType, assertions)
}
