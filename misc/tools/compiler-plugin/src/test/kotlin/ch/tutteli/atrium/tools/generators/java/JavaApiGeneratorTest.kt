package ch.tutteli.atrium.tools.generators.java

import ch.tutteli.atrium.tools.generator.java.JavaApiGeneratorPluginComponentRegistrar
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.JvmTarget
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals

class JavaApiGeneratorTest {

    @Test
    fun `can generate anyExpectations`() {
        val result = compile(
            SourceFile.fromPath(
                Paths.get("")
                    .resolve("../../../apis/fluent/atrium-api-fluent/src/commonMain/kotlin/ch/tutteli/atrium/api/fluent/en_GB/anyExpectations.kt")
                    .toFile()
            )
        )
        assertEquals(KotlinCompilation.ExitCode.OK, result.exitCode)
    }

    private fun compile(
        vararg sourceFiles: SourceFile,
        plugin: CompilerPluginRegistrar = JavaApiGeneratorPluginComponentRegistrar(),
    ): KotlinCompilation.Result {
        return KotlinCompilation().apply {
            sources = sourceFiles.toList()
            compilerPluginRegistrars = listOf(plugin)
            inheritClassPath = true
            jvmTarget = JvmTarget.JVM_11.toString()
        }.compile()
    }
}
