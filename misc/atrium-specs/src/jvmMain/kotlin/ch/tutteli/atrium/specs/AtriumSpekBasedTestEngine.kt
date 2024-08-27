package ch.tutteli.atrium.specs

import org.junit.platform.engine.*
import org.junit.platform.engine.discovery.ClassSelector
import org.junit.platform.engine.discovery.ClasspathRootSelector
import org.junit.platform.engine.discovery.PackageSelector
import org.spekframework.spek2.junit.JUnitEngineExecutionListenerAdapter
import org.spekframework.spek2.junit.SpekEngineDescriptor
import org.spekframework.spek2.junit.SpekTestDescriptor
import org.spekframework.spek2.junit.SpekTestDescriptorFactory
import org.spekframework.spek2.runtime.JvmDiscoveryContextFactory
import org.spekframework.spek2.runtime.SpekRuntime
import org.spekframework.spek2.runtime.execution.DiscoveryRequest
import org.spekframework.spek2.runtime.execution.ExecutionRequest
import org.spekframework.spek2.runtime.scope.Path
import org.spekframework.spek2.runtime.scope.PathBuilder
import java.lang.reflect.Modifier
import java.nio.file.Paths
import org.junit.platform.engine.ExecutionRequest as JUnitExecutionRequest

class AtriumSpekBasedTestEngine : TestEngine {
    private val spekRuntime by lazy { SpekRuntime() }
    private val descriptorFactory = SpekTestDescriptorFactory()

    override fun getId(): String = "spek2-atrium"

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        val engineDescriptor = SpekEngineDescriptor(uniqueId, id)

        val sourceDirs = discoveryRequest.getSelectorsByType(ClasspathRootSelector::class.java)
            .map { it.classpathRoot }
            .map { Paths.get(it) }
            .map { it.toString() }

        val classSelectors = discoveryRequest.getSelectorsByType(ClassSelector::class.java)
            .filter {
                // get all super classes
                val superClasses = mutableListOf<String>()
                var current = it.javaClass.superclass
                while (current != null) {
                    superClasses.add(current.name)
                    current = current.superclass
                }

                superClasses.contains("org.spekframework.spek2.Spek")
            }
            .filter {
                !(it.javaClass.isAnonymousClass
                    || it.javaClass.isLocalClass
                    || it.javaClass.isSynthetic
                    || Modifier.isAbstract(it.javaClass.modifiers))
            }.map {
                PathBuilder
                    .from(it.javaClass.kotlin)
                    .build()
            }

        val packageSelectors = discoveryRequest.getSelectorsByType(PackageSelector::class.java)
            .map {
                PathBuilder()
                    .appendPackage(it.packageName)
                    .build()
            }

        val filters = linkedSetOf<Path>()

        filters.addAll(classSelectors)
        filters.addAll(packageSelectors)

//        // todo: empty filter should imply root
//        if (filters.isEmpty()) {
//            filters.add(PathBuilder.ROOT)
//        }

        // won't find anything without filter hence can return empty engine descriptor
        if (filters.isEmpty()) {
            return engineDescriptor
        }

        val context = JvmDiscoveryContextFactory.create(sourceDirs)

        val discoveryResult = spekRuntime.discover(DiscoveryRequest(context, filters.toList()))

        discoveryResult.roots
            .map { descriptorFactory.create(it) }
            .forEach(engineDescriptor::addChild)

        return engineDescriptor

    }

    override fun execute(request: JUnitExecutionRequest) {
        try {
            val roots = request.rootTestDescriptor.children
                .filterIsInstance<SpekTestDescriptor>()
                .map(SpekTestDescriptor::scope)

            val executionRequest = ExecutionRequest(
                roots, JUnitEngineExecutionListenerAdapter(request.engineExecutionListener, descriptorFactory)
            )

            spekRuntime.execute(executionRequest)
        } catch (t: Throwable) {
            t.printStackTrace()
            request.fail(t)
        }
    }

    private fun JUnitExecutionRequest.fail(throwable: Throwable) {
        engineExecutionListener.executionFinished(
            rootTestDescriptor,
            TestExecutionResult.failed(throwable)
        )
    }
}

