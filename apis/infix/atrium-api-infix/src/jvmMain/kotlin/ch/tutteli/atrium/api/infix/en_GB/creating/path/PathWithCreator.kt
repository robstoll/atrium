package ch.tutteli.atrium.api.infix.en_GB.creating.path

import ch.tutteli.atrium.creating.Expect

/**
 *  Parameter object which combines a [path] (as [String]) with an [assertionCreator] which defines assertions for
 *  a resulting feature of type [E].
 *
 *  Use the function `path(String) { ... }` to create this representation.
 *
 *  @since 0.12.0
 */
//TODO 2.0.0 remove data?
data class PathWithCreator<E> constructor(val path: String, val assertionCreator: Expect<E>.() -> Unit)
