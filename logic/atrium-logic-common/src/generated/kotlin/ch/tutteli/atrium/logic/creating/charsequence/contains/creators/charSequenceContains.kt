// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroupimport ch.tutteli.atrium.core.ExperimentalNewExpectTypesimport ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContainsimport ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl.DefaultCharSequenceContainsAssertionsimport ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviourimport ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviourimport ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar


fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, NoOpSearchBehaviour>.values(expected: List<CharSequenceOrNumberOrChar>): AssertionGroup = impl.values(this, expected)

fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, IgnoringCaseSearchBehaviour>.valuesIgnoringCase(expected: List<CharSequenceOrNumberOrChar>): AssertionGroup = impl.valuesIgnoringCase(this, expected)

fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, NoOpSearchBehaviour>.regex(expected: List<Regex>): AssertionGroup = impl.regex(this, expected)

fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, IgnoringCaseSearchBehaviour>.regexIgnoringCase(expected: List<String>): AssertionGroup = impl.regexIgnoringCase(this, expected)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerStepLogic<T, S>.impl: CharSequenceContainsAssertions
    get() = entryPointStepLogic.container.getImpl(CharSequenceContainsAssertions::class) { DefaultCharSequenceContainsAssertions() }
