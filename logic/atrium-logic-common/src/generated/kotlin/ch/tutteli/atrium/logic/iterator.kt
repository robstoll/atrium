//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

fun <E, T : Iterator<E>> AssertionContainer<T>.hasNext(): Assertion = _iteratorImpl.hasNext(this)
fun <E, T : Iterator<E>> AssertionContainer<T>.hasNotNext(): Assertion = _iteratorImpl.hasNotNext(this)
