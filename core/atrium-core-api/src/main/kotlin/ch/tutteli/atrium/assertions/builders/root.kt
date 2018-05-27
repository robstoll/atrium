package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker

/**
 * Builder to create the root [AssertionGroup] -- do not use it in assertion functions; this group should only be
 * created by [AssertionChecker]s.
 */
@Suppress("unused")
val AssertionBuilder.root: DefaultAssertionGroupBuilderOptions<RootAssertionGroupType>
    get() = DescriptionAndRepresentationOption.create(RootAssertionGroupType, AssertionsOption.asFactoryWithDefaultFinalStep())
