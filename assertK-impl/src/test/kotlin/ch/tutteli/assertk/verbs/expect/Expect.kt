package ch.tutteli.assertk.verbs.expect

import ch.tutteli.assertk.creating.*

fun expect(act: () -> Unit): ThrowableFluent
    = AssertionPlantFactory.throwableFluent("expect the thrown exception", act)
