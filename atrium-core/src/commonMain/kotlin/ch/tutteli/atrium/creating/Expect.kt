package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.proofs.Proof

/**
 * The internal type which we work with which combines [Expect], [ProofContainer] (deprecated [AssertionContainer])
 * and [ExpectGrouping].
 *
 * In theory, [Expect] should extend [ExpectGrouping] but due to Kotlin type inference/overload resolution bugs, we have
 * to split it. One benefit of it, we can define extensions for [ExpectGrouping] which are not visible for [Expect].
 *
 * Similarly, we separate [Expect] from [ProofContainer] so that we can provide extension functions for
 * [ProofContainer] which are more or less identical to the ones defined for api-fluent but don't return an [Expect]
 * but [Proof] etc.
 *
 * Also, we separate [Expect] from [ProofContainer] since a lot of functionality defined for ProofContainer is
 * not relevant for newcomers to Atrium (see [https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas](https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas)
 * for more information about the personas).
 */
//TODO remove suppress once we remove AssertionContainer, with 2.0.0 at the latest
@Suppress("DEPRECATION")
interface ExpectInternal<SubjectT> : Expect<SubjectT>, AssertionContainer<SubjectT>, ProofContainer<SubjectT>, ExpectGrouping

/**
 * Represents the extension point for expectation functions and sophisticated builders for subjects of type [SubjectT].
 *
 * Following a simple example, see [Write own Expectation Functions](https://github.com/robstoll/atrium?tab=readme-ov-file#write-own-expectation-functions)
 * for more help:
 * ```kotlin
 * val <T: SQLException> Expect<T>.errorCode: FeatureExpect<T, Int>
 *     get() = feature(SQLException::getErrorCode)
 * ```
 *
 * Note, that we have used a type parameter and not `Expect<SqlException>`. This is due to the fact that `Expect`
 * is [invariant](https://kotlinlang.org/docs/reference/generics.html#variance) which means without type parameter
 * something like the following would result in a compile error
 * ```kotlin
 * expect {
 *   ...
 * }.toThrow<BatchUpdateException> {
 *   errorCode.toEqual(23000)
 *   // compile error: None of the following candidates is applicable because of receiver type mismatch:
 * }
 * ```
 * Besides, we would need to return a `FeatureExpect<SQLException, Int>` and would therefore lose the information
 * that the thrown Exception was a `BatchUpdateException` and would first need to use `toBeAnInstanceOf<BatchUpdateException>`
 * if we want to assert something which is specific to `BatchUpdateException`. With the type parameter we don't and
 * could therefore then also write something like the following within the `toThrow` block:
 * ```kotlin
 *   feature(BatchUpdateException::getUpdateCounts).toEqual(intArrayOf(3, 2))
 * ```
 *
 * In general, you should always use the type parameter approach, the only exception is if you deal with final classes
 * (e.g. data classes) which don't have a type parameter itself. In such a case there is no benefit to have a type
 * parameter but on the other hand, it also doesn't hurt -- less to think about
 * (your IDE might warn you that it is not necessary though).
 *
 * As a side notice, we have not defined `Expect` as covariant on purpose (switched to invariant in v0.9.0),
 * because if we did, then things like `expect(1).toEqual(1.0)` would no longer be a compile error.
 * And Atrium's goal is exactly to prevent users from pitfalls like that.
 *
 * @param SubjectT The type of the subject of `this` expectation.
 */
interface Expect<SubjectT>



/**
 * Represents a group of expectations including nested groups of expectations (nested [ExpectGrouping]).
 *
 * It is the extension point for groups of expectations with unrelated subjects.
 *
 * @since 1.1.0
 */
interface ExpectGrouping
