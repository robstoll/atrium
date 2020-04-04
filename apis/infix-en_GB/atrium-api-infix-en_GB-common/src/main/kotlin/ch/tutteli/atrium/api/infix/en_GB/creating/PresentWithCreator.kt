package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.creating.Expect

/**
 *  Parameter object which represents a present value (e.g. to represent a present `Optional`) with an element type [E]
 *  combined with an [assertionCreator] which defines assertions for the element.
 *
 *  Use the function `present { ... }` to create this representation.
 *
 *  @since 0.11.0
 */
data class PresentWithCreator<E>(val assertionCreator: Expect<E>.() -> Unit)
