@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [first][Pair.first] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().first",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.first"
    )
)
val <K : Any> Assert<Pair<K, *>>.first get() : Assert<K> = property(Pair<K, *>::first)

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [first][Pair.first] (which could be `null`)
 * so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
//TODO deprecate as soon as https://youtrack.jetbrains.com/issue/KT-33398 is fixed
//@Deprecated(
//    "Switch from Assert to Expect; will be removed with 1.0.0",
//    ReplaceWith(
//        "this.asExpect().first",
//        "ch.tutteli.atrium.domain.builders.migration.asExpect",
//        "ch.tutteli.atrium.api.fluent.en_GB.first"
//    )
//)
val <K> Assert<Pair<K, *>>.first get() : AssertionPlantNullable<K> = property(Pair<K, *>::first)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s property [first][Pair.first] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().apply { first.asAssert(assertionCreator) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.first"
    )
)
fun <K : Any, V> Assert<Pair<K, V>>.first(assertionCreator: Assert<K>.() -> Unit)
    = addAssertion(AssertImpl.pair.first(this, assertionCreator))

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [second][Pair.second] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().second",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.second"
    )
)
val <V : Any> Assert<Pair<*, V>>.second get() : Assert<V> = property(Pair<*, V>::second)

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [second][Pair.second]
 * (which could be `null`) so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
//TODO deprecate as soon as https://youtrack.jetbrains.com/issue/KT-33398 is fixed
//@Deprecated(
//    "Switch from Assert to Expect; will be removed with 1.0.0",
//    ReplaceWith(
//        "this.asExpect().second",
//        "ch.tutteli.atrium.domain.builders.migration.asExpect",
//        "ch.tutteli.atrium.api.fluent.en_GB.second"
//    )
//)
val <V> Assert<Pair<*, V>>.second get() : AssertionPlantNullable<V> = property(Pair<*, V>::second)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s property [second][Pair.second] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().apply { second.asAssert(assertionCreator) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.second"
    )
)
fun <K, V: Any> Assert<Pair<K, V>>.second(assertionCreator: Assert<V>.() -> Unit)
    = addAssertion(AssertImpl.pair.second(this, assertionCreator))
