@file:JvmName("DeprecatedCollectionAssertions")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

@Deprecated("use CollectionAssertions.hasSize instead, will be removed with 1.0.0", ReplaceWith("CollectionAssertions.hasSize(plant, size)"))
fun <T : Collection<*>> _hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    = CollectionAssertions.hasSize(plant, size)

@Deprecated("use CollectionAssertions.isEmpty instead, will be removed with 1.0.0", ReplaceWith("CollectionAssertions.isEmpty(plant)"))
fun <T : Collection<*>> _isEmpty(plant: AssertionPlant<T>): Assertion
    = CollectionAssertions.isEmpty(plant)

@Deprecated("use CollectionAssertions.isNotEmpty instead, will be removed with 1.0.0", ReplaceWith("CollectionAssertions.isNotEmpty(plant)"))
fun <T : Collection<*>> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = CollectionAssertions.isNotEmpty(plant)
