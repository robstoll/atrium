@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s [Collection.size] is [size].
 *
 * Shortcut for `size.toBe(expectedSize)` depends on the underlying implementation though.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().hasSize(size).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.hasSize"
    )
)
fun <T : Collection<*>> Assert<T>.hasSize(size: Int)
    = addAssertion(AssertImpl.collection.hasSize(this, size))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().isEmpty().asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.isEmpty"
    )
)
fun <T : Collection<*>> Assert<T>.isEmpty()
    = addAssertion(AssertImpl.collection.isEmpty(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().isNotEmpty().asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.isNotEmpty"
    )
)
fun <T : Collection<*>> Assert<T>.isNotEmpty()
    = addAssertion(AssertImpl.collection.isNotEmpty(this))

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property
 * [size][Collection.size] so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().size",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.size"
    )
)
val Assert<Collection<*>>.size get(): Assert<Int> = property(Collection<*>::size)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s property [size][Collection.size]
 * holds all assertions the given [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().size.asAssert(assertionCreator)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.size"
    )
)
fun <E, T: Collection<E>> Assert<T>.size(assertionCreator: Assert<Int>.() -> Unit): Assert<T>
    = addAssertion(AssertImpl.collection.size(this, assertionCreator))
