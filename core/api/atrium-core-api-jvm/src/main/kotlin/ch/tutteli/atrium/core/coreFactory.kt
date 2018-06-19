package ch.tutteli.atrium.core

import java.util.*

/**
 * The access point to an implementation of [CoreFactory].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
actual val coreFactory by lazy { SingleServiceLoader.load(CoreFactory::class.java) }
