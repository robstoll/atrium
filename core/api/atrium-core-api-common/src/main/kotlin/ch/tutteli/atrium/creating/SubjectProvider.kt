package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some

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
     * For this reason we refactored our design and now pass subject as arguments in places where you need it and
     * where it is safe to use it.
     *
     * It's best if you switch entirely from [Assert] to [Expect] and from `AssertImpl` to `ExpectImpl`.
     * Have a look at the migration guide given in [https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration](https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration)
     * for a fast way to migrate everything.
     *
     * In case you want to migrate part by part then the following, non-exhaustive list, tries to explain
     * how you need to migrate to use the new design:
     *
     * 1.
     *      ```
     *      fun Assert<Int>.isEven() = createAndAddAssertion(IS, RawString.create("even")) { subject % 2 == 0 }  // old
     *      fun Expect<Int>.isEven() = createAndAddAssertion(IS, RawString.create("even")) { it % 2 == 0 }       // new
     *      ```
     *      notice the switch from `Assert` to `Expect`
     *
     * 2.
     *      ```
     *      AssertImpl.builder.createDescriptive(TO_BE, expected) { subject == expected }   // old
     *      AssertImpl.builder.createDescriptive(this, TO_BE, expected) { it == expected }  // new
     *      ```
     *
     * 3.
     *      ```
     *      AssertImpl.builder.descriptive.withTest { subject == expected }   // old
     *      AssertImpl.builder.descriptive.withTest(this) { it == expected }  // new - `this` refers to Assert or Expect
     *      ```
     *
     * 4.
     *      ```
     *      AssertImpl.builder.descriptive.withTest(...).withFailureHint { ...  subject ... }   // old
     *      AssertImpl.builder.descriptive.withTest(...).withFailureHint(this) { ... it ... }  // new - `this` refers to Assert or Expect
     *      ```
     *
     * 5.
     *      ```
     *      AssertImpl.builder.descriptive.withTest(...).withFailureHint(...).showOnlyIf { ... subject ... }   // old
     *      AssertImpl.builder.descriptive.withTest(...).withFailureHint(...).showOnlyIf(this) { ... it ... }  // new - `this` refers to Assert or Expect
     *      ```
     *
     * 6.
     *      ```
     *      AssertImpl.changeSubject(this) { subject.asIterable() }        // old
     *      ExpectImpl.changeSubject(this).unreported { it.asIterable() }  // new
     *      ```
     *      notice the switch from `AssertImpl` to `ExpectImpl`
     *
     * 7. Feature assertions
     *      Those are not straight forward and require that you migrate to Expect first or that you use `asExpect`
     *      ```
     *      import ch.tutteli.atrium.verbs.expect
     *      expect(person) {
     *        property(subject::firstName).startsWith("hello")
     *        returnValueOf(subject::myFun, "arg1").toBe(42)
     *      } and {
     *        property(subject::lastName).toBe("world")
     *      }
     *
     *      // using asExpect
     *
     *      expect(person).asExpect { // everything within the brackets is in the Expect world
     *        feature { f(it::firstName) }.startsWith("hello")
     *        feature { f(it::myFun, "arg1") }.toBe(42)
     *      }    // back to the Assert world
     *      .and {
     *        asExpect() // switch to Expect world, all subsequent calls in Expect world
     *          .feature{ f(subject::lastName) }
     *          .toBe("world")
     *      }
     *      ```
     *      Have a look at the migration guides if you want to switch entirely to Expect as there is a fast
     *      search & replace strategy.
     *
     * 8. nested `expect`, `assert `or `assertThat`
     *    ```
     *    //old
     *    expect(1 to 2) {
     *      expect(subject.second).toBe(2)
     *    }
     *
     *    //new
     *    expect(1 to 2) {
     *      feature { f(it::second) }.toBe(2)
     *    }
     *    ```
     *    same same but different for `assert` and `assertThat`
     */
    @Deprecated(
        "Switch from Assert to Expect and do no longer access subject as it might break reporting. In contexts where it is safe to access the subject, it is passed by parameter and can be accessed via `it`. See KDoc for migration hints; will be removed with 1.0.0",
        ReplaceWith("it")
    )
    val subject: T

    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject change could not be
     * carried out.
     */
    val maybeSubject: Option<T>
}
