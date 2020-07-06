//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:JvmMultifileClass
@file:JvmName("ImplsKt")
package ch.tutteli.atrium.logic.kotlin_1_3

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.kotlin_1_3.impl.DefaultResultAssertions

@PublishedApi
internal inline val <T> AssertionContainer<T>._resultImpl
    get() = getImpl(ResultAssertions::class) { DefaultResultAssertions() }

