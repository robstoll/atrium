@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder

import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

@Suppress("RemoveExplicitTypeArguments" /* makes type of toThrow explicit */)
inline fun <reified TExpected : Throwable> Expect<out suspend () -> Any?>.toThrow(): Expect<TExpected> =
    ExpectImpl.changeSubject(this).unreported { { runBlocking { it() } } }.toThrow<TExpected>()
