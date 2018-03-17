package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BasicAssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create an [AssertionGroup] with the given [groupType].
 */
interface BasicAssertionGroupBuilder {

    /**
     * The [AssertionGroupType] which shall be used for the [AssertionGroup].
     */
    val groupType: AssertionGroupType

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [name] as [AssertionGroup.name], [subject] as [AssertionGroup.subject]
     * and [assertion] as single [AssertionGroup.assertions].
     */
    fun create(name: Translatable, subject: Any, assertion: Assertion): AssertionGroup

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [name] as [AssertionGroup.name], [subject] as [AssertionGroup.subject]
     * and [assertions] as [AssertionGroup.assertions].
     */
    fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
}


/**
 * Builder to create an [AssertionGroup] with the given [groupType].
 */
internal class BasicAssertionGroupBuilderImpl internal constructor(
    override val groupType: AssertionGroupType
) : BasicAssertionGroupBuilder {

    override fun create(name: Translatable, subject: Any, assertion: Assertion)
        = create(name, subject, listOf(assertion))

    override fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
        = BasicAssertionGroup(groupType, name, subject, assertions)
}
