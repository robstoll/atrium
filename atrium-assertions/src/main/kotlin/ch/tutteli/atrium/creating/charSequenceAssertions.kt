@file:JvmName("CharSequenceAnyAssertions")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

@Deprecated("use CharSequenceAssertions.containsBuilder instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.containsBuilder(plant)"))
fun <T : CharSequence> _containsBuilder(plant: AssertionPlant<T>)
    = CharSequenceAssertions.containsBuilder(plant)

@Deprecated("use CharSequenceAssertions.containsNotBuilder instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.containsNotBuilder(plant)"))
fun <T : CharSequence> _containsNotBuilder(plant: AssertionPlant<T>)
    = CharSequenceAssertions.containsNotBuilder(plant)


@Deprecated("use CharSequenceAssertions.startsWith instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.startsWith(plant, expected)"))
fun <T : CharSequence> _startsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = CharSequenceAssertions.startsWith(plant, expected)

@Deprecated("use CharSequenceAssertions.startsNotWith instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.startsNotWith(plant, expected)"))
fun <T : CharSequence> _startsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = CharSequenceAssertions.startsNotWith(plant, expected)

@Deprecated("use CharSequenceAssertions.endsWith instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.endsWith(plant, expected)"))
fun <T : CharSequence> _endsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = CharSequenceAssertions.endsWith(plant, expected)

@Deprecated("use CharSequenceAssertions.endsNotWith instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.endsNotWith(plant, expected)"))
fun <T : CharSequence> _endsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    = CharSequenceAssertions.endsNotWith(plant, expected)

@Deprecated("use CharSequenceAssertions.isEmpty instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.isEmpty(plant)"))
fun <T : CharSequence> _isEmpty(plant: AssertionPlant<T>): Assertion
    = CharSequenceAssertions.isEmpty(plant)

@Deprecated("use CharSequenceAssertions.isNotEmpty instead, will be removed with 1.0.0", ReplaceWith("CharSequenceAssertions.isNotEmpty(plant)"))
fun <T : CharSequence> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = CharSequenceAssertions.isNotEmpty(plant)
