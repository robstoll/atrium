// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the given [index] is within the bounds of [Assert.subject][SubjectProvider.subject],
 * creates a feature assertion plant for the corresponding element and returns the newly created plant.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
@Suppress("DEPRECATION")
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().get(index).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.get"
    )
)
infix fun <E: Any, T: List<E>> Assert<T>.get(index: Int)
    = AssertImpl.list.get(this, index)

/**
 * Prepares the assertion about the return value of calling [get][List.get] with the given [index].
 *
 * @return A fluent builder to finish the assertion.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().get(ch.tutteli.atrium.api.infix.en_GB.index(index.index) { \n/* needs further adjustments, move the lambda passed to assertIt to here and remove assertIt */ \n }).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.get",
        "ch.tutteli.atrium.api.infix.en_GB.index"
    )
)
infix fun <E : Any, T: List<E>> Assert<T>.get(index: Index): ListGetOption<E, T>
    = ListGetOption.create(this, index.index)


/**
 * Makes the assertion that the given [index] is within the bounds of [Assert.subject][SubjectProvider.subject],
 * creates a feature assertion plant for the corresponding nullable element and returns the newly created plant.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().get(index).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.get"
    )
)
@Suppress("DEPRECATION")
infix fun <E, T: List<E>> Assert<T>.get(index: Int)
    = AssertImpl.list.getNullable(this, index)

/**
 * Prepares the assertion about the nullable return value of calling [get][List.get] with the given [index].
 *
 * Notice, that the corresponding element of the given [index] can be `null` even if the index is within bounds
 * as the [List] has a nullable element type.
 *
 * @return A fluent builder to finish the assertion.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().get(ch.tutteli.atrium.api.infix.en_GB.index(index.index) { \n/* needs further adjustments, move the lambda passed to assertIt to here and remove assertIt */ \n }).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.get",
        "ch.tutteli.atrium.api.infix.en_GB.index"
    )
)
infix fun <E, T: List<E>> Assert<T>.get(index: Index): ListGetNullableOption<E, T>
    = ListGetNullableOption.create(this, index.index)

