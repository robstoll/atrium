//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:JvmMultifileClass
@file:JvmName("ImplsKt")
package ch.tutteli.atrium.logic.creating.charsequence.contains.creators

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl.DefaultCharSequenceContainsAssertions

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerStepLogic<T, S>._charSequenceContainsImpl
    get() = containsBuilder.container.getImpl(CharSequenceContainsAssertions::class) { DefaultCharSequenceContainsAssertions() }

