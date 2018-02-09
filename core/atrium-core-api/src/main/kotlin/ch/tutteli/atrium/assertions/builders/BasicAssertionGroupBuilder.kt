package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BasicAssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create an [AssertionGroup] with the given [groupType].
 */
class BasicAssertionGroupBuilder internal constructor(private val groupType: AssertionGroupType) {
    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [name] as [AssertionGroup.name], [subject] as [AssertionGroup.subject]
     * and [assertion] as single [AssertionGroup.assertions].
     */
    fun create(name: Translatable, subject: Any, assertion: Assertion)
        = create(name, subject, listOf(assertion))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [name] as [AssertionGroup.name], [subject] as [AssertionGroup.subject]
     * and [assertions] as [AssertionGroup.assertions].
     */
    fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
        = BasicAssertionGroup(groupType, name, subject, assertions)
}
