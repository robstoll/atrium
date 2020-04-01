@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
package ch.tutteli.atrium.api.infix.en_GB.jdk8.creating.optional

import ch.tutteli.atrium.creating.Expect
import java.util.Optional

/**
 *  Parameter object which represents a present [Optional] with an element type [E] combined
 *  with an [assertionCreator] which defines assertions for the element.
 *
 *  Use the function `present { ... }` to create this representation.
 *
 *  @since 0.11.0
 */
data class PresentWithCreator<E>(val assertionCreator: Expect<E>.() -> Unit)
