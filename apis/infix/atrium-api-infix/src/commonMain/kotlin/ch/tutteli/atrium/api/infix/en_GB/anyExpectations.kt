package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.utils.iterableLikeToIterable
import ch.tutteli.atrium.reporting.Reporter
import kotlin.reflect.KClass

/**
 * Expects that the subject of `this` expectation is (equal to) [expected]
 * where the comparison is carried out based on [Any.equals].
 *
 * Use [toBeEqualComparingTo] if you want a comparison based on [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.toEqual
 *
 * @since 0.17.0
 */
infix fun <T> Expect<T>.toEqual(expected: T): Expect<T> = _logicAppend { toBe(expected) }

/**
 * Expects that the subject of `this` expectation is not (equal to) [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.notToEqual
 *
 * @since 0.17.0
 */
infix fun <T> Expect<T>.notToEqual(expected: T): Expect<T> = _logicAppend { notToBe(expected) }

/**
 * Expects that the subject of `this` expectation is the same instance as [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.toBeTheInstance
 *
 * @since 0.17.0
 */
infix fun <T> Expect<T>.toBeTheInstance(expected: T): Expect<T> = _logicAppend { isSameAs(expected) }

/**
 * Expects that the subject of `this` expectation is not the same instance as [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.notToBeTheInstance
 *
 * @since 0.17.0
 */
infix fun <T> Expect<T>.notToBeTheInstance(expected: T): Expect<T> = _logicAppend { isNotSameAs(expected) }


/**
 * Expects that the subject of `this` expectation is either `null` in case [assertionCreatorOrNull]
 * is `null` or is not `null` and holds all assertions [assertionCreatorOrNull] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.toEqualNullIfNullGivenElse
 *
 * @since 0.17.0
 */
infix fun <T : Any> Expect<T?>.toEqualNullIfNullGivenElse(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): Expect<T?> = _logicAppend { toBeNullIfNullGivenElse(assertionCreatorOrNull) }


/**
 * Expects that the subject of `this` expectation is not null and changes the subject to the non-nullable version.
 *
 * @param o The filler object [o].
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before).
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.notToEqualNullFeature
 *
 * @since 0.17.0
 */
inline infix fun <reified T : Any> Expect<T?>.notToEqualNull(@Suppress("UNUSED_PARAMETER") o: o): Expect<T> =
    notToEqualNullButToBeAnInstanceOf(T::class).transform()

/**
 * Expects that the subject of `this` expectation is not null and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before).
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.notToEqualNull
 *
 * @since 0.17.0
 */
inline infix fun <reified T : Any> Expect<T?>.notToEqualNull(noinline assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    notToEqualNullButToBeAnInstanceOf(T::class).transformAndAppend(assertionCreator)

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <T : Any> Expect<T?>.notToEqualNullButToBeAnInstanceOf(kClass: KClass<T>): SubjectChangerBuilder.ExecutionStep<T?, T> =
    _logic.notToBeNullButOfType(kClass)


/**
 * Expects that the subject of `this` expectation *is a* [TSub] (the same type or a sub-type)
 * and changes the subject to this type.
 *
 * Notice, that asserting a function type is [flawed](https://youtrack.jetbrains.com/issue/KT-27846).
 * The actual types are ignored as function types erase to Function0,
 * Function1 etc. on byte code level, which means the assertion holds as long as the subject is a
 * function and has the same amount of arguments regardless if the types differ. For instance
 * `expect({x: Int -> "hello"}).toBeAnInstanceOf<String -> Unit>{}` holds, even though `(Int) -> String` is clearly not
 * a `(String) -> Unit`.
 *
 * More generally speaking, the [flaw](https://youtrack.jetbrains.com/issue/KT-27826) applies to all generic types.
 * For instance `toBeAnInstanceOf<List<String>>` would only check if the subject is a `List` without checking if
 * the element type is actually `String`. Or in other words
 * `expect(listOf(1, 2)).toBeAnInstanceOf<List<String>>{}` holds, even though `List<Int>` is clearly not a `List<String>`.
 *
 * @return An [Expect] with the new type [TSub].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.toBeAnInstanceOfFeature
 *
 * @since 0.17.0
 */
//TODO make infix and add `o` as parameter as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
inline fun <reified TSub : Any> Expect<*>.toBeAnInstanceOf(): Expect<TSub> =
    toBeAnInstanceOf(TSub::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <TSub : Any> Expect<*>.toBeAnInstanceOf(
    kClass: KClass<TSub>
): SubjectChangerBuilder.ExecutionStep<out Any?, TSub> {
    @Suppress(
        // AssertionContainer is invariant hence the cast from `out Any?` to `Any?` is unsafe but in this case it is
        // safe as the only action we carry out here is down-casting from whatever to TSub if the subject is actually
        // a TSub
        "UNCHECKED_CAST"
    )
    val assertionContainer = _logic as AssertionContainer<Any?>
    return assertionContainer.isA(kClass)
}

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun  <TNotExpected : Any> Expect<*>.notToBeAnInstanceOf(notExpectedClass: KClass<TNotExpected>): Expect<*> =
    _logicAppend { notToBeAnInstanceOf(notExpectedClass) }

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
 *   .toBeAnInstanceOf<Student> { ... } // subject now refined to Student
 *   .baz()                // available via Student
 *   .foo()                // not available to Student, only to Person, results in compilation error
 *   .bar()                // available via T : Person
 * ```
 *
 * Notice, that asserting a function type is [flawed](https://youtrack.jetbrains.com/issue/KT-27846).
 * The actual types are ignored as function types erase to Function0,
 * Function1 etc. on byte code level, which means the assertion holds as long as the subject is a
 * function and has the same amount of arguments regardless if the types differ. For instance
 * `expect({x: Int -> "hello"}).toBeAnInstanceOf<String -> Unit>{}` holds, even though `(Int) -> String` is clearly not
 * a `(String) -> Unit`.
 *
 * More generally speaking, the [flaw](https://youtrack.jetbrains.com/issue/KT-27826) applies to all generic types.
 * For instance `toBeAnInstanceOf<List<String>>` would only check if the subject is a `List` without checking if
 * the element type is actually `String`. Or in other words
 * `expect(listOf(1, 2)).toBeAnInstanceOf<List<String>>{}` holds, even though `List<Int>` is clearly not a `List<String>`.
 *
 * @return An [Expect] with the new type [TSub].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.toBeAnInstanceOf
 *
 * @since 0.17.0
 */
inline infix fun <reified TSub : Any> Expect<*>.toBeAnInstanceOf(noinline assertionCreator: Expect<TSub>.() -> Unit): Expect<TSub> =
    toBeAnInstanceOf(TSub::class).transformAndAppend(assertionCreator)

/**
 * Expects that the subject of `this` expectation *is not a* [TNotExpected] (the same type or a sub-type) and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return the calling [Expect] with the same type [T] (see wildcard).
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.notToBeInstanceOf
 *
 * @since 1.1.0
 */
inline fun <reified TNotExpected : Any> Expect<*>.notToBeAnInstanceOf(): Expect<*> =
    notToBeAnInstanceOf(TNotExpected::class)


/**
 * Expects that the subject of `this` expectation is not equal to any value of [values].
 *
 * @param values The values which are not expected to be equal to the subject of `this` expectation
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.notToEqualOneOf
 *
 * @since 0.17.0
 */
infix fun <T> Expect<T>.notToEqualOneOf(values: Values<T>): Expect<T> =
    _logicAppend { isNotIn(values.toList()) }

/**
 * Expects that the subject of `this` expectation is not equal to any value of [expected].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case the iterable is empty.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.notToEqualOneIn
 *
 * @since 0.17.0
 */
infix fun <T> Expect<T>.notToEqualOneIn(expected: IterableLike): Expect<T> =
    _logicAppend { isNotIn(iterableLikeToIterable(expected)) }

/**
 * Can be used to separate single assertions.
 *
 * For instance `expect(1) toBeLessThan 2 and toBeGreaterThan 0` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and the second
 * asserts that 1 is greater than 0. If the first assertion fails, then the second assertion is not evaluated.
 *
 * @param o The filler object [o].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.andFeature
 *
 * @since 0.12.0
 */
@Suppress("NOTHING_TO_INLINE")
inline infix fun <T> Expect<T>.and(@Suppress("UNUSED_PARAMETER") o: o): Expect<T> = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `expect(1) toBeLessThan 3 and { it toBe even; it toBeGreaterThan 1 }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [Reporter]) contain both failing sub-assertions.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.and
 */
infix fun <T> Expect<T>.and(assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    _logic.appendAsGroup(assertionCreator)

/**
 * Inline property referring actually to `this` and allows writing infix assertions within an expectation-group
 *
 * For instance, instead of:
 * ```
 * expect("hello world") {
 *   this startsWith "hello"
 *   this endsWith "world"
 * }
 * ```
 * You can write
 * ```
 * expect("hello world") {
 *   it startsWith "hello"
 *   it endsWith "world"
 * }
 * ```
 *
 * @return `this`
 *
 * @since 0.12.0
 */
inline val <T> Expect<T>.it: Expect<T> get() : Expect<T> = this

/**
 * Inline property referring actually to `this` and allows writing infix assertions within an expectation-group
 *
 * For instance, instead of:
 * ```
 * expect(person) {
 *   this name toEqual 1
 * }
 * ```
 * You can write
 * ```
 * expect("hello world") {
 *   its name toEqual 1
 * }
 * ```
 *
 * @return `this`
 *
 * @since 0.12.0
 */
inline val <T> Expect<T>.its: Expect<T> get() : Expect<T> = this
