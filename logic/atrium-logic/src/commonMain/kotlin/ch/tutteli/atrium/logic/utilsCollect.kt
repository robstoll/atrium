//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic.creating.transformers.TransformationExecutionStep


/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * It uses the [AssertionContainer.maybeSubject] as subject of the given [assertionCreator] and
 * delegates to [collectBasedOnSubject].
 *
 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions.
 */
@Deprecated(
    "Use the import from core and pass an ExpectationCreatorWithUsageHints, will be removed with 2.0.0 at the latest",
    ReplaceWith(
        "this.collect(ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the generic */ ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED))).first",
        "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
        "ch.tutteli.atrium.reporting.reportables.ErrorMessages",
        "ch.tutteli.atrium.creating.collect"
    )
)
inline fun <T> AssertionContainer<T>.collect(noinline assertionCreator: Expect<T>.() -> Unit): Assertion =
    collectBasedOnSubject(maybeSubject, assertionCreator)


/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * It uses the [AssertionContainer.maybeSubject] as subject of the given [assertionCreator] and
 * delegates to [collectForCompositionBasedOnSubject].
 *
 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 */
@Deprecated(
    "Use the import from core and pass an ExpectationCreatorWithUsageHints, will be removed with 2.0.0 at the latest",
    ReplaceWith(
        "this.collectForComposition(ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the defaultHintsAtLeastOneExpectationDefined */))).first",
        "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
        "ch.tutteli.atrium.reporting.reportables.ErrorMessages",
        "ch.tutteli.atrium.creating.collectForComposition"
    )
)
inline fun <T> AssertionContainer<T>.collectForComposition(
    noinline assertionCreator: Expect<T>.() -> Unit
): List<Assertion> = collectForCompositionBasedOnSubject(maybeSubject, assertionCreator)

/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
 *   [None] in case a previous subject transformation was not successful -
 *   this will be used as subject for the given [assertionCreator].
 * @param assertionCreator A lambda which defines the expectations for the given [maybeSubject].
 *
 * @return The collected assertions.
 */
@Deprecated(
    "Use collectBasedOnGivenSubject from core and pass an ExpectationCreatorWithUsageHints, will be removed with 2.0.0 at the latest",
    ReplaceWith(
        "this.collectBasedOnGivenSubject(maybeSubject, ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the defaultHintsAtLeastOneExpectationDefined */))).first",
        "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
        "ch.tutteli.atrium.reporting.reportables.ErrorMessages",
        "ch.tutteli.atrium.creating.collectBasedOnGivenSubject"
    )
)
inline fun <T> AssertionContainer<*>.collectBasedOnSubject(
    maybeSubject: Option<T>,
    noinline assertionCreator: Expect<T>.() -> Unit
): Assertion {
    val collectedAssertions = collectForCompositionBasedOnSubject(maybeSubject, assertionCreator)
    return when (collectedAssertions.size) {
        0 -> throw IllegalStateException("no assertion was collected for $maybeSubject")
        1 -> collectedAssertions[0]
        else ->
            assertionBuilder.invisibleGroup.withAssertions(collectedAssertions).build()
    }
}

/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.

 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 */
@Deprecated(
    "Use collectForCompositionBasedOnGivenSubject from core and pass an ExpectationCreatorWithUsageHints, will be removed with 2.0.0 at the latest",
    ReplaceWith(
        "this.collectForCompositionBasedOnGivenSubject(maybeSubject, ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the defaultHintsAtLeastOneExpectationDefined */))).first",
        "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
        "ch.tutteli.atrium.reporting.reportables.ErrorMessages",
        "ch.tutteli.atrium.creating.collectForCompositionBasedOnGivenSubject"
    )
)
@OptIn(ExperimentalComponentFactoryContainer::class)
inline fun <T> AssertionContainer<*>.collectForCompositionBasedOnSubject(
    maybeSubject: Option<T>,
    noinline assertionCreator: Expect<T>.() -> Unit
): List<Assertion> = CollectingExpect(maybeSubject, components)
    .appendAsGroup(assertionCreator)
    .getAssertions()


@Deprecated(
    "Use collectAndCoreAppend from core and pass an ExpectationCreatorWithUsageHints, will be removed with 2.0.0 at the latest",
    ReplaceWith(
        "this.collectAndCoreAppend(ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the defaultHintsAtLeastOneExpectationDefined */))).first",
        "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
        "ch.tutteli.atrium.reporting.reportables.ErrorMessages",
        "ch.tutteli.atrium.creating.collectAndCoreAppend"
    )
)
/**
 * Finishes the transformation process by appending the [Assertion]
 * which is returned when calling [TransformationExecutionStep.collectAndAppend] with [_logicAppend]
 * and the given [assertionCreator].
 *
 * See [collectBasedOnSubject] for more information.
 *
 * @return an [Expect] for the subject of this expectation.
 */
fun <T, R> TransformationExecutionStep<T, R, *>.collectAndLogicAppend(assertionCreator: AssertionContainer<R>.() -> Assertion): Expect<T> =
    collectAndAppend { _logicAppend(assertionCreator) }
