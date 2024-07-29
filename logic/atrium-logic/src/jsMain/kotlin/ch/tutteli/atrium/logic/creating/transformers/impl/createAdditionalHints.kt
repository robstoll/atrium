//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion

actual fun createAdditionalHints(throwable: Throwable): List<Assertion> = emptyList()
