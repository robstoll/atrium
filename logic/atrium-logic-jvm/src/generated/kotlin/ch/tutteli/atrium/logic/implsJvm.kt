//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:JvmMultifileClass
@file:JvmName("ImplsKt")
package ch.tutteli.atrium.logic

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultFloatingPointJvmAssertions

@PublishedApi
internal inline val <T> AssertionContainer<T>._floatingPointJvmImpl
    get() = getImpl(FloatingPointJvmAssertions::class) { DefaultFloatingPointJvmAssertions() }

