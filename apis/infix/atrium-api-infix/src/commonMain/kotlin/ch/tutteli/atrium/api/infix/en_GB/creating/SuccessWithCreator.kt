package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.creating.Expect

/**
 * Parameter object that takes [assertionCreator] which defines assertions for a resulting feature of type [E].
 *
 * Use the function `success { ... }` to create a [SuccessWithCreator].
 *
 * @since 0.12.0
 */
//TODO 2.0.0 remove data?
data class SuccessWithCreator<E> internal constructor(val assertionCreator: Expect<E>.() -> Unit);
