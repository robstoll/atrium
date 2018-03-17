package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create the root [AssertionGroup] -- do not use it in assertion functions; this group should only be
 * created by [AssertionChecker]s.
 *
 * @param name The [AssertionGroup.name].
 * @param subject The [AssertionGroup.subject] which is used to represent the group.
 */
@Suppress("unused")
fun AssertionBuilder.root(name: Translatable, subject: Any): BasicAssertionGroupBuilder<RootAssertionGroupType>
    = BasicAssertionGroupBuilderImpl(RootAssertionGroupType, name, subject)
