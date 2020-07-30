//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultIteratorAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: IteratorAssertions
    get() = getImpl(IteratorAssertions::class) { DefaultIteratorAssertions() }

fun <E, T : Iterator<E>> AssertionContainer<T>.hasNext(): Assertion = impl.hasNext(this)
fun <E, T : Iterator<E>> AssertionContainer<T>.hasNotNext(): Assertion = impl.hasNotNext(this)
