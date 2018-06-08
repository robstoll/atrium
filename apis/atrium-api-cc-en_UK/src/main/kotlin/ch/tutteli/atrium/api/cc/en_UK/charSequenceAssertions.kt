package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour as DeprecatedNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour as DeprecatedNotSearchBehaviour

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.contains"))
val <T : CharSequence> Assert<T>.contains: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    get() = AssertImpl.charSequence.containsBuilder(this)

@Deprecated("Use the extension fun `contains` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.contains"))
fun <T : CharSequence> getContains(plant: Assert<T>): DeprecatedBuilder<T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, plant.contains.searchBehaviour)


/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.containsNot"))
val <T : CharSequence> Assert<T>.containsNot: NotCheckerOption<T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.charSequence.containsNotBuilder(this))

@Deprecated("Use the extension fun `containsNot` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.containsNot"))
fun <T : CharSequence> getContainsNot(plant:  Assert<T>): DeprecatedNotCheckerBuilder<T, NotSearchBehaviour>
    = DeprecatedNotCheckerBuilder(AssertImpl.charSequence.containsNotBuilder(plant))


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [toString] representation
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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.contains(expected, *otherExpected)"))
fun <T : CharSequence> Assert<T>.contains(expected: Any, vararg otherExpected: Any): AssertionPlant<T>
    = contains.atLeast(1).values(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if given).
 *
 * It is a shortcut for `containsNot.values(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.containsNot(expected, *otherExpected)"))
fun <T : CharSequence> Assert<T>.containsNot(expected: Any, vararg otherExpected: Any)
    = containsNot.values(expected, *otherExpected)


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [getDefault][Translatable.getDefault]
 * representation and the [getDefault][Translatable.getDefault] representations of the [otherExpected] (if given),
 * using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).defaultTranslationOf(expected, *otherExpected)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).defaultTranslationOf(IS)`
 * instead of:
 *   `containsDefaultTranslationOf(IS, IS)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.containsDefaultTranslationOf(expected, *otherExpected)"))
fun <T : CharSequence> Assert<T>.containsDefaultTranslationOf(expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
    = contains.atLeast(1).defaultTranslationOf(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does  not contain [expected]'s
 * [getDefault][Translatable.getDefault] representation and neither one of the [otherExpected]'s
 * [getDefault][Translatable.getDefault] representation (if given).
 *
 * It is a shortcut for `containsNot.defaultTranslationOf(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.containsNotDefaultTranslationOf(expected, *otherExpected)"))
fun <T : CharSequence> Assert<T>.containsNotDefaultTranslationOf(expected: Translatable, vararg otherExpected: Translatable)
    = containsNot.defaultTranslationOf(expected, * otherExpected)


/**
 * Makes the assertion that [AssertionPlant.subject] contains a sequence which matches the given regular expression
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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.containsRegex(expected, *otherExpected)"))
fun <T : CharSequence> Assert<T>.containsRegex(pattern: String, vararg otherPatterns: String): AssertionPlant<T>
    = contains.atLeast(1).regex(pattern, *otherPatterns)

/**
 * Makes the assertion that [AssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.startsWith(expected)"))
fun <T : CharSequence> Assert<T>.startsWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.startsNotWith(expected)"))
fun <T : CharSequence> Assert<T>.startsNotWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.endsWith(expected)"))
fun <T : CharSequence> Assert<T>.endsWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.endsNotWith(expected)"))
fun <T : CharSequence> Assert<T>.endsNotWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.isEmpty()"))
fun <T : CharSequence> Assert<T>.isEmpty()
    = addAssertion(AssertImpl.charSequence.isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.isNotEmpty()"))
fun <T : CharSequence> Assert<T>.isNotEmpty()
    = addAssertion(AssertImpl.charSequence.isNotEmpty(this))
