package readme.examples

import ch.tutteli.niok.absolutePathAsString
import ch.tutteli.niok.exists
import ch.tutteli.niok.readText
import ch.tutteli.niok.writeText
import org.junit.jupiter.api.Test
import org.junit.jupiter.engine.config.DefaultJupiterConfiguration
import org.junit.jupiter.engine.descriptor.ClassTestDescriptor
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor
import org.junit.platform.engine.*
import org.junit.platform.engine.support.descriptor.EngineDescriptor
import org.junit.platform.engine.support.descriptor.MethodSource
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.nio.file.Paths
import java.util.Locale

class JUnitReadmeTestEngine: TestEngine {
    private val javaClasses = listOf( // TODO scan at discovery
        Between1Spec::class.java,
        DataDrivenSpec::class.java,
        FaqSpec::class.java,
        FeatureExtractorSpec::class.java,
        FirstExampleSpec::class.java,
        MostExamplesSpec::class.java,
        OwnExpectationFunctionsSpec::class.java,
        OwnExpectationVerbSpec::class.java,
        PathSpec::class.java,
        ThirdPartySpec::class.java,
    )

    private val examples = mutableMapOf<String, String>()
    private val code = HashSet<String>()
    private val snippets = mutableMapOf<String, String>()

    override fun getId(): String = "junit5-readme"

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        val engineDescriptor = EngineDescriptor(uniqueId, id)
        val configuration = DefaultJupiterConfiguration(discoveryRequest.configurationParameters)

        javaClasses.forEach { javaClass ->
            val classTestDescriptor = ClassTestDescriptor(
                uniqueId.append("class", javaClass.name),
                javaClass,
                configuration
            )
            engineDescriptor.addChild(classTestDescriptor)

            val methods = javaClass.methods.filter { method: Method ->
                method.isAnnotationPresent(Test::class.java) // @Test
                    && Modifier.isPublic(method.modifiers) // public
            }
            methods.forEach { method: Method ->
                val testMethodDescriptor = TestMethodTestDescriptor(
                    classTestDescriptor.uniqueId.append("method", method.name),
                    javaClass,
                    method,
                    configuration
                )
                classTestDescriptor.addChild(testMethodDescriptor)
            }
        }

        return engineDescriptor
    }

    override fun execute(request: ExecutionRequest) {
        val default = Locale.getDefault()
        try {
            Locale.setDefault(Locale.UK)

            runJUnitWithCustomListener(request) //NOTE: build examples and code

            val classes: List<String> = javaClasses.map { it.name.replace(".", "/") }
            processExamples(classes, request)
        } catch (t: Throwable) {
            t.printStackTrace()
            Locale.setDefault(default)
        }
    }

    private fun processExamples(classes: List<String>, request: ExecutionRequest){
        val specContents = classes.map { qualifiedClass ->
            qualifiedClass to fileContent("src/main/kotlin/$qualifiedClass.kt", request)
        }
        specContents.forEach { (_, specContent) ->
            extractSnippets(specContent, request)
        }

        var readmeContent = fileContent(readmeStringPath, request)

        if (examples.isEmpty()) {
            request.fail("no examples found")
            return
        }
        if (code.isEmpty()) {
            request.fail("no code found")
            return
        }
        if (snippets.isEmpty()) {
            request.fail("no snippets found")
            return
        }

        examples.forEach { (exampleId, output) ->
            readmeContent = updateExampleLikeInReadme(readmeContent, specContents, exampleId, output, request)
        }
        code.forEach { codeId ->
            readmeContent = updateCodeInReadme(readmeContent, specContents, codeId, request)
        }
        snippets.forEach { (snippetId, snippetContent) ->
            readmeContent = updateSnippetInReadme(readmeContent, snippetId, snippetContent, request)
        }
        Paths.get(readmeStringPath).writeText(readmeContent)
    }

    private fun extractSnippets(specContent: String, request: ExecutionRequest) {
        Regex("//(snippet-.+)-start([\\S\\s]*?)//(snippet-.+)-end").findAll(specContent).forEach {
            val (tag, content, endTag) = it.destructured
            request.failIf(tag != endTag) { "tag $tag-start did not end with $tag-end but with $endTag" }
            snippets[tag] = content.trimIndent()
        }
    }

    private fun runJUnitWithCustomListener(request: ExecutionRequest) {
        val executionListener = ReadmeExecutionListener(
            request.engineExecutionListener,
            examples,
            code
        )

        executionListener.execute(request.rootTestDescriptor)
    }

    private fun fileContent(path: String, request: ExecutionRequest): String {
        val file = Paths.get(path)
        request.failIf(!file.exists) { "could not find ${file.absolutePathAsString}" }
        return file.readText()
    }

    private inline fun ExecutionRequest.failIf(predicate: Boolean, errorMessage: () -> String) {
        if (predicate) fail(errorMessage())
    }

    private fun ExecutionRequest.fail(errorMessage: String) = fail(IllegalStateException(errorMessage))
    private fun ExecutionRequest.fail(throwable: Throwable) {
        engineExecutionListener.executionFinished(
            rootTestDescriptor,
            TestExecutionResult.failed(throwable)
        )
    }

    private fun updateExampleLikeInReadme(
        readmeContent: String,
        specContents: List<Pair<String, String>>,
        exampleId: String,
        output: String,
        request: ExecutionRequest
    ): String = when {
        exampleId.startsWith("ex-") ->
            updateExampleInReadme(readmeContent, specContents, exampleId, output, request)
        exampleId.startsWith("exs-") ->
            updateSplitExampleInReadme(readmeContent, specContents, exampleId, output, request)
        else -> {
            request.fail("unknown example kind $exampleId")
            readmeContent
        }
    }

    private fun updateExampleInReadme(
        readmeContent: String,
        specContents: List<Pair<String, String>>,
        exampleId: String,
        output: String,
        request: ExecutionRequest
    ): String = updateTagInReadme(readmeContent, specContents, exampleId, request, "example") { qualifiedClass, lineNumber, sourceCode ->
        """```kotlin
        |$sourceCode
        |```
        |↑ <sub>[Example](https://github.com/robstoll/atrium/${System.getenv("README_SOURCETREE")}/misc/tools/readme-examples/src/main/kotlin/$qualifiedClass.kt#L$lineNumber)</sub> ↓ <sub>[Output](#$exampleId)</sub>
        |<a name="$exampleId"></a>
        |```text
        |$output
        |```
        """.trimMargin()
    }


    private fun updateSplitExampleInReadme(
        readmeContent: String,
        specContents: List<Pair<String, String>>,
        exampleId: String,
        output: String,
        request: ExecutionRequest
    ): String {
        val updatedReadme = updateTagInReadme(
            readmeContent, specContents, exampleId, request, "ex-code"
        ) { _, _, sourceCode ->
            """```kotlin
            |$sourceCode
            |```""".trimMargin()
        }

        return updateTagInReadme(updatedReadme, "$exampleId-output", request, "exs-output") {
            """```text
            |$output
            |```""".trimMargin()
        }
    }

    private fun updateTagInReadme(
        readmeContent: String,
        specContents: List<Pair<String, String>>,
        tag: String,
        request: ExecutionRequest,
        kind: String,
        content: (String, Int, String) -> String
    ): String = updateTagInReadme(readmeContent, tag, request, kind) {
        val (qualifiedClass, lineNumber, sourceCode) = extractSourceCode(specContents, tag, request)
        content(qualifiedClass, lineNumber, sourceCode)
    }

    private fun updateTagInReadme(
        readmeContent: String,
        tag: String,
        request: ExecutionRequest,
        kind: String,
        contentProvider: () -> String
    ): String {
        val tagRegex = Regex("( *)<$tag>[\\S\\s]*</$tag>")
        return if (!tagRegex.containsMatchIn(readmeContent)) {
            request.fail("$kind tag <$tag> not found in $readmeStringPath")
            readmeContent
        } else {
            tagRegex.replace(readmeContent) {
                """<$tag>
                |
                |${contentProvider()}
                |</$tag>
                """.trimMargin().prependIndent(it.destructured.component1())
            }
        }
    }

    private fun extractSourceCode(
        specContents: List<Pair<String, String>>,
        testId: String,
        request: ExecutionRequest
    ): Triple<String, Int, String> {
        var lineNumber: Int? = null
        val sb = StringBuilder()

        specContents.forEach { (qualifiedClass, specContent) ->
            specContent.lineSequence().forEachIndexed { index, line ->
                if (lineNumber != null) {
                    if (line.startsWith("    }")) return Triple(qualifiedClass, lineNumber!!, sb.toString().trimIndent())
                    sb.append(line).append("\n")

                } else if (line.trim().startsWith("fun `$testId`")) {
                    lineNumber = index + 1
                }
            }
        }
        request.fail("cannot find source code for $testId")
        return Triple("", -1, "")
    }

    private fun updateCodeInReadme(
        readmeContent: String,
        specContents: List<Pair<String, String>>,
        snippetId: String,
        request: ExecutionRequest
    ): String = updateTagInReadme(readmeContent, specContents, snippetId, request, "code") { _, _, sourceCode ->

        """```kotlin
        |$sourceCode
        |```
        """.trimMargin()
    }


    private fun updateSnippetInReadme(
        readmeContent: String,
        snippetId: String,
        snippetContent: String,
        request: ExecutionRequest
    ): String {

        val snippet = Regex("//$snippetId-insert")
        return if (!snippet.containsMatchIn(readmeContent)) {
            request.fail("$snippetId is never inserted in $readmeStringPath")
            readmeContent
        } else {
            snippet.replace(readmeContent) { snippetContent }
        }
    }

    companion object {
        private const val readmeStringPath = "../../../README.md"
    }

    private class ReadmeExecutionListener(
        private val listener: EngineExecutionListener,
        private val examples: MutableMap<String, String>,
        private val code: MutableSet<String>,
    ) {
        fun execute(testDescriptor: TestDescriptor) {
            if(testDescriptor.isTest) return invokeTestMethod(listener, testDescriptor)

            if(testDescriptor.isContainer) {
                listener.executionStarted(testDescriptor)

                try {
                    testDescriptor.children.forEach { child: TestDescriptor ->
                        execute(child)
                    }
                } catch (t: Throwable) {
                    return listener.executionFinished(testDescriptor, TestExecutionResult.failed(t))
                }

                return listener.executionFinished(testDescriptor, TestExecutionResult.successful())
            }

            throw IllegalArgumentException("testDescriptor is not a test nor container: ${testDescriptor.displayName}")
        }

        private fun invokeTestMethod(listener: EngineExecutionListener, testDescriptor: TestDescriptor) {
            listener.executionStarted(testDescriptor)

            val method = testDescriptor.source.get() as MethodSource
            val instance: Any
            try {
                instance = method.javaClass.getDeclaredConstructor().newInstance()
            } catch (t: Throwable) {
                t.printStackTrace()
                return listener.executionFinished(testDescriptor, TestExecutionResult.failed(t))
            }

            try {
                method.javaMethod.invoke(instance)
                return handleSuccess(testDescriptor)
            } catch (t: Throwable) {
                return handleFailure(testDescriptor, t)
            }
        }

        private fun handleSuccess(testDescriptor: TestDescriptor) {
            val testName: String = testDescriptor.simpleName

            if (!testName.startsWith("code")) {
                listener.executionFinished(
                    testDescriptor,
                    TestExecutionResult.failed(IllegalStateException("example tests are supposed to fail"))
                )
                return
            }
            if (code.contains(testName)) {
                listener.executionFinished(
                    testDescriptor,
                    TestExecutionResult.failed(IllegalStateException("code $testName is at least defined twice"))
                )
                return
            }

            code.add(testName)
            listener.executionFinished(testDescriptor, TestExecutionResult.successful())
        }

        private fun handleFailure(testDescriptor: TestDescriptor, thrown: Throwable) {
            val testName: String = testDescriptor.simpleName

            if (!testName.startsWith("ex")) {
                listener.executionFinished(
                    testDescriptor,
                    TestExecutionResult.failed(
                        IllegalStateException(
                            "only example tests are supposed to fail, not $testName",
                            thrown.cause
                        )
                    )
                )
                return
            }
            when (thrown.cause) {
                is AssertionError -> {
                    val resultCauseMessage = thrown.cause!!.message!!
                    examples[testName] = resultCauseMessage
                    listener.executionFinished(testDescriptor, TestExecutionResult.successful())
                }
                else -> listener.executionFinished(testDescriptor, TestExecutionResult.failed(thrown))
            }
        }

        private val TestDescriptor.simpleName: String
            get() {
                return displayName.replace("()", "")
            }
    }
}
