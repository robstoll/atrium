package ch.tutteli.atrium.api.infix.en_GB.creating.thirdparty

/**
 * Parameter object which contains a [description], [representation] together with [expectationExecutor].
 *
 * Use `thirdPartyExpectation("(assertJ) has same content as", file) { ... }` to create a
 * DescriptionRepresentationWithExecutor
 *
 * @property description The description of the third party expectation.
 * @property representation The representation of the third party expectation.
 * @property expectationExecutor The lambda which executes the third party expectation where the current subject is
 *   passed as parameter.
 *
 * @since 1.2.0
 */
//TODO 2.0.0 remove data?
data class DescriptionRepresentationWithExecutor<T> internal constructor(
    val description: String,
    val representation: Any?,
    val expectationExecutor: (T) -> Unit
)
