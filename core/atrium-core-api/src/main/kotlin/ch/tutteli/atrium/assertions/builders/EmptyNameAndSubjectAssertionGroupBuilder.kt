package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndSubjectAssertionGroup

/**
 * Builder to create an [AssertionGroup] with an empty [AssertionGroup.name], an empty [AssertionGroup.subject] and
 * with the given [groupType].
 */
class EmptyNameAndSubjectAssertionGroupBuilder internal constructor(private val groupType: AssertionGroupType) {
    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion] as single [AssertionGroup.assertions].
     */
    fun create(assertion: Assertion): AssertionGroup
        = create(listOf(assertion))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertions] as [AssertionGroup.assertions].
     */
    fun create(assertions: List<Assertion>): AssertionGroup
        = EmptyNameAndSubjectAssertionGroup(groupType, assertions)
}
