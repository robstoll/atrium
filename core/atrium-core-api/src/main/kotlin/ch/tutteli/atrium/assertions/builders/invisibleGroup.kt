package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType

/**
 * Builder to create an [AssertionGroup] with an [InvisibleAssertionGroupType] -- use it only if you have several
 * independent [Assertion]s which should be evaluated together.
 *
 * Or in other words, it should still evaluate the remaining assertions if the first fails.
 * Use `AssertImpl.builder.list` ([AssertionBuilder.list]) or another builder which creates an [AssertionGroup] if
 * the assertions are not independent but belong together and can be grouped under a
 * [AssertionGroup.name] (making the assertions kind of sub-assertions of the [AssertionGroup.name]).
 * It is very likely that you do not need this kind of [AssertionGroup] and another type does a better job.
 */
@Suppress("unused")
val AssertionBuilder.invisibleGroup: AssertionsOption<InvisibleAssertionGroupType, BasicAssertionGroupFinalStep>
    get() = AssertionsOption.withDefaultFinalStepAndEmptyDescriptionAndRepresentation(InvisibleAssertionGroupType)
