package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndSubjectAssertionGroup

/**
 * Builder to create an [AssertionGroup] with an empty [AssertionGroup.name], an empty [AssertionGroup.subject] and
 * with the given [groupType].
 */
interface EmptyNameAndSubjectAssertionGroupBuilder {

    /**
     * The [AssertionGroupType] which shall be used for the [AssertionGroup].
     */
    val groupType: AssertionGroupType

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion] as single [AssertionGroup.assertions].
     */
    fun create(assertion: Assertion): AssertionGroup

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertions] as [AssertionGroup.assertions].
     */
    fun create(assertions: List<Assertion>): AssertionGroup
}


/**
 * Builder to create an [AssertionGroup] with an empty [AssertionGroup.name], an empty [AssertionGroup.subject] and
 * with the given [groupType].
 */
internal class EmptyNameAndSubjectAssertionGroupBuilderImpl internal constructor(
    override val groupType: AssertionGroupType
) : EmptyNameAndSubjectAssertionGroupBuilder {

    override fun create(assertion: Assertion): AssertionGroup
        = create(listOf(assertion))

    override fun create(assertions: List<Assertion>): AssertionGroup
        = EmptyNameAndSubjectAssertionGroup(groupType, assertions)
}
