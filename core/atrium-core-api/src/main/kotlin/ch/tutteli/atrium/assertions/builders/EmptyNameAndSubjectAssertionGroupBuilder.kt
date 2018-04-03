package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType

/**
 * Builder to create an [AssertionGroup] with an empty [AssertionGroup.name], an empty [AssertionGroup.subject] and
 * with the given [groupType].
 */
interface EmptyNameAndSubjectAssertionGroupBuilder<out T: AssertionGroupType> : AssertionGroupBuilder<T>
