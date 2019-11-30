package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the thrown [Throwable] *is a* [TExpected] (the same type or a sub-type).
 *
 * Notice, that asserting a generic type is [flawed](https://youtrack.jetbrains.com/issue/KT-27826).
 * For instance `toThrow<MyException<String>>` would only check if the subject is a `MyException` without checking if
 * the element type is actually `String`.
 *
 * @return An assertion container with the new type [TExpected].
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline fun <reified TExpected : Throwable> Expect<out () -> Any?>.toThrow(): Expect<TExpected> =
    ExpectImpl.fun0.isThrowing(this, TExpected::class).getExpectOfFeature()

/**
 * Expects that the thrown [Throwable] *is a* [TExpected] (the same type or a sub-type) and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * Notice, in contrast to other assertion functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was `Throwable?` but an [Expect] of the specified type [TExpected].
 * This has the side effect that a subsequent call has only assertion functions available which are suited
 * for [TExpected]. Since [Expect] is invariant it especially means that an assertion function which was not
 * written in a generic way will not be available. Fixing such a function is easy (in most cases),
 * you need to transform it into a generic from. Following an example:
 *
 * ```
 * interface Person
 * class Student: Person
 * fun Expect<Person>.foo()        = "dummy"  // limited only to Person, not recommended
 * fun <T: Person> Expect<T>.bar() = "dummy"  // available to Person and all subtypes, the way to go
 * fun Expect<Student>.baz()       = "dummy"  // specific only for Student, ok since closed class
 *
 * val p: Person = Student()
 * expect(p)               // subject of type Person
 *   .isA<Student> { ... } // subject now refined to Student
 *   .baz()                // available via Student
 *   .foo()                // not available to Student, only to Person, results in compilation error
 *   .bar()                // available via T : Person
 * ```
 *
 * Notice, that asserting a generic type is [flawed](https://youtrack.jetbrains.com/issue/KT-27826).
 * For instance `toThrow<MyException<String>>` would only check if the subject is a `MyException` without checking if
 * the element type is actually `String`.
 *
 * @return An assertion container with the new type [TExpected].
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline fun <reified TExpected : Throwable> Expect<out () -> Any?>.toThrow(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = ExpectImpl.fun0.isThrowing(this, TExpected::class).addToFeature(assertionCreator)


/**
 * Expects that no [Throwable] is thrown at all when calling the subject (a lambda with arity 0, i.e. without argument)
 * and changes the subject of the assertion to the return value of type [R].
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <R, T : () -> R> Expect<T>.notToThrow(): Expect<R> = ExpectImpl.fun0.isNotThrowing(this).getExpectOfFeature()
