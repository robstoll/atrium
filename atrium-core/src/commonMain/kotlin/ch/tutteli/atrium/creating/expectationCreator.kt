package ch.tutteli.atrium.creating

/**
 * A type-alias which allows to refer (in KDoc) to a definition of a lambda responsible to create expectations.
 *
 * @param SubjectT The type of the subject for which the expectations are created.
 *
 * @since 1.1.0
 */
typealias ExpectationCreator<SubjectT> = Expect<SubjectT>.() -> Unit

/**
 * Can be used in places where a
 * [lambda with receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver)
 * is required where the receiver is of type [Expect]`<T>` and Kotlin is not able to infer it automatically.
 *
 * @param SubjectT The type of the subject for which the expectations are created.
 *
 * @since 1.3.0
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <SubjectT> expectationCreator(noinline expectationCreator: Expect<SubjectT>.() -> Unit) = expectationCreator
