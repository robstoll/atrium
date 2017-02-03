package ch.tutteli.assertk

import org.jetbrains.spek.api.dsl.Pending
import org.jetbrains.spek.api.dsl.SpecBody
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
