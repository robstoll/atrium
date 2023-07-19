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
 * expect(person) feature { f(it::age) } it { this toBe 20 }
 *
 * // instead of (which causes problems with Kotlin < 1.4)
 * expect(person) feature of({ f(it::age) }) { it toBe 20 }
 * ```
 *
 * Note that this workaround will be removed in some minor version after a major version with Kotlin 1.4 support
 * (most likely with Atrium v1.1.0 where Atrium v1.0.0 requires Kotlin 1.4)
 *
 * @return an [Expect] for the subject of `this` expectation.
 */

@Suppress(
    /* inline so that one does not actually call `it` on binary level */ "NOTHING_TO_INLINE",
    "DeprecatedCallableAddReplaceWith"
)
//TODO 1.2.0 remove
@Deprecated("Should no longer be necessary with Kotlin 1.4 which will be the minimum requirement for Atrium v1.2.0, will be removed without replacement")
inline infix fun <T> Expect<T>.it(noinline assertionCreator: Expect<T>.() -> Unit): Expect<T> = and(assertionCreator)
