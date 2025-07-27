package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.creating.Expect


/**
 *  Parameter object which combines an [index] of type [Int] with an [assertionCreator] which defines assertions for
 *  a resulting feature of type [E].
 *
 *  Use the function `index(Int) { ... }` to create this representation.
 *
 *  @since 0.12.0
 */
//TODO 2.0.0 remove data?
data class IndexWithCreator<E> internal constructor(val index: Int, val assertionCreator: Expect<E>.() -> Unit)
