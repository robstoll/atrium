@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().contains",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.contains"
    )
)
val <T : CharSequence> Assert<T>.contains: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    get() = AssertImpl.charSequence.containsBuilder(this)

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().containsNot",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.containsNot"
    )
)
val <T : CharSequence> Assert<T>.containsNot: NotCheckerOption<T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.charSequence.containsNotBuilder(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if given), using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `contains` to create a more sophisticated `contains`
 * assertion where you can use options such as [atLeast], [atMost] and [exactly] to control the number of occurrences
 * you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).value('a')`
 * instead of:
 *   `contains('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().contains(expected, *otherExpected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.contains"
    )
)
fun <T : CharSequence> Assert<T>.contains(expected: Any, vararg otherExpected: Any): AssertionPlant<T> =
    contains.atLeast(1).values(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if given).
 *
 * It is a shortcut for `containsNot.values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().containsNot(expected, *otherExpected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.containsNot"
    )
)
fun <T : CharSequence> Assert<T>.containsNot(expected: Any, vararg otherExpected: Any) =
    containsNot.values(expected, *otherExpected)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains a sequence which matches the given regular expression
 * [pattern] as well as the [otherPatterns] (if given), using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).regex(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).regex('a(b)?')`
 * instead of:
 *   `contains.atLeast(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().containsRegex(pattern, *otherPatterns).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.containsRegex"
    )
)
fun <T : CharSequence> Assert<T>.containsRegex(pattern: String, vararg otherPatterns: String): AssertionPlant<T> =
    contains.atLeast(1).regex(pattern, *otherPatterns)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().startsWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.startsWith"
    )
)
fun <T : CharSequence> Assert<T>.startsWith(expected: CharSequence) =
    addAssertion(AssertImpl.charSequence.startsWith(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().startsNotWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.startsNotWith"
    )
)
fun <T : CharSequence> Assert<T>.startsNotWith(expected: CharSequence) =
    addAssertion(AssertImpl.charSequence.startsNotWith(this, expected))


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().endsWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.endsWith"
    )
)
fun <T : CharSequence> Assert<T>.endsWith(expected: CharSequence) =
    addAssertion(AssertImpl.charSequence.endsWith(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().endsNotWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.endsNotWith"
    )
)
fun <T : CharSequence> Assert<T>.endsNotWith(expected: CharSequence) =
    addAssertion(AssertImpl.charSequence.endsNotWith(this, expected))


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isEmpty().asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.isEmpty"
    )
)
fun <T : CharSequence> Assert<T>.isEmpty() = addAssertion(AssertImpl.charSequence.isEmpty(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isNotEmpty().asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.isNotEmpty"
    )
)
fun <T : CharSequence> Assert<T>.isNotEmpty() = addAssertion(AssertImpl.charSequence.isNotEmpty(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] [CharSequence].[kotlin.text.isNotBlank].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isNotBlank().asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.isNotBlank"
    )
)
fun <T : CharSequence> Assert<T>.isNotBlank() = addAssertion(AssertImpl.charSequence.isNotBlank(this))
