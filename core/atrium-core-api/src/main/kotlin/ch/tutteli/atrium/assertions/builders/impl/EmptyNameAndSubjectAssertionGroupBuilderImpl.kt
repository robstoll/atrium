package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndSubjectAssertionGroup
import ch.tutteli.atrium.assertions.builders.EmptyNameAndSubjectAssertionGroupBuilder

internal class EmptyNameAndSubjectAssertionGroupBuilderImpl<out T: AssertionGroupType>(
    override val groupType: T
) : EmptyNameAndSubjectAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = EmptyNameAndSubjectAssertionGroup(groupType, assertions)
}
