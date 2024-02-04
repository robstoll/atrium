package ch.tutteli.atrium.assertions

@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class ExperimentalExpectationApi(val reason: String = "")
