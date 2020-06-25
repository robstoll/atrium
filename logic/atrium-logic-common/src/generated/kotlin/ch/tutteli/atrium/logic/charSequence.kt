//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

    //TODO add with 0.14.0
//fun <T : CharSequence> AssertionContainer<T>.containsBuilder(): CharSequenceContains.Builder<T, NoOpSearchBehaviour> = _charSequenceImpl.containsBuilder(this)
//fun <T : CharSequence> AssertionContainer<T>.containsNotBuilder(): CharSequenceContains.Builder<T, NotSearchBehaviour> = _charSequenceImpl.containsNotBuilder(this)

fun <T : CharSequence> AssertionContainer<T>.startsWith(expected: CharSequence): Assertion = _charSequenceImpl.startsWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.startsNotWith(expected: CharSequence): Assertion = _charSequenceImpl.startsNotWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.endsWith(expected: CharSequence): Assertion = _charSequenceImpl.endsWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.endsNotWith(expected: CharSequence): Assertion = _charSequenceImpl.endsNotWith(this, expected)
fun <T : CharSequence> AssertionContainer<T>.isEmpty(): Assertion = _charSequenceImpl.isEmpty(this)
fun <T : CharSequence> AssertionContainer<T>.isNotEmpty(): Assertion = _charSequenceImpl.isNotEmpty(this)
fun <T : CharSequence> AssertionContainer<T>.isNotBlank(): Assertion = _charSequenceImpl.isNotBlank(this)

fun <T : CharSequence> AssertionContainer<T>.matches(expected: Regex): Assertion = _charSequenceImpl.matches(this, expected)
fun <T : CharSequence> AssertionContainer<T>.mismatches(expected: Regex): Assertion = _charSequenceImpl.mismatches(this, expected)
