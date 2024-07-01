//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.Expect

/**
 * The [AssertionGroupType] for [AssertionGroup]s which are the root of all assertions.
 *
 * Or in other words, it is the [AssertionGroupType] for those [AssertionGroup]s which are created when
 * [Expect] is checking the added [Assertion]s.
 * Its [AssertionGroup.description] corresponds to the assertion verb used (e.g, `assert`).
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
object RootAssertionGroupType : AssertionGroupType
