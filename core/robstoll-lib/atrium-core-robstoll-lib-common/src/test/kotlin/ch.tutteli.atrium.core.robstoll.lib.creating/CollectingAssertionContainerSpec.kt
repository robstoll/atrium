package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory

object CollectingAssertionContainerSpec : ch.tutteli.atrium.specs.creating.CollectingAssertionContainerSpec(
    AssertionVerbFactory, ::CollectingAssertionContainerImpl
)
