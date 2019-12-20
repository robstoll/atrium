package ch.tutteli.atrium.domain.creating.changers.impl.subjectchanger

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger

/**
 * Represents a [SubjectChanger.FailureHandler] which as an adapter for another failure handler by mapping first
 * the given subject to another type [R1] which is understood as input of the other failure handler.
 *
 * Effectively turning a `FailureHandler<R1, R>` into a `FailureHandler<T, R>` with the help of a mapping
 * function `(T) -> R1`
 *
 * @param T The type of the subject
 * @param R1 The type of the mapped subject
 * @param R The type of the subject after the subject change (if it were possible).
 *
 * @since 0.9.0
 */
class FailureHandlerAdapter<T, R1, R>(
    val failureHandler: SubjectChanger.FailureHandler<R1, R>,
    val map: (T) -> R1
) : SubjectChanger.FailureHandler<T, R> {

    override fun createAssertion(
        originalAssertionContainer: Expect<T>,
        descriptiveAssertion: Assertion,
        maybeAssertionCreator: Option<Expect<R>.() -> Unit>
    ): Assertion {
        return originalAssertionContainer._domain.changeSubject.unreported(map)
            .let {
                failureHandler.createAssertion(it, descriptiveAssertion, maybeAssertionCreator)
            }
    }
}
