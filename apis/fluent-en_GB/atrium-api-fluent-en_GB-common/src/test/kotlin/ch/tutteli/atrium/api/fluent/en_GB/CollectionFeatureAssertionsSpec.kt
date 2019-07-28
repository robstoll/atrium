package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.property

class CollectionFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionFeatureAssertionsSpec(
    AssertionVerbFactory,
    property<Collection<String>, Int>(Expect<Collection<String>>::size),
    fun1<Collection<String>, Expect<Int>.() -> Unit>(Expect<Collection<String>>::size)
)
