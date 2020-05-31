@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsCheckersKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder as DeprecatedAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.IterableContainsAtMostCheckerBuilder as DeprecatedAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.IterableContainsButAtMostCheckerBuilder as DeprecatedButAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.IterableContainsExactlyCheckerBuilder as DeprecatedExactlyCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.IterableContainsNotOrAtMostCheckerBuilder as DeprecatedNotOrAtMostCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder as DeprecatedBuilder

@Deprecated("Use the extension fun `zumindest` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.zumindest(times)"))
fun <E, T : Iterable<E>> zumindest(builder:  DeprecatedBuilder<E, T, InAnyOrderSearchBehaviour>, times: Int): DeprecatedAtLeastCheckerBuilder<E, T>
    = DeprecatedAtLeastCheckerBuilder(times, builder)

@Deprecated("Use the extension fun `aberHoechstens` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.aberHoechstens(times)"))
fun <E, T : Iterable<E>> aberHoechstens(checkerBuilder: DeprecatedAtLeastCheckerBuilder<E, T>, times: Int): DeprecatedButAtMostCheckerBuilder<E, T>
    = DeprecatedButAtMostCheckerBuilder(times, checkerBuilder, checkerBuilder.containsBuilder)

@Deprecated("Use the extension fun `genau` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.genau(times)"))
fun <E, T : Iterable<E>> genau(builder: DeprecatedBuilder<E, T, InAnyOrderSearchBehaviour>, times: Int): DeprecatedExactlyCheckerBuilder<E, T>
    = DeprecatedExactlyCheckerBuilder(times, builder)


@Deprecated("Use the extension fun `hoechstens` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.hoechstens(times)"))
fun <E, T : Iterable<E>> hoechstens(builder: DeprecatedBuilder<E, T, InAnyOrderSearchBehaviour>, times: Int): DeprecatedAtMostCheckerBuilder<E, T>
    = DeprecatedAtMostCheckerBuilder(times, builder)

@Deprecated("Use the extension fun `nichtOderHoechstens` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.nichtOderHoechstens(times)"))
fun <E, T : Iterable<E>> nichtOderHoechstens(builder: DeprecatedBuilder<E, T, InAnyOrderSearchBehaviour>, times: Int): DeprecatedNotOrAtMostCheckerBuilder<E, T>
    = DeprecatedNotOrAtMostCheckerBuilder(times, builder)
