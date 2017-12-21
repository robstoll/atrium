package ch.tutteli.atrium.spec

import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.dsl.ActionBody
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.TestBody
import org.jetbrains.spek.api.dsl.TestContainer

fun SpecBody.setUp(description: String, body: SpecBody.() -> Unit)
    = group(description, body = body)

fun SpecBody.inCaseOf(description: String, body: ActionBody.() -> Unit)
    = action("in case of $description", body = body)

fun TestContainer.check(description: String, body: TestBody.() -> Unit)
    = test(description, body = body)

fun <T : Any> SpecBody.checkNarrowingAssertion(description: String,
                                               act: (AssertionPlant<T>.() -> Unit) -> Unit,
                                               lazy: (AssertionPlant<T>.() -> Unit),
                                               vararg otherMethods: Pair<String, (AssertionPlant<T>.() -> Unit)>) {
    checkGenericNarrowingAssertion(description, act, lazy, *otherMethods)
}

fun <T> SpecBody.checkGenericNarrowingAssertion(
    description: String,
    act: (T.() -> Unit) -> Unit,
    lazy: (T.() -> Unit),
    vararg otherMethods: Pair<String, (T.() -> Unit)>)
    = checkGenericNarrowingAssertion(description, act, "lazy" to lazy, *otherMethods)

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

