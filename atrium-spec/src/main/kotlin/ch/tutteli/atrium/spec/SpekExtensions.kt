package ch.tutteli.atrium.spec

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
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
                                               act: (IAssertionPlant<T>.() -> Unit) -> Unit,
                                               immediate: (IAssertionPlant<T>.() -> Unit),
                                               lazy: (IAssertionPlant<T>.() -> Unit),
                                               vararg otherMethods: Pair<String, (IAssertionPlant<T>.() -> Unit)>) {
    checkGenericNarrowingAssertion(description, act, immediate, lazy, *otherMethods)
}

fun <T> SpecBody.checkNarrowingNullableAssertion(description: String,
                                                 act: (IAssertionPlantNullable<T>.() -> Unit) -> Unit,
                                                 immediate: (IAssertionPlantNullable<T>.() -> Unit),
                                                 lazy: (IAssertionPlantNullable<T>.() -> Unit),
                                                 vararg otherMethods: Pair<String, (IAssertionPlantNullable<T>.() -> Unit)>) {
    checkGenericNarrowingAssertion(description, act, immediate, lazy, *otherMethods)
}

fun <T> SpecBody.checkGenericNarrowingAssertion(
    description: String, act: (T.() -> Unit) -> Unit,
    immediate: (T.() -> Unit), lazy: (T.() -> Unit), vararg otherMethods: Pair<String, (T.() -> Unit)>) {

    group(description) {
        mapOf("immediate" to immediate, "lazy" to lazy, *otherMethods).forEach { (checkMethod, assertion) ->
            test("in case of $checkMethod evaluation") {
                act(assertion)
            }
        }
    }
}


