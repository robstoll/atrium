package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.utils.iterableLikeToIterable
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.kbox.glue
import kotlin.reflect.KClass

/**
 * Expects that the subject of `this` expectation is equal to [expected]
 * where the comparison is carried out based on [Any.equals].
 *
 * Use [toBeEqualComparingTo] if you want a comparison based on [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.toEqual
 *
 * @since 0.17.0
 */
fun <T> Expect<T>.toEqual(expected: T): Expect<T> = _logicAppend { toBe(expected) }

/**
 * Expects that the subject of `this` expectation is **not** equal to [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToEqual
 *
 * @since 0.17.0
 */
fun <T> Expect<T>.notToEqual(expected: T): Expect<T> = _logicAppend { notToBe(expected) }

/**
 * Expects that the subject of `this` expectation is the same instance as [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.toBeTheInstance
 *
 * @since 0.17.0
 */
fun <T> Expect<T>.toBeTheInstance(expected: T): Expect<T> = _logicAppend { isSameAs(expected) }

/**
 * Expects that the subject of `this` expectation is **not** the same instance as [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToBeTheInstance
 *
 * @since 0.17.0
 */
fun <T> Expect<T>.notToBeTheInstance(expected: T): Expect<T> = _logicAppend { isNotSameAs(expected) }

/**
 * Expects that the subject of `this` expectation is either `null` in case [assertionCreatorOrNull]
 * is `null` or is not `null` and holds all assertions [assertionCreatorOrNull] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.toEqualNullIfNullGivenElse
 */
fun <T : Any> Expect<T?>.toEqualNullIfNullGivenElse(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): Expect<T?> = _logicAppend { toBeNullIfNullGivenElse(assertionCreatorOrNull) }

/**
 * Expects that the subject of `this` expectation is not null and changes the subject to the non-nullable version.
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before).
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToEqualNullFeature
 */
inline fun <reified T : Any> Expect<T?>.notToEqualNull(): Expect<T> =
    notToEqualNullButToBeAnInstanceOf(T::class).transform()


@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <T : Any> Expect<T?>.notToEqualNullButToBeAnInstanceOf(kClass: KClass<T>): SubjectChangerBuilder.ExecutionStep<T?, T> =
    _logic.notToBeNullButOfType(kClass)

/**
 * Expects that the subject of `this` expectation is not null and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before)
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToEqualNull
 */
inline fun <reified T : Any> Expect<T?>.notToEqualNull(noinline assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    notToEqualNullButToBeAnInstanceOf(T::class).transformAndAppend(assertionCreator)

/**
 * Expects that the type of the subject of `this` expectation is the defined [SubTypeOfSubjectT] or a subtype thereof
 * and down-casts the subject to [SubTypeOfSubjectT].
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
 * @param SubTypeOfSubjectT Typically a type which is a subtype of `SubjectT` in this `Expect<SubjectT>`.
 * @return An [Expect] with the new type [SubTypeOfSubjectT].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.toBeAnInstanceOfFeature
 *
 * @since 0.17.0
 */
inline fun <reified SubTypeOfSubjectT : Any> Expect<*>.toBeAnInstanceOf(): Expect<SubTypeOfSubjectT> = toBeAnInstanceOf(SubTypeOfSubjectT::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <SubTypeOfSubjectT : Any> Expect<*>.toBeAnInstanceOf(
    kClass: KClass<SubTypeOfSubjectT>
): SubjectChangerBuilder.ExecutionStep<out Any?, SubTypeOfSubjectT> {
    @Suppress(
        // AssertionContainer is invariant hence the cast from `out Any?` to `Any?` is unsafe but in this case it is
        // safe as the only action we carry out here is down-casting from whatever to SubTypeOfSubjectT if the subject is actually
        // a SubTypeOfSubjectT
        "UNCHECKED_CAST"
    )
    val assertionContainer = _logic as AssertionContainer<Any?>
    return assertionContainer.isA(kClass)
}

/**
 * Expects that the type of the subject of `this` expectation is the defined [SubTypeOfSubjectT] or a subtype thereof
 * and that it holds all assertions the given [assertionCreator] creates.
 *
 * Notice, in contrast to other expectation functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was some type `T `, but an [Expect] of the specified type [SubTypeOfSubjectT].
 * This has the side effect that a subsequent call has only assertion functions available which are suited for [SubTypeOfSubjectT].
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
 * expect(p)                 // subject of type Person
 *   .toBeAnInstanceOf<Student> { ... } // subject now refined to Student
 *   .baz()                  // available via Student
 *   .foo()                  // not available to Student, only to Person, results in compilation error
 *   .bar()                  // available via T : Person
 * ```
 *
 * Notice, that asserting a function type is [flawed](https://youtrack.jetbrains.com/issue/KT-27846).
 * The actual types are ignored as function types erase to Function0,
 * Function1 etc. on byte code level, which means the assertion holds as long as the subject is a
 * function and has the same amount of arguments regardless if the types differ. For instance
 * `expect({x: Int -> "hello"}).toBeAnInstanceOfnInstanceOf<String -> Unit>{}` holds, even though `(Int) -> String` is clearly not
 * a `(String) -> Unit`.
 *
 * More generally speaking, the [flaw](https://youtrack.jetbrains.com/issue/KT-27826) applies to all generic types.
 * For instance `toBeAnInstanceOfnInstanceOf<List<String>>` would only check if the subject is a `List` without checking if
 * the element type is actually `String`. Or in other words
 * `expect(listOf(1, 2)).toBeAnInstanceOfnInstanceOf<List<String>>{}` holds, even though `List<Int>` is clearly not a `List<String>`.
 *
 * @param SubTypeOfSubjectT Typically a type which is a subtype of `SubjectT` in this `Expect<SubjectT>`.
 * @return An [Expect] with the new type [SubTypeOfSubjectT].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.toBeAnInstanceOf
 *
 * @since 0.17.0
 */
inline fun <reified SubTypeOfSubjectT : Any> Expect<*>.toBeAnInstanceOf(noinline assertionCreator: Expect<SubTypeOfSubjectT>.() -> Unit): Expect<SubTypeOfSubjectT> =
    toBeAnInstanceOf(SubTypeOfSubjectT::class).transformAndAppend(assertionCreator)

/**
 * Expects that the type of the subject of `this` expectation is **not** the defined [SubTypeOfSubjectT] neither
 * a subtype thereof.
 *
 * Notice, this function has only one type parameter in order that you do not have to restate the type of the current
 * subject. But that also means that we need to return `Expect<*>` or in other words, we loose the type of the subject.
 * Which means, if you want to state a further expectation after [notToBeAnInstanceOf] then you most likely want to
 * use the overload which expects one (or more) [KClass] instead which keeps the type of the initial subject.
 *
 * @param SubTypeOfSubjectT the type which we do not expect to be the same or a super-type of the subject
 *   of `this` expectation.
 * @return an [Expect] for the subject of `this` expectation but untyped (with a star projection).
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToBeAnInstanceOf
 *
 * @since 1.1.0
 */
inline fun <reified SubTypeOfSubjectT : Any> Expect<*>.notToBeAnInstanceOf(): Expect<*> =
    notToBeAnInstanceOf(SubTypeOfSubjectT::class)

/**
 * Expects that the type of the subject of `this` expectation is **not** the given [type] neither
 * a subtype thereof and same same for the [otherTypes].
 *
 * @param type one of the types which we do not expect to be the same or a super-type of the type of the subject
 *   of `this` expectation.
 * @param otherTypes all types which we do not expect to be the same or a super-type of the type of the subject
 *   of `this` expectation.
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToBeAnInstanceOfKClasses
 *
 * @since 1.1.0
 */
fun <T> Expect<T>.notToBeAnInstanceOf(type: KClass<*>, vararg otherTypes: KClass<*>): Expect<T> =
    _logicAppend { notToBeAnInstanceOf(type glue otherTypes) }

/**
 * Expects that the subject of `this` expectation is not equal to [expected] and [otherValues].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToEqualOneOf
 *
 * @since 0.17.0
 */
fun <T> Expect<T>.notToEqualOneOf(expected: T, vararg otherValues: T): Expect<T> =
    _logicAppend { isNotIn(expected glue otherValues) }

/**
 * Expects that the subject of `this` expectation is not equal to any value of [expected].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case the iterable is empty.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.notToEqualOneIn
 *
 * @since 0.17.0
 */
fun <T> Expect<T>.notToEqualOneIn(expected: IterableLike): Expect<T> =
    _logicAppend { isNotIn(iterableLikeToIterable(expected)) }

/**
 * Can be used to separate single assertions.
 *
 * For instance `expect(1).toBeLessThan(2).and.toBeGreaterThan(0)` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and the second
 * asserts that 1 is greater than 0. If the first assertion fails, then the second assertion is not evaluated.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.andFeature
 */
inline val <T> Expect<T>.and: Expect<T> get() = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `expect(1).toBeLessThan(3).and { toBeEven(); toBeGreaterThan(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence, the reporting might (depending on the configured [Reporter]) contain both failing sub-assertions.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyExpectationSamples.and
 */
infix fun <T> Expect<T>.and(assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    _logic.appendAsGroup(assertionCreator)
