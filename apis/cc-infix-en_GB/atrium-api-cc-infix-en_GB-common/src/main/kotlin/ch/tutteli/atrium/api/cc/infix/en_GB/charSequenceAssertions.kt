// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Blank
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * a sophisticated `contains` assertions.
 *
 * @param contain Has to be `contain`.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains(ch.tutteli.atrium.api.infix.en_GB.o)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.contains",
        "ch.tutteli.atrium.api.infix.en_GB.o"
    )
)
infix fun <T : CharSequence> Assert<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    = AssertImpl.charSequence.containsBuilder(this)

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsNot(ch.tutteli.atrium.api.infix.en_GB.o)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.containsNot",
        "ch.tutteli.atrium.api.infix.en_GB.o"
    )
)
infix fun <T : CharSequence> Assert<T>.notTo(@Suppress("UNUSED_PARAMETER") contain: contain): NotCheckerOption<T, NotSearchBehaviour>
    = NotCheckerOptionImpl(AssertImpl.charSequence.containsNotBuilder(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains the [toString] representation of the given [expected]
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 value expected`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.contains"
    )
)
infix fun <T : CharSequence> Assert<T>.contains(expected: Any): AssertionPlant<T>
    = this contains Values(expected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains the given [values] [toString] representation
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 the Values(...)`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [Values.expected]
 * is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `contains` to create a more sophisticated `contains`
 * assertion where you can use options such as [atLeast], [atMost] and [exactly] to control the number of occurrences
 * you expect.
 *
 * Meaning you might want to use:
 *   `to contain exactly 2 the value 'a'`
 * instead of:
 *   `contains Values('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains(ch.tutteli.atrium.api.infix.en_GB.values(values.expected, *values.otherExpected)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.contains",
        "ch.tutteli.atrium.api.infix.en_GB.values"
    )
)
infix fun <T : CharSequence> Assert<T>.contains(values: Values<Any>): AssertionPlant<T>
    = this to contain atLeast 1 the values


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains a sequence which matches the given [pattern].
 *
 * It is a shortcut for `to contain atLeast 1 regex pattern`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsRegex(pattern).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.containsRegex"
    )
)
infix fun <T : CharSequence> Assert<T>.containsRegex(pattern: String): AssertionPlant<T>
    = this contains RegexPatterns(pattern)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains a sequence which matches the given [patterns]
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 the RegexPatterns(...)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and
 * [RegexPatterns.expected] is defined as `'a(b)?'` and one of the [RegexPatterns.otherExpected] is defined as
 * `'a(b)?'` as well, then both match, even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain exactly 2 the regex 'a(b)?'`
 * instead of:
 *   `to contain atLeast 1 the RegexPatterns('a(b)?', 'a(b)?')`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains(patterns).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.contains"
    )
)
infix fun <T : CharSequence> Assert<T>.contains(patterns: RegexPatterns): AssertionPlant<T>
    = this to contain atLeast 1 the patterns

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not [expected]'s [toString] representation.
 *
 * Delegates to [containsNot] [Values].
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsNot(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.containsNot"
    )
)
infix fun <T : CharSequence> Assert<T>.containsNot(expected: Any)
    = this containsNot Values(expected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain the [toString] representation
 * of the given [values].
 *
 * It is a shortcut for `notTo contain the Values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsNot(ch.tutteli.atrium.api.infix.en_GB.values(values.expected, *values.otherExpected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.containsNot",
        "ch.tutteli.atrium.api.infix.en_GB.values"
    )
)
infix fun <T : CharSequence> Assert<T>.containsNot(values: Values<Any>)
    = this notTo contain the values

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().startsWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.startsWith"
    )
)
infix fun <T : CharSequence> Assert<T>.startsWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsWith(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().startsNotWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.startsNotWith"
    )
)
infix fun <T : CharSequence> Assert<T>.startsNotWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsNotWith(this, expected))


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().endsWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.endsWith"
    )
)
infix fun <T : CharSequence> Assert<T>.endsWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsWith(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().endsNotWith(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.endsNotWith"
    )
)
infix fun <T : CharSequence> Assert<T>.endsNotWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsNotWith(this, expected))


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @param Empty Has to be `Empty`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().toBe(empty).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.toBe"
    )
)
infix fun <T : CharSequence> Assert<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(AssertImpl.charSequence.isEmpty(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @param Empty Has to be `Empty`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().notToBe(empty).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.notToBe"
    )
)
infix fun <T : CharSequence> Assert<T>.notToBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(AssertImpl.charSequence.isNotEmpty(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] [CharSequence].[kotlin.text.isNotBlank].
 *
 * @param Blank Has to be `Blank`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().notToBe(blank).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.notToBe"
    )
)
infix fun <T : CharSequence> Assert<T>.notToBe(@Suppress("UNUSED_PARAMETER") Blank: Blank)
    = addAssertion(AssertImpl.charSequence.isNotBlank(this))
