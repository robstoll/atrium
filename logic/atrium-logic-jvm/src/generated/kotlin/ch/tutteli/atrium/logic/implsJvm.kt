//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:JvmMultifileClass
@file:JvmName("ImplsKt")
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultBigDecimalAssertions
import ch.tutteli.atrium.logic.impl.DefaultChronoLocalDateAssertions
import ch.tutteli.atrium.logic.impl.DefaultChronoLocalDateTimeAssertions
import ch.tutteli.atrium.logic.impl.DefaultChronoZonedDateTimeAssertions
import ch.tutteli.atrium.logic.impl.DefaultFloatingPointJvmAssertions
import ch.tutteli.atrium.logic.impl.DefaultLocalDateAssertions
import ch.tutteli.atrium.logic.impl.DefaultLocalDateTimeAssertions
import ch.tutteli.atrium.logic.impl.DefaultOptionalAssertions
import ch.tutteli.atrium.logic.impl.DefaultPathAssertions
import ch.tutteli.atrium.logic.impl.DefaultZonedDateTimeAssertions

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._bigDecimalImpl
    get(): BigDecimalAssertions = getImpl(BigDecimalAssertions::class) { DefaultBigDecimalAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._chronoLocalDateImpl
    get(): ChronoLocalDateAssertions = getImpl(ChronoLocalDateAssertions::class) { DefaultChronoLocalDateAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._chronoLocalDateTimeImpl
    get(): ChronoLocalDateTimeAssertions = getImpl(ChronoLocalDateTimeAssertions::class) { DefaultChronoLocalDateTimeAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._chronoZonedDateTimeImpl
    get(): ChronoZonedDateTimeAssertions = getImpl(ChronoZonedDateTimeAssertions::class) { DefaultChronoZonedDateTimeAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._floatingPointJvmImpl
    get(): FloatingPointJvmAssertions = getImpl(FloatingPointJvmAssertions::class) { DefaultFloatingPointJvmAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._localDateImpl
    get(): LocalDateAssertions = getImpl(LocalDateAssertions::class) { DefaultLocalDateAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._localDateTimeImpl
    get(): LocalDateTimeAssertions = getImpl(LocalDateTimeAssertions::class) { DefaultLocalDateTimeAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._optionalImpl
    get(): OptionalAssertions = getImpl(OptionalAssertions::class) { DefaultOptionalAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._pathImpl
    get(): PathAssertions = getImpl(PathAssertions::class) { DefaultPathAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._zonedDateTimeImpl
    get(): ZonedDateTimeAssertions = getImpl(ZonedDateTimeAssertions::class) { DefaultZonedDateTimeAssertions() }

