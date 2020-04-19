package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.creating.Expect

/**
 *  Parameter object which combines an [key] of type [K] with an [assertionCreator] which defines assertions for
 *  a resulting feature of type [V].
 *
 *  Use the function `key(...) { ... }` to create this representation where the first parameter corresponds
 *  to the [key] and the second is the [assertionCreator].
 *
 *  @since 0.12.0
 */
data class KeyWithCreator<out K, V> internal constructor(val key: K, val assertionCreator: Expect<V>.() -> Unit)
