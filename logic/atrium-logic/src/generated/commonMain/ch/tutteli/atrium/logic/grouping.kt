// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
@file:Suppress("ObjectPropertyName", "FunctionName")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultGroupingAssertions


fun <T> AssertionContainer<T>.grouping(description: String, representationProvider: () -> Any?, groupingActions: ExpectGrouping.() -> Unit): Assertion =
    impl.grouping(this, description, representationProvider, groupingActions)

fun <T> AssertionContainer<T>.group(description: String, representationProvider: () -> Any?, assertionCreator: Expect<T>.() -> Unit): Assertion =
    impl.group(this, description, representationProvider, assertionCreator)


@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: GroupingAssertions
    get() = getImpl(GroupingAssertions::class) { DefaultGroupingAssertions() }
