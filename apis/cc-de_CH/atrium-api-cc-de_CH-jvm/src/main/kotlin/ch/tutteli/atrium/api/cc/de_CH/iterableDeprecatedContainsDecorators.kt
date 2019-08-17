@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsDecoratorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

@Deprecated("Use the extension fun `inBeliebigerReihenfolge` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.inBeliebigerReihenfolge"))
fun <E, T : Iterable<E>> getInBeliebigerReihenfolge(builder: IterableContainsBuilder<E, T, NoOpSearchBehaviour>): IterableContainsBuilder<E, T, InAnyOrderSearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.inBeliebigerReihenfolge.searchBehaviour)

@Deprecated("Use the extension fun `nur` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.nur"))
fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContainsBuilder<E, T, InAnyOrderSearchBehaviour>): IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.nur.searchBehaviour)

@Deprecated("Use the extension fun `inGegebenerReihenfolge` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.inGegebenerReihenfolge"))
fun <E, T : Iterable<E>> getInGegebenerReihenfolge(builder: IterableContainsBuilder<E, T, NoOpSearchBehaviour>): IterableContainsBuilder<E, T, InOrderSearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.inGegebenerReihenfolge.searchBehaviour)

@Deprecated("Use the extension fun `only` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.nur"))
fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContainsBuilder<E, T, InOrderSearchBehaviour>): IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.nur.searchBehaviour)
