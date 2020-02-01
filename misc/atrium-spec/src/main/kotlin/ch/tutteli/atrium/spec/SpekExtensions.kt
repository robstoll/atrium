@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.spec

import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.dsl.ActionBody
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.TestBody
import org.jetbrains.spek.api.dsl.TestContainer

@Deprecated("Switch to atrium-specs and spek2; will be removed with 1.0.0", ReplaceWith("context(description, body)"))
fun SpecBody.setUp(description: String, body: SpecBody.() -> Unit) = group(description, body = body)

@Deprecated("Switch to atrium-specs and spek2; will be removed with 1.0.0", ReplaceWith("context(description, body)"))
fun SpecBody.inCaseOf(description: String, body: ActionBody.() -> Unit) = action("in case of $description", body = body)

@Deprecated("Switch to atrium-specs and spek2; will be removed with 1.0.0", ReplaceWith("it(description, body)"))
fun TestContainer.check(description: String, body: TestBody.() -> Unit) = test(description, body = body)

@Deprecated(
    "Switch to atrium-specs and spek2; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.specs.checkNarrowingAssertion(description, act, lazy, *otherMethods)")
)
fun <T : Any> SpecBody.checkNarrowingAssertion(
    description: String,
    act: (AssertionPlant<T>.() -> Unit) -> Unit,
    lazy: (AssertionPlant<T>.() -> Unit),
    vararg otherMethods: Pair<String, (AssertionPlant<T>.() -> Unit)>
) {
    checkGenericNarrowingAssertion(description, act, lazy, *otherMethods)
}

@Deprecated(
    "Switch to atrium-specs and spek2; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.specs.checkGenericNarrowingAssertion(description, act, lazy, *otherMethods)")
)
fun <T> SpecBody.checkGenericNarrowingAssertion(
    description: String,
    act: (T.() -> Unit) -> Unit,
    lazy: (T.() -> Unit),
    vararg otherMethods: Pair<String, (T.() -> Unit)>
) = checkGenericNarrowingAssertion(description, act, "lazy" to lazy, *otherMethods)

@Deprecated(
    "Switch to atrium-specs and spek2; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.specs.checkGenericNarrowingAssertion(description, act, *methods)")
)
fun <T> SpecBody.checkGenericNarrowingAssertion(
    description: String, act: (T.() -> Unit) -> Unit, vararg methods: Pair<String, (T.() -> Unit)>
) {
    group(description) {
        mapOf(*methods).forEach { (checkMethod, assertion) ->
            test("in case of $checkMethod evaluation") {
                act(assertion)
            }
        }
    }
}

