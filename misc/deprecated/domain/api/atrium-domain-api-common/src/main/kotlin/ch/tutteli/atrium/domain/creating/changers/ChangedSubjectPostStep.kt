package ch.tutteli.atrium.domain.creating.changers

import ch.tutteli.atrium.creating.Expect

/**
 * Option step which allows to decide what should be done with the transformed subject of type [R].
 *
 * Notice, this class does not add any functionality to [PostFinalStep] and is more like a marker.
 * The purpose of the marker is extensibility, this way you can write a post-final-step which only applies to
 * [ChangedSubjectPostStep] and not to all kind of [PostFinalStep].
 *
 * @param expect The [Expect] which was involved in the building process
 *   and holds assertion for the initial subject.
 * @param transform The subject transformation which creates and returns a new [Expect] of type [R].
 * @param transformAndApply The subject transformation which not only creates and
 *   returns a new [Expect] of type [R] but also applies a given assertionCreator lambda.
 */
class ChangedSubjectPostStep<T, R>(
    expect: Expect<T>,
    transform: Expect<T>.() -> Expect<R>,
    transformAndApply: Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>
) : PostFinalStep<T, R, Expect<R>>(expect, transform, transformAndApply)
