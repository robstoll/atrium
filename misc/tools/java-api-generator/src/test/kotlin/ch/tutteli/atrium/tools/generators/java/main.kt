package ch.tutteli.atrium.tools.generators.java

import ch.tutteli.atrium.tools.generator.java.GenerationData
import ch.tutteli.atrium.tools.generator.java.JavaApiGeneratorPluginComponentRegistrar
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.JvmTarget
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.createDirectories
import kotlin.io.path.walk
import kotlin.io.path.writeText

@OptIn(ExperimentalPathApi::class)
fun main() {
    val targetPackage = "ch.tutteli.atrium.api.fluent.java"
    val targetDir =
        Paths.get("apis/fluent/atrium-api-fluent-java/src/main/generated/java/" + targetPackage.replace('.', '/'))

    val apiFluentDir = Paths.get("apis/fluent/atrium-api-fluent")

    val generationDataMap = mutableMapOf<String, GenerationData>()

    val pluginComponentRegistrar = JavaApiGeneratorPluginComponentRegistrar(generationDataMap)

    val result = compile(
        pluginComponentRegistrar,
        listOf(
            "commonMain" to true,
            "jvmMain" to false
        ).flatMap { (subPath, isMultiplatformCommonSource) ->
            apiFluentDir.resolve("src/${subPath}/kotlin/ch/tutteli/atrium/api/fluent/en_GB/").walk()
                .map { SourceFile.fromPath(it.toFile(), isMultiplatformCommonSource) }
        }
    )

    targetDir.createDirectories()

    generationDataMap.forEach { (identifier, generationData) ->
        val shortName = identifier.substringAfterLast('.')

        targetDir.resolve("Abstract${shortName}Expect.java").writeText(
            """
            |package $targetPackage;
            |
            |import ch.tutteli.atrium.creating.AssertionContainer;
            |import ch.tutteli.atrium.creating.Expect;
            |import ch.tutteli.atrium.logic.${shortName}Kt;
            |import ch.tutteli.atrium.logic.LogicKt;
            |
            |public abstract class Abstract${shortName}Expect<SubjectT extends ${generationData.javaType}, SelfT extends Abstract${shortName}Expect<SubjectT, SelfT>>
            |    extends AbstractExpect<SubjectT, SelfT> {
            |    public Abstract${shortName}Expect(Expect<SubjectT> coreExpect) {
            |        super(coreExpect);
            |    }
            |
            |    ${generationData.sb}
            |}
            """.trimMargin()
        )

        targetDir.resolve("${shortName}Expect.java").writeText(
            """
            |package $targetPackage;
            |
            |import ch.tutteli.atrium.creating.Expect;
            |
            |public class ${shortName}Expect<SubjectT extends ${shortName}>
            |    extends Abstract${shortName}Expect<SubjectT, ${shortName}Expect<SubjectT>> {
            |
            |    public ${shortName}Expect(Expect<SubjectT> coreExpect) {
            |        super(coreExpect);
            |    }
            |}
            """.trimMargin()
        )
    }

    targetDir.resolve("Expects.java").writeText(
        """
        |package ch.tutteli.atrium.api.fluent.java;
        |
        |import ch.tutteli.atrium.creating.Expect;
        |import ch.tutteli.atrium.logic.creating.RootExpectBuilder;
        |
        |
        |public interface Expects {
        |    ${
            generationDataMap.map { (identifier, generationData) ->
                val shortName = identifier.substringAfterLast('.')
                """
                |    static <SubjectT extends ${generationData.javaType}> ${shortName}Expect<SubjectT> expect(SubjectT subject) {
                |        return expect${shortName}(createExpect(subject));
                |    }
        """.trimMargin()
            }
        }
        |
        |    static <SubjectT extends Block> BlockExpect<SubjectT> expect(SubjectT subject) {
        |        return new BlockExpect(createExpect(subject));
        |    }
        |
        |
        |    private static <SubjectT> Expect<SubjectT> createExpect(SubjectT subject) {
        |        return RootExpectBuilder.Companion.forSubject(subject)
        |            .withVerb("I expected subject")
        |            .withoutOptions()
        |            .build();
        |    }
        |}
    """.trimMargin()
    )

    check(result.exitCode == KotlinCompilation.ExitCode.OK) {
        "compiler errors, see above"
    }
}

private fun compile(
    plugin: CompilerPluginRegistrar,
    sourceFiles: List<SourceFile>,
): KotlinCompilation.Result {
    return KotlinCompilation().apply {
        sources = sourceFiles.toList()
        compilerPluginRegistrars = listOf(plugin)
        inheritClassPath = true
        jvmTarget = JvmTarget.JVM_11.toString()
    }.compile()
}
