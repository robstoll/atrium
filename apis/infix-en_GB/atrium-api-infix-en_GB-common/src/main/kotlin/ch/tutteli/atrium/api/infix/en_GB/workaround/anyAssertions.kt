package ch.tutteli.atrium.api.infix.en_GB.workaround

import ch.tutteli.atrium.api.infix.en_GB.and
import ch.tutteli.atrium.creating.Expect

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * Intended to be used in combination with feature assertions where Kotlin < 1.4 is not able to infer the correct type.
 * For instance:
 * ```
 * // use
 * expect(person) feature { f(it::age) } it { o toBe 20 }
 *
 * // instead of (which causes problems with Kotlin < 1.4)
 * expect(person) feature of({ f(it::age) }) { o toBe 20 }
 * ```
 *
 * Note that this workaround will be removed in some minor version after a major version with Kotlin 1.4 support
 * (most likely with Atrium v1.1.0 where Atrium v1.0.0 requires Kotlin 1.4)
 *
 * @return This assertion container to support a fluent API.
 */
@Suppress("NOTHING_TO_INLINE" /* inline so that one does not actually call `it` on binary level */)
inline infix fun <T> Expect<T>.it(noinline assertionCreator: Expect<T>.() -> Unit): Expect<T> = and(assertionCreator)
