package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s which are the root of all assertions.
 *
 * Or in other words, it is the [IAssertionGroupType] for those [IAssertionGroup]s which are created when calling
 * [IAssertionPlant.checkAssertions] and its [IAssertionGroup.name] corresponds to the assertion verb used
 * (e.g, `assert`).
 */
object RootAssertionGroupType : IAssertionGroupType
