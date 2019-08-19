@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.dsl.SpecBody

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterablePredicateSpecBase(verbs: AssertionVerbFactory, spec: Spec.() -> Unit) : IterableContainsEntriesSpecBase(verbs, spec) {
    companion object {

        fun SpecBody.nonNullableCases(
            describePrefix: String,
            containsPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit) -> Assert<Iterable<Double>>>,
            containsNullablePair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?) -> Assert<Iterable<Double?>>>,
            action: SpecBody.(Assert<Iterable<Double>>.(Assert<Double>.() -> Unit) -> Any) -> Unit
        ) {
            group("$describePrefix describe non-nullable cases") {
                mapOf<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit) -> Any>(
                    containsPair.first to { a -> containsPair.second(this, a) },
                    "${containsNullablePair.first} for nullable" to { a -> containsNullablePair.second(this, a) }
                ).forEach { (describe, containsEntriesFunArr) ->
                    describeFun(describe) {
                        action(containsEntriesFunArr)
                    }
                }
            }
        }
    }
}
