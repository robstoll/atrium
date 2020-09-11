//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Creates an [IterableContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Only here to retain binary backward compatibility; will be removed with 1.0.0")
fun <E, T : Iterable<E>> contains(
    expect: Expect<T>,
    @Suppress("UNUSED_PARAMETER") o: o
): IterableContains.Builder<E, T, NoOpSearchBehaviour> =
    ExpectImpl.iterable.containsBuilder(expect)

/**
 * Creates an [IterableContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Only here to retain binary backward compatibility; will be removed with 1.0.0")
fun <E, T : Iterable<E>> containsNot(
    expect: Expect<T>,
    @Suppress("UNUSED_PARAMETER") o: o
): NotCheckerOption<E, T, NotSearchBehaviour> =
    NotCheckerOptionImpl(ExpectImpl.iterable.containsNotBuilder(expect))
