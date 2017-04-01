package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import org.jetbrains.spek.api.dsl.*
import org.jetbrains.spek.engine.Scope
import org.jetbrains.spek.engine.SpekTestEngine
import org.jetbrains.spek.engine.lifecycle.LifecycleManager
import org.junit.platform.engine.UniqueId

//TODO delete hack as soon as https://github.com/JetBrains/spek/pull/176 is fixed and available in release
private fun SpecBody.failSafeGroup(description: String, pending: Pending = Pending.No, body: SpecBody.() -> Unit) {
    try {
        val lifecycleManager = LifecycleManager()
        val group = Scope.Group(
            UniqueId.forEngine("tutteli-test"),
            pending, SpekTestEngine.getSource(), lifecycleManager
        )
        body.invoke(SpekTestEngine.Collector(group, lifecycleManager))
        //no exception occurred, we can call the real group method
        group(description, pending, body)
    } catch(t: Throwable) {
        group(description, pending, body = {
            test("!! unexpected Exception occurred !!") {
                throw t
            }
        })
    }
}

//TODO delete hack as soon as https://github.com/JetBrains/spek/pull/176 is fixed and available in release
fun SpecBody.describe(description: String, body: SpecBody.() -> Unit)
    = failSafeGroup("describe $description", body = body)

//TODO delete hack as soon as https://github.com/JetBrains/spek/pull/176 is fixed and available in release
fun SpecBody.context(description: String, body: SpecBody.() -> Unit)
    = failSafeGroup("context $description", body = body)

//TODO rewrite to use group as soon as https://github.com/JetBrains/spek/pull/176 is fixed and available in release
fun SpecBody.setUp(description: String, body: SpecBody.() -> Unit)
    = failSafeGroup(description, body = body)

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

internal fun <T> SpecBody.checkGenericNarrowingAssertion(
    description: String, act: (T.() -> Unit) -> Unit,
    immediate: (T.() -> Unit), lazy: (T.() -> Unit), vararg otherMethods: Pair<String, (T.() -> Unit)>) {

    group(description) {
        mapOf("immediate" to immediate, "lazy" to lazy, *otherMethods).forEach { checkMethod, assertion ->
            check("in case of $checkMethod evaluation") {
                act(assertion)
            }
        }
    }
}


