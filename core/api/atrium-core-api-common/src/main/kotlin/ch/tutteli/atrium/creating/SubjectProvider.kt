package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.None

/**
 * Provides the subject of an [Assertion].
 *
 * Notice, this interface has its mere purpose to facilitate the transition from [Assert] to [Expect].
 * It might well be that we are going to remove it with 1.0.0 without previous notice.
 * Hence, to be on the safe side, you should use [Expect] instead.
 */
interface SubjectProvider<out T> {
    
    /**
     * The subject of an [Assertion] -- deprecated, will be removed with 1.0.0.
     *
     * Accessing the subject in places which are not safe for reporting breaks reporting and can hinder
     * that all assertions can be evaluated. For instance:
     * ```
     * expect(person).isA<Student> { // is not a student -> subject is now undefined, throws PlantHasNoSubjectException if accessed
     *   property(subject::firstName).toBe("Robert")
     *   property(Person::lastName).toBe("Stoll") // is not evaluated as subject one line above already threw PlantHasNoSubjectException
     * }
     * ```
     *
     * For this reason we refactored our design and now pass subject as arguments in places where you need it and where it is safe to use it.
     * Following a non-exhaustive list explaining how you need to migrate to use the new design:
     *
     * ```
     * fun Assert<Int>.isEven() = createAndAddAssertion(IS, RawString.create("even")) { subject % 2 == 0 }  // old
     * fun Expect<Int>.isEven() = createAndAddAssertion(IS, RawString.create("even")) { it % 2 == 0 }       // new
     * ```
     * notice the switch from `Assert` to `Expect`
     *
     * ```
     * AssertImpl.builder.createDescriptive(TO_BE, expected) { subject == expected }   // old
     * AssertImpl.builder.createDescriptive(this, TO_BE, expected) { it == expected }  // new
     * ```
     *
     * ```
     * AssertImpl.builder.descriptive.withTest { subject == expected }   // old
     * AssertImpl.builder.descriptive.withTest(this) { it == expected }  // new - `this` refers to Assert or Expect
     * ```
     *
     * ```
     * AssertImpl.builder.descriptive.withTest(...).withFailureHint { ...  subject ... }   // old
     * AssertImpl.builder.descriptive.withTest(...).withFailureHint(this) { ... it ... }  // new - `this` refers to Assert or Expect
     * ```
     *
     * AssertImpl.builder.descriptive.withTest(...).withFailureHint(...).showOnlyIf { ... subject ... }   // old
     * AssertImpl.builder.descriptive.withTest(...).withFailureHint(...).showOnlyIf(this) { ... it ... }  // new - `this` refers to Assert or Expect
     * ```
     *
     * ```
     * AssertImpl.changeSubject(this) { subject.asIterable() }        // old
     * ExpectImpl.changeSubject.unreported(this) { it.asIterable() }  // new
     * ```
     *  notice the switch from `AssertImpl` to `ExpectImpl`
     *
     *
     */
    @Deprecated(
        "Do not access subject as it might break reporting. In contexts where it is safe to access the subject, it is passed by parameter. See KDoc for migration hints",
        ReplaceWith("it")
    )
    val subject: T

    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject change could not be
     * carried out.
     */
    val maybeSubject: Option<T>
}
