@file:Suppress("DEPRECATION" /* TODO remove suppress with 1.0.0 */)
package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries

/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * In case `null` is used for the identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 */
class Entry<T : Any>(
    val assertionCreatorOrNull: (Expect<T>.() -> Unit)?
) : GroupWithoutNullableEntries<(Expect<T>.() -> Unit)?>,
    GroupWithNullableEntries<(Expect<T>.() -> Unit)?> {
    override fun toList(): List<(Expect<T>.() -> Unit)?> = listOf(assertionCreatorOrNull)
}
