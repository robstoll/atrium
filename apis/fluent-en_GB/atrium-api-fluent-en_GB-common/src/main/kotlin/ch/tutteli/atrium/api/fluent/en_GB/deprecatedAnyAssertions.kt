//TODO remove file with 1.0.0
package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.utils.iterableLikeToIterable
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.kbox.glue
import kotlin.reflect.KClass

/**
 * Expects that the subject of `this` expectation is (equal to) [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.toBe
 */
@Deprecated("Use toEqual; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEqual<T>(expected)"))
fun <T> Expect<T>.toBe(expected: T): Expect<T> = _logicAppend { toBe(expected) }

/**
 * Expects that the subject of `this` expectation is not (equal to) [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.notToBe
 */
@Deprecated("Use notToEqual; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToEqual<T>(expected)"))
fun <T> Expect<T>.notToBe(expected: T): Expect<T> = _logicAppend { notToBe(expected) }

/**
 * Expects that the subject of `this` expectation is the same instance as [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.isSameAs
 */
@Deprecated("Use toBeTheInstance; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeTheInstance<T>(expected)"))
fun <T> Expect<T>.isSameAs(expected: T): Expect<T> = _logicAppend { isSameAs(expected) }

/**
 * Expects that the subject of `this` expectation is not the same instance as [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.isNotSameAs
 */
@Deprecated(
    "Use notToBeTheInstance; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToBeTheInstance<T>(expected)")
)
fun <T> Expect<T>.isNotSameAs(expected: T): Expect<T> = _logicAppend { isNotSameAs(expected) }

/**
 * Expects that the subject of `this` expectation is either `null` in case [assertionCreatorOrNull]
 * is `null` or is not `null` and holds all assertions [assertionCreatorOrNull] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.toBeNullIfNullGivenElse
 */
@Deprecated(
    "Use toEqualNullIfNullGivenElse; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toEqualNullIfNullGivenElse<T>(assertionCreatorOrNull)")
)
fun <T : Any> Expect<T?>.toBeNullIfNullGivenElse(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): Expect<T?> = _logicAppend { toBeNullIfNullGivenElse(assertionCreatorOrNull) }

/**
 * Expects that the subject of `this` expectation is not null and changes the subject to the non-nullable version.
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before).
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.notToBeNullFeature
 */
@Deprecated("Use notToEqualNull; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToEqualNull<T>()"))
inline fun <reified T : Any> Expect<T?>.notToBeNull(): Expect<T> = notToBeNullButOfType(T::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <T : Any> Expect<T?>.notToBeNullButOfType(kClass: KClass<T>): SubjectChangerBuilder.ExecutionStep<T?, T> =
    _logic.notToBeNullButOfType(kClass)

/**
 * Expects that the subject of `this` expectation is not null and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before)
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.notToBeNull
 */
@Deprecated(
    "Use notToEqualNull; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToEqualNull<T>(assertionCreator)")
)
inline fun <reified T : Any> Expect<T?>.notToBeNull(noinline assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    notToBeNullButOfType(T::class).transformAndAppend(assertionCreator)

/**
 * Expects that the subject of `this` expectation *is a* [TSub] (the same type or a sub-type)
 * and changes the subject to this type.
 *
 * Notice, that asserting a function type is [flawed](https://youtrack.jetbrains.com/issue/KT-27846).
 * The actual types are ignored as function types erase to Function0,
 * Function1 etc. on byte code level, which means the assertion holds as long as the subject is a
 * function and has the same amount of arguments regardless if the types differ. For instance
 * `expect({x: Int -> "hello"}).isA<String -> Unit>{}` holds, even though `(Int) -> String` is clearly not
 * a `(String) -> Unit`.
 *
 * More generally speaking, the [flaw](https://youtrack.jetbrains.com/issue/KT-27826) applies to all generic types.
 * For instance `isA<List<String>>` would only check if the subject is a `List` without checking if
 * the element type is actually `String`. Or in other words
 * `expect(listOf(1, 2)).isA<List<String>>{}` holds, even though `List<Int>` is clearly not a `List<String>`.
 *
 * @return An [Expect] with the new type [TSub].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.isAFeature
 */
@Deprecated("Use toBeAnInstanceOf; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeAnInstanceOf<TSub>()"))
inline fun <reified TSub : Any> Expect<*>.isA(): Expect<TSub> = isA(TSub::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <TSub : Any> Expect<*>.isA(kClass: KClass<TSub>): SubjectChangerBuilder.ExecutionStep<out Any?, TSub> =
    _logic.isA(kClass)

/**
 * Expects that the subject of `this` expectation *is a* [TSub] (the same type or a sub-type) and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * Notice, in contrast to other assertion functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was some type `T `, but an [Expect] of the specified type [TSub].
 * This has the side effect that a subsequent call has only assertion functions available which are suited for [TSub].
 * Since [Expect] is invariant it especially means that an assertion function which was not written in a generic way
 * will not be available. Fixing such a function is easy (in most cases),
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
 * Notice, that asserting a function type is [flawed](https://youtrack.jetbrains.com/issue/KT-27846).
 * The actual types are ignored as function types erase to Function0,
 * Function1 etc. on byte code level, which means the assertion holds as long as the subject is a
 * function and has the same amount of arguments regardless if the types differ. For instance
 * `expect({x: Int -> "hello"}).isA<String -> Unit>{}` holds, even though `(Int) -> String` is clearly not
 * a `(String) -> Unit`.
 *
 * More generally speaking, the [flaw](https://youtrack.jetbrains.com/issue/KT-27826) applies to all generic types.
 * For instance `isA<List<String>>` would only check if the subject is a `List` without checking if
 * the element type is actually `String`. Or in other words
 * `expect(listOf(1, 2)).isA<List<String>>{}` holds, even though `List<Int>` is clearly not a `List<String>`.
 *
 * @return An [Expect] with the new type [TSub].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.isA
 */
@Deprecated("Use toBeAnInstanceOf; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeAnInstanceOf<TSub>(assertionCreator)"))
inline fun <reified TSub : Any> Expect<*>.isA(noinline assertionCreator: Expect<TSub>.() -> Unit): Expect<TSub> =
    isA(TSub::class).transformAndAppend(assertionCreator)

/**
 * Expects that the subject of `this` expectation is not (equal to) [expected] and [otherValues].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.isNoneOf
 *
 * @since 0.13.0
 */
@Deprecated(
    "Use notToEqualOneOf; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToEqualOneOf<T>(expected, *otherValues)")
)
fun <T> Expect<T>.isNoneOf(expected: T, vararg otherValues: T): Expect<T> =
    _logicAppend { isNotIn(expected glue otherValues) }

/**
 * Expects that the subject of `this` expectation is not (equal to) any value of [expected].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case the iterable is empty.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.AnyAssertionSamples.isNotIn
 *
 * @since 0.13.0
 */
@Deprecated("Use notToBeIn; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToBeIn<T>(expected)"))
fun <T> Expect<T>.isNotIn(expected: IterableLike): Expect<T> =
    _logicAppend { isNotIn(iterableLikeToIterable(expected)) }


