package ch.tutteli.atrium.core

import kotlin.RequiresOptIn

@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class ExperimentalNewExpectTypes
