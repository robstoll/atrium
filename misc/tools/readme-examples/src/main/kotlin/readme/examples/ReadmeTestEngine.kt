package readme.examples

import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.niok.absolutePathAsString
import ch.tutteli.niok.exists
import ch.tutteli.niok.readText
import ch.tutteli.niok.writeText
import org.junit.platform.engine.*
import org.spekframework.spek2.junit.JUnitEngineExecutionListenerAdapter
import org.spekframework.spek2.junit.SpekTestDescriptor
import org.spekframework.spek2.junit.SpekTestDescriptorFactory
import org.spekframework.spek2.junit.SpekTestEngine
import org.spekframework.spek2.runtime.SpekRuntime
import org.spekframework.spek2.runtime.execution.ExecutionRequest
import java.nio.file.Paths
import java.util.Locale
import org.junit.platform.engine.ExecutionRequest as JUnitExecutionRequest

class ReadmeTestEngine : TestEngine {
    private val spek = SpekTestEngine()

    private val examples = mutableMapOf<String, String>()
    private val code = HashSet<String>()
    private val snippets = mutableMapOf<String, String>()

    override fun getId(): String = "spek2-readme"

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        val descriptor = spek.discover(discoveryRequest, uniqueId)
        require(descriptor.children.isNotEmpty()) {
            "Could not find any specification, check your runtime classpath"
        }
        return descriptor
    }

    override fun execute(request: JUnitExecutionRequest) {
        val default = Locale.getDefault()
        try {
            Locale.setDefault(Locale.UK)

            ReporterFactory.specifyFactory(ReadmeReporterFactory.ID)

            runSpekWithCustomListener(request)

            processExamples(request)
        } catch (t: Throwable) {
            t.printStackTrace()
            Locale.setDefault(default)

            request.fail(t)
        }
    }

    private fun processExamples(request: JUnitExecutionRequest) {
        val specContent = fileContent("src/main/kotlin/readme/examples/ReadmeSpec.kt", request)
        extractSnippets(specContent, request)

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
            readmeContent = updateExampleLikeInReadme(readmeContent, specContent, exampleId, output, request)
        }
        code.forEach { codeId ->
            readmeContent = updateCodeInReadme(readmeContent, specContent, codeId, request)
        }
        snippets.forEach { snippetId, snippetContent ->
            readmeContent = updateSnippetInReadme(readmeContent, snippetId, snippetContent, request)
        }
        Paths.get(readmeStringPath).writeText(readmeContent)
    }

    private fun extractSnippets(specContent: String, request: JUnitExecutionRequest) {
        Regex("//(snippet-.+)-start([\\S\\s]*?)//(snippet-.+)-end").findAll(specContent).forEach {
            val (tag, content, endTag) = it.destructured
            request.failIf(tag != endTag) { "tag $tag-start did not end with $tag-end but with $endTag" }
            snippets[tag] = content.trimIndent()
        }
    }

    private fun runSpekWithCustomListener(request: JUnitExecutionRequest) {
        val roots = request.rootTestDescriptor.children
            .filterIsInstance<SpekTestDescriptor>()
            .map(SpekTestDescriptor::scope)

        val executionListener = ReadmeExecutionListener(
            JUnitEngineExecutionListenerAdapter(request.engineExecutionListener, SpekTestDescriptorFactory()),
            examples,
            code
        )
        val executionRequest = ExecutionRequest(roots, executionListener)
        SpekRuntime().execute(executionRequest)
    }

    private fun fileContent(path: String, request: JUnitExecutionRequest): String {
        val file = Paths.get(path)
        request.failIf(!file.exists) { "could not find ${file.absolutePathAsString}" }
        return file.readText()
    }

    private inline fun JUnitExecutionRequest.failIf(predicate: Boolean, errorMessage: () -> String) {
        if (predicate) fail(errorMessage())
    }

    private fun JUnitExecutionRequest.fail(errorMessage: String) = fail(IllegalStateException(errorMessage))
    private fun JUnitExecutionRequest.fail(throwable: Throwable) {
        engineExecutionListener.executionFinished(
            rootTestDescriptor,
            TestExecutionResult.failed(throwable)
        )
    }


    private fun updateExampleLikeInReadme(
        readmeContent: String,
        specContent: String,
        exampleId: String,
        output: String,
        request: JUnitExecutionRequest
    ): String = when {
        exampleId.startsWith("ex-") -> updateExampleInReadme(readmeContent, specContent, exampleId, output, request)
        exampleId.startsWith("exs-") -> updateSplitExampleInReadme(
            readmeContent, specContent, exampleId, output, request
        )
        else -> {
            request.fail("unknown example kind $exampleId")
            readmeContent
        }
    }

    private fun updateExampleInReadme(
        readmeContent: String,
        specContent: String,
        exampleId: String,
        output: String,
        request: JUnitExecutionRequest
    ): String = updateTagInReadme(readmeContent, specContent, exampleId, request, "example") { lineNumber, sourceCode ->
        """```kotlin
        |$sourceCode
        |```
        |↑ <sub>[Example](https://github.com/robstoll/atrium/${System.getenv("README_SOURCETREE")}/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L$lineNumber)</sub> ↓ <sub>Output</sub>
        |```text
        |$output
        |```
        """.trimMargin()
    }


    private fun updateSplitExampleInReadme(
        readmeContent: String,
        specContent: String,
        exampleId: String,
        output: String,
        request: JUnitExecutionRequest
    ): String {
        val updatedReadme = updateTagInReadme(
            readmeContent, specContent, exampleId, request, "ex-code"
        ) { _, sourceCode ->
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
        specContent: String,
        tag: String,
        request: org.junit.platform.engine.ExecutionRequest,
        kind: String,
        content: (Int, String) -> String
    ): String = updateTagInReadme(readmeContent, tag, request, kind) {
        val (lineNumber, sourceCode) = extractSourceCode(specContent, tag, request)
        content(lineNumber, sourceCode)
    }

    private fun updateTagInReadme(
        readmeContent: String,
        tag: String,
        request: org.junit.platform.engine.ExecutionRequest,
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
        specContent: String,
        testId: String,
        request: JUnitExecutionRequest
    ): Pair<Int, String> {
        var lineNumber: Int? = null
        val sb = StringBuilder()

        specContent.lineSequence().forEachIndexed { index, line ->
            if (lineNumber != null) {
                if (line.startsWith("    }")) return lineNumber!! to sb.toString().trimIndent()
                sb.append(line).append("\n")

            } else if (line.trim().startsWith("test(\"$testId\")")) {
                lineNumber = index + 1
            }

        }
        request.fail("cannot find source code for $testId")
        return -1 to ""
    }

    private fun updateCodeInReadme(
        readmeContent: String,
        specContent: String,
        snippetId: String,
        request: org.junit.platform.engine.ExecutionRequest
    ): String = updateTagInReadme(readmeContent, specContent, snippetId, request, "code") { _, sourceCode ->

        """```kotlin
        |$sourceCode
        |```
        """.trimMargin()
    }


    private fun updateSnippetInReadme(
        readmeContent: String,
        snippetId: String,
        snippetContent: String,
        request: JUnitExecutionRequest
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
}

