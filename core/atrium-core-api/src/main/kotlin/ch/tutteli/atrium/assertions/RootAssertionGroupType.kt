package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant

/**
 * The [AssertionGroupType] for [AssertionGroup]s which are the root of all assertions.
 *
 * Or in other words, it is the [AssertionGroupType] for those [AssertionGroup]s which are created when calling
 * [AssertionPlant.checkAssertions] and its [AssertionGroup.name] corresponds to the assertion verb used
 * (e.g, `assert`).
 */
object RootAssertionGroupType : AssertionGroupType
