package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
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
