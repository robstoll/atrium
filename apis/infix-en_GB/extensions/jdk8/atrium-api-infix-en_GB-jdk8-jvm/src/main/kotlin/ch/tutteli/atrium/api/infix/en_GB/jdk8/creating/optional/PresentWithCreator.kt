@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
package ch.tutteli.atrium.api.infix.en_GB.jdk8.creating.optional

import ch.tutteli.atrium.creating.Expect


/**
 *  Parameter object which uses [assertionCreator] which defines assertions for
 *  a resulting feature of type [E].
 *
 *  Use the function `present(Expect) { ... }` to create this representation.
 *
 *  @since 0.11.0
 */
data class PresentWithCreator<E>(val assertionCreator: Expect<E>.() -> Unit)
