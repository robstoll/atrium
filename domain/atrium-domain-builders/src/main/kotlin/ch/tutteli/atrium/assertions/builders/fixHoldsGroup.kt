package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions] but specified by the argument `holds` passed to its constructor).
 */
@Suppress("unused")
val AssertionBuilder.fixHoldsGroup get() = FixHoldsAssertionGroupBuilder()

/**
*  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
* [AssertionGroup.assertions] but specified by the argument `holds` passed to `create`).
*/
class FixHoldsAssertionGroupBuilder internal constructor() {
    /**
     * Creates an [AssertionGroup] whose [AssertionGroup.holds] returns `false`, its [AssertionGroup.type] is
     * [DefaultListAssertionGroupType] and it uses the given [name], [subject] and [assertion] as single [Assertion]
     * in [AssertionGroup.assertions].
     */
    fun createFailingWithListType(name: Translatable, subject: Any, assertion: Assertion): AssertionGroup
        = createFailingWithListType(name, subject, listOf(assertion))

    /**
     * Creates an [AssertionGroup] whose [AssertionGroup.holds] returns `false`, its [AssertionGroup.type] is
     * [DefaultListAssertionGroupType] and it uses the given [name], [subject] and [assertions].
     */
    fun createFailingWithListType(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
        = create(name, subject, false, DefaultListAssertionGroupType, assertions)

    /**
     * Creates an [AssertionGroup] whose [AssertionGroup.holds] returns the given [holds] and
     * uses the given [name], [subject], [type] and [assertion] as single [Assertion] in [AssertionGroup.assertions].
     */
    fun create(name: Translatable, subject: Any, holds: Boolean, type: AssertionGroupType, assertion: Assertion): AssertionGroup
        = create(name, subject, holds, type, listOf(assertion))

    /**
     * Creates an [AssertionGroup] whose [AssertionGroup.holds] returns the given [holds] and
     * uses the given [name], [subject], [type] and [assertions] for the created group.
     */
    fun create(name: Translatable, subject: Any, holds: Boolean, type: AssertionGroupType, assertions: List<Assertion>): AssertionGroup
        = FixHoldsAssertionGroup(type, name, subject, assertions, holds)
}

