//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:JvmMultifileClass
@file:JvmName("ImplsKt")
package ch.tutteli.atrium.logic.creating.iterable.contains.creators

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl.DefaultIterableLikeContainsAssertions
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl.DefaultIterableLikeContainsInAnyOrderAssertions

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <E, T : Any, S : IterableLikeContains.SearchBehaviour> IterableLikeContains.EntryPointStepLogic<E, T, S>._iterableLikeContainsImpl
    get() = container.getImpl(IterableLikeContainsAssertions::class) { DefaultIterableLikeContainsAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <E, T : Any> IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>._iterableLikeContainsInAnyOrderImpl
    get() = entryPointStepLogic.container.getImpl(IterableLikeContainsInAnyOrderAssertions::class) { DefaultIterableLikeContainsInAnyOrderAssertions() }

