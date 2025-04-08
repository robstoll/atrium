// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating.proofs.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.charsequence.contains.creators.impl.DefaultCharSequenceToContainProofs


fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, NoOpSearchBehaviour>.values(expected: List<CharSequenceOrNumberOrChar>): AssertionGroup = impl.values(this, expected)

fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, IgnoringCaseSearchBehaviour>.valuesIgnoringCase(expected: List<CharSequenceOrNumberOrChar>): AssertionGroup = impl.valuesIgnoringCase(this, expected)

fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, NoOpSearchBehaviour>.regex(expected: List<Regex>): AssertionGroup = impl.regex(this, expected)

fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, IgnoringCaseSearchBehaviour>.regexIgnoringCase(expected: List<String>): AssertionGroup = impl.regexIgnoringCase(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <SubjectT : CharSequence, SearchBehaviourT : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerStepLogic<SubjectT, SearchBehaviourT>.impl: CharSequenceToContainProofs
    get() = entryPointStepCore.container.getImpl(CharSequenceToContainProofs::class) { DefaultCharSequenceToContainProofs() }
