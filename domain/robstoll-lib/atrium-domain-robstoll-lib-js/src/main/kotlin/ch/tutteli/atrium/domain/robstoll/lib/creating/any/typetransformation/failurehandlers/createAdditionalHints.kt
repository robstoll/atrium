package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion

actual fun createAdditionalHints(throwable: Throwable, maxStackTrace: Int): List<Assertion> = emptyList()
