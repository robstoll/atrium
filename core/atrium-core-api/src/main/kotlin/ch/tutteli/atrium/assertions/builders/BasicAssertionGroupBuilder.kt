package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BasicAssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create an [AssertionGroup] with the given [groupType], [name] and [subject].
 */
interface BasicAssertionGroupBuilder<out T : AssertionGroupType> : AssertionGroupBuilder<T> {

    /**
     * The [AssertionGroup.name].
     */
    val name: Translatable

    /**
     * The [AssertionGroup.subject].
     */
    val subject: Any
}

internal class BasicAssertionGroupBuilderImpl<out T : AssertionGroupType>(
    override val groupType: T,
    override val name: Translatable,
    override val subject: Any
) : BasicAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = BasicAssertionGroup(groupType, name, subject, assertions)
}
