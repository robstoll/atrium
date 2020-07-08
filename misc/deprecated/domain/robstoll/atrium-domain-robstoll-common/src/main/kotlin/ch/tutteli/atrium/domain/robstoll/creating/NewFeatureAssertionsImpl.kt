//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.NewFeatureAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._genericFeature

@Deprecated("Will be removed with 1.0.0")
class NewFeatureAssertionsImpl : NewFeatureAssertions {

    override fun <T, R> genericFeature(expect: Expect<T>, metaFeature: MetaFeature<R>) =
        _genericFeature(expect, metaFeature)
}
