package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

interface IteratorAssertions {
    fun <E, T : Iterator<E>> hasNext(container: AssertionContainer<T>): Assertion
    fun <E, T : Iterator<E>> hasNotNext(container: AssertionContainer<T>): Assertion
}
