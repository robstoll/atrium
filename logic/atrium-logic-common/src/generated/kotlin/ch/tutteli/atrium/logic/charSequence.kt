//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultCharSequenceAssertions


    /**
     * Starts the building process of a sophisticated `contains` assertions.
     */
fun <T : CharSequence> AssertionContainer<T>.containsBuilder(): CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour> = impl.containsBuilder(this)

    /**
     * Starts the building process of a sophisticated `contains` assertions and already applies a [NotCheckerStep] with
     * a [NotSearchBehaviour].
     */
fun <T : CharSequence> AssertionContainer<T>.containsNotBuilder(): NotCheckerStep<T, NotSearchBehaviour> = impl.containsNotBuilder(this)

fun <T : CharSequence> AssertionContainer<T>.startsWith(expected: CharSequence): Assertion = impl.startsWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.startsNotWith(expected: CharSequence): Assertion = impl.startsNotWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.endsWith(expected: CharSequence): Assertion = impl.endsWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.endsNotWith(expected: CharSequence): Assertion = impl.endsNotWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.isEmpty(): Assertion = impl.isEmpty(this)
fun <T : CharSequence> AssertionContainer<T>.isNotEmpty(): Assertion = impl.isNotEmpty(this)
fun <T : CharSequence> AssertionContainer<T>.isNotBlank(): Assertion = impl.isNotBlank(this)

fun <T : CharSequence> AssertionContainer<T>.matches(expected: Regex): Assertion = impl.matches(this, expected)
fun <T : CharSequence> AssertionContainer<T>.mismatches(expected: Regex): Assertion = impl.mismatches(this, expected)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: CharSequenceAssertions
    get() = getImpl(CharSequenceAssertions::class) { DefaultCharSequenceAssertions() }
