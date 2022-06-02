@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

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
data class PathWithCreator<E> internal constructor(val path: String, val assertionCreator: Expect<E>.() -> Unit)
