
package ch.tutteli.atrium.spec

import org.jetbrains.spek.api.dsl.TestBody
import org.jetbrains.spek.api.lifecycle.TestScope
import org.jetbrains.spek.engine.Scope
import org.jetbrains.spek.engine.SpekExecutionContext
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.support.hierarchical.Node

/**
 * Represents a [TestScope] which accepts [AssertionError] but fails for any other exception.
 */
class ForgivingTest(private val test: Test) :
    Scope.Group(test.uniqueId, test.pending, test.source, test.lifecycleManager)
    , Node<SpekExecutionContext> by test
    , TestScope by test {

    override val parent = test.parent
    override fun getType() = TestDescriptor.Type.TEST

    override fun execute(
        context: SpekExecutionContext,
        dynamicTestExecutor: Node.DynamicTestExecutor?
    ): SpekExecutionContext {
        try {
            test.body.invoke(object : TestBody {})
        } catch (e: AssertionError) {
            println("forgiving $test")
        } catch (@Suppress("DEPRECATION") e: ch.tutteli.atrium.creating.PlantHasNoSubjectException){
            println("forgiving $test")
        } catch(e: IllegalArgumentException){
            println("forgiving $test")
        }
        return context
    }
}
