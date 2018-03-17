package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndSubjectAssertionGroup

/**
 * Builder to create an [AssertionGroup] with an empty [AssertionGroup.name], an empty [AssertionGroup.subject] and
 * with the given [groupType].
 */
interface EmptyNameAndSubjectAssertionGroupBuilder<out T: AssertionGroupType> : AssertionGroupBuilder<T>

internal class EmptyNameAndSubjectAssertionGroupBuilderImpl<out T: AssertionGroupType>(
    override val groupType: T
) : EmptyNameAndSubjectAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = EmptyNameAndSubjectAssertionGroup(groupType, assertions)
}
