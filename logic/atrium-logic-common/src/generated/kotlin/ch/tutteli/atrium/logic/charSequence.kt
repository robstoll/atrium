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


    /**
     * Starts the building process of a sophisticated `contains` assertions.
     */
fun <T : CharSequence> AssertionContainer<T>.containsBuilder(): CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour> = _charSequenceImpl.containsBuilder(this)

    /**
     * Starts the building process of a sophisticated `contains` assertions and already applies a [NotCheckerStep] with
     * a [NotSearchBehaviour].
     */
fun <T : CharSequence> AssertionContainer<T>.containsNotBuilder(): NotCheckerStep<T, NotSearchBehaviour> = _charSequenceImpl.containsNotBuilder(this)

fun <T : CharSequence> AssertionContainer<T>.startsWith(expected: CharSequence): Assertion = _charSequenceImpl.startsWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.startsNotWith(expected: CharSequence): Assertion = _charSequenceImpl.startsNotWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.endsWith(expected: CharSequence): Assertion = _charSequenceImpl.endsWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.endsNotWith(expected: CharSequence): Assertion = _charSequenceImpl.endsNotWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.isEmpty(): Assertion = _charSequenceImpl.isEmpty(this)
fun <T : CharSequence> AssertionContainer<T>.isNotEmpty(): Assertion = _charSequenceImpl.isNotEmpty(this)
fun <T : CharSequence> AssertionContainer<T>.isNotBlank(): Assertion = _charSequenceImpl.isNotBlank(this)

fun <T : CharSequence> AssertionContainer<T>.matches(expected: Regex): Assertion = _charSequenceImpl.matches(this, expected)
fun <T : CharSequence> AssertionContainer<T>.mismatches(expected: Regex): Assertion = _charSequenceImpl.mismatches(this, expected)
