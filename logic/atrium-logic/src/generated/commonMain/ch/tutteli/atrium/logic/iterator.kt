// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

/**
 * Collection of assertion functions and builders which are applicable to subjects with an [Iterator] type.
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultIteratorAssertions

fun <E, T : Iterator<E>> AssertionContainer<T>.hasNext(): Assertion = impl.hasNext(this)
fun <E, T : Iterator<E>> AssertionContainer<T>.hasNotNext(): Assertion = impl.hasNotNext(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: IteratorAssertions
    get() = getImpl(IteratorAssertions::class) { DefaultIteratorAssertions() }
