package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.notToThrow
import ch.tutteli.atrium.logic.toThrow
import kotlin.reflect.KClass

/**
 * Expects that invoking the subject (a function with arity 0, i.e. without arguments) throws a [Throwable]
 * of type [ExpectedThrowableT] or a subtype.
 *
 * Notice, that asserting a generic type is [flawed](https://youtrack.jetbrains.com/issue/KT-27826).
 * For instance `toThrow<MyException<String>>` would only check if the subject is a `MyException` without checking if
 * the element type is actually `String`.
 *
 * @return An [Expect] with the new type [ExpectedThrowableT].
 */
inline fun <reified ExpectedThrowableT : Throwable> Expect<out () -> Any?>.toThrow(): Expect<ExpectedThrowableT> =
    toThrow(ExpectedThrowableT::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <ExpectedThrowableT : Throwable> Expect<out () -> Any?>.toThrow(
    kClass: KClass<ExpectedThrowableT>
): SubjectChangerBuilder.ExecutionStep<*, ExpectedThrowableT> = _logic.toThrow(kClass)

/**
 * Expects that invoking the subject (a function with arity 0, i.e. without arguments) throws a [Throwable]
 * of type [ExpectedThrowableT] or a subtype and that it holds all assertions the given [assertionCreator] creates.
 *
 * Notice, in contrast to other assertion functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was `Throwable?` but an [Expect] of the specified type [ExpectedThrowableT].
 * This has the side effect that a subsequent call has only assertion functions available which are suited
 * for [ExpectedThrowableT]. Since [Expect] is invariant it especially means that an assertion function which was not
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
 * @return An [Expect] with the new type [ExpectedThrowableT].
 */
inline infix fun <reified ExpectedThrowableT : Throwable> Expect<out () -> Any?>.toThrow(
    noinline assertionCreator: Expect<ExpectedThrowableT>.() -> Unit
): Expect<ExpectedThrowableT> = toThrow(ExpectedThrowableT::class).transformAndAppend(assertionCreator)


/**
 * Expects that no [Throwable] is thrown at all when calling the subject (a lambda with arity 0, i.e. without arguments)
 * and changes the subject of `this` expectation to the return value of type [R].
 *
 * @return An [Expect] with the new type [R].
 */
fun <R, T : () -> R> Expect<T>.notToThrow(): Expect<R> =
    _logic.notToThrow().transform()

/**
 * Expects that no [Throwable] is thrown at all when calling the subject (a lambda with arity 0, i.e. without arguments)
 * and that the corresponding return value holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the new type [R].
 */
infix fun <R, T : () -> R> Expect<T>.notToThrow(
    assertionCreator: Expect<R>.() -> Unit
): Expect<R> = _logic.notToThrow().transformAndAppend(assertionCreator)
