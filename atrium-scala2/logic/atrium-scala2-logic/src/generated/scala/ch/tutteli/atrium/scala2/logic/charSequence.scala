//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.CharSequenceKt
import ch.tutteli.atrium.scala2.logic._

class CharSequenceLogic[(container: AssertionContainer[[]) {

    //TODO add with 0.14.0
//    def containsBuilder(): CharSequenceContains.Builder[T, NoOpSearchBehaviour] = CharSequenceKt.containsBuilder(container)
//    def containsNotBuilder(): CharSequenceContains.Builder[T, NotSearchBehaviour] = CharSequenceKt.containsNotBuilder(container)

    def startsWith(expected: CharSequence): Assertion = CharSequenceKt.startsWith(container, expected)
    def startsNotWith(expected: CharSequence): Assertion = CharSequenceKt.startsNotWith(container, expected)
    def endsWith(expected: CharSequence): Assertion = CharSequenceKt.endsWith(container, expected)
    def endsNotWith(expected: CharSequence): Assertion = CharSequenceKt.endsNotWith(container, expected)
    def isEmpty(): Assertion = CharSequenceKt.isEmpty(container)
    def isNotEmpty(): Assertion = CharSequenceKt.isNotEmpty(container)
    def isNotBlank(): Assertion = CharSequenceKt.isNotBlank(container)

    def matches(expected: Regex): Assertion = CharSequenceKt.matches(container, expected)
    def mismatches(expected: Regex): Assertion = CharSequenceKt.mismatches(container, expected)
}
