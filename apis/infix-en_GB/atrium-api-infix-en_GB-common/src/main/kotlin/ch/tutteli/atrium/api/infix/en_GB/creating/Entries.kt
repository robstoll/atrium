@file:Suppress("DEPRECATION" /* TODO remove suppress with 1.0.0 */)

package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [Group] of identification lambdas.
 *
 * It is also used to express `(Expect<T>.() -> Unit)?, vararg (Expect<T>.() -> Unit)?` at other places the infix-api.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * Use the function `entries({ ... }, ...)` to create this representation.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class Entries<T> internal constructor(
    val assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    val otherAssertionCreatorsOrNulls: Array<out (Expect<T>.() -> Unit)?>
) : GroupWithoutNullableEntries<(Expect<T>.() -> Unit)?>,
    GroupWithNullableEntries<(Expect<T>.() -> Unit)?>,
    VarArgHelper<(Expect<T>.() -> Unit)?> {
    override val expected get() = assertionCreatorOrNull
    override val otherExpected get() = otherAssertionCreatorsOrNulls

    override fun toList(): List<(Expect<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}
