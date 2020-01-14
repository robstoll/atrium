@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)

package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 0.10.0 - no need to migrate to Spek2
object CollectingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.CollectingAssertionPlantSpec(
    AssertionVerbFactory, ::CollectingAssertionPlantImpl
)
