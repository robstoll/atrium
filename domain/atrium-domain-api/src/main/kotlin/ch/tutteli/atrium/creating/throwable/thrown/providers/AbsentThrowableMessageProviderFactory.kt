package ch.tutteli.atrium.creating.throwable.thrown.providers

import ch.tutteli.atrium.SingleServiceLoader
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable
import java.util.*

/**
 * The access point to an implementation of [AbsentThrowableMessageProviderFactory].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val absentThrowableMessageProviderFactory by lazy { SingleServiceLoader.load(AbsentThrowableMessageProviderFactory::class.java) }


/**
 * Defines the minimum set of [ThrowableThrown.AbsentThrowableMessageProvider]s,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AbsentThrowableMessageProviderFactory {

    fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
}
