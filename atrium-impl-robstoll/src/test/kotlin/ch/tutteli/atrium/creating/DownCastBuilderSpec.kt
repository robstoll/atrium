package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory

object DownCastBuilderSpec : ch.tutteli.atrium.spec.creating.DownCastBuilderSpec(
    AssertionVerbFactory,
    { description, subClass, commonFields ->
        DownCastBuilder(description, subClass, commonFields)
    })
