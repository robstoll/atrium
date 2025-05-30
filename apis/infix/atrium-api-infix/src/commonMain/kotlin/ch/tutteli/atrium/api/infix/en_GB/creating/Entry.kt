package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.logic.utils.Group

/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * In case `null` is used for the identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * Use the function `entry { ... }` to create this representation.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Proof]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 */
data class Entry<T : Any> internal constructor(
    val assertionCreatorOrNull: (Expect<T>.() -> Unit)?
) : Group<(Expect<T>.() -> Unit)?> {

    override fun toList(): List<(Expect<T>.() -> Unit)?> = listOf(assertionCreatorOrNull)
}
