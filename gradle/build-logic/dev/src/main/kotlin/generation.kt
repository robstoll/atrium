
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.streams.asSequence

/**
 * Use the function `forTarget` to create this data class.
 */
@Suppress("ArrayInDataClass")
data class TargetWithAdditionalPackages(
    val sourceSet: KotlinSourceSet,
    val additionalPackages: Array<out Pair<String, (Path) -> Pair<String, String>>>
)

fun Project.includingTarget(
    sourceSetNamePrefix: String,
    vararg additionalPackages: Pair<String, (Path) -> Pair<String, String>>
): TargetWithAdditionalPackages = TargetWithAdditionalPackages(
    the<KotlinMultiplatformExtension>().sourceSets.getByName(sourceSetNamePrefix + "Main"),
    additionalPackages
)


fun Project.createGenerateLogicTask(
    vararg targetWithPackages: TargetWithAdditionalPackages,
    suffix: String = ""
): TaskProvider<*> {
    val generateLogic = tasks.register("generateLogic") {
        group = "build"
        description = "generates extension methods for AssertionContainer based on interfaces"
        outputs.dir("src/generated/")
    }

    targetWithPackages.forEach { (sourceSet, additionalPackages) ->
        val mainSrcFolder = sourceSet.kotlin.srcDirs.first()
        val generatedFolder = "src/generated/${sourceSet.name}/"

        val generatedFiles = project.files(generatedFolder)
        sourceSet.kotlin.srcDir(generatedFiles)

        val all = mapOf<String, (Path) -> Pair<String, String>>("" to { _ ->
            Pair(
                "<T> AssertionContainer<T>",
                "getImpl"
            )
        }) + additionalPackages
        all.forEach { (relativePackagePath, f) ->
            val relativePackagePathWithSlash = relativePackagePath + if (suffix !== "") "/$suffix" else ""
            val task = registerGenerateLogicTaskForPackage(
                sourceSet.name,
                generatedFolder,
                relativePackagePathWithSlash,
                mainSrcFolder,
                f
            )
            val generatedPackageFiles = project.files("$generatedFolder/${prefixPackagePath(relativePackagePathWithSlash)}")
            generatedPackageFiles.builtBy(task)
            generateLogic.configure {
                dependsOn(task)
            }
        }
    }
    return generateLogic
}

fun Project.registerGenerateLogicTaskForPackage(
    sourceSetName: String,
    generatedFolder: String,
    relativePackagePath: String,
    mainSrcFolder: File,
    f: (Path) -> Pair<String, String>
): TaskProvider<Task> {

    return tasks.register("generateLogic_${sourceSetName}_${relativePackagePath.replace('/', '_')}") {
        group = "build"
        description = "generates ext. methods for package $relativePackagePath"
        val packagePath = prefixPackagePath(relativePackagePath)
        val srcPath = "${mainSrcFolder.absolutePath}/$packagePath/"
        val generatedPath = "${project.projectDir}/$generatedFolder/$packagePath"
        inputs.dir(srcPath)
        outputs.dir(generatedPath)

        val fullPackage = packagePath.replace('/', '.')
        val interfaces = getInterfaces(srcPath)
        val interfacesWithPair = interfaces.associateWith { f(it) }

        val ln = System.lineSeparator()

        doFirst {
            val header = """
                // @formatter:off
                //---------------------------------------------------
                //  Generated content, don't change here but in:
                //  gradle/build-logic/dev/src/main/kotlin/generation.kt
                //  Enjoy the day 🙂
                //---------------------------------------------------

                """.trimIndent().replace("\n", ln)

            val identifier = "[a-zA-Z0-9]+"
            val newLine = """(?:\n|\r\n?)"""
            val newLineAndIndent = """$newLine\s*"""
            val parameter = { paramNumber: Int -> Regex(""",(?: |${newLineAndIndent})(?<paramName$paramNumber>$identifier) *: (?<typeName$paramNumber>[^:]+?)""") }
            val returnType = Regex("""(?:${newLineAndIndent})?\)(?<returnType>:.+)""")

            val typeIdentifier = Regex(""" *fun (?<generics><.+?> )?(?<funcName>$identifier)\((?:$newLineAndIndent)?$identifier *: (?<type>$identifier(?:\.$identifier)*<.+>)""")
            val patterns = (6 downTo 0).map { numOfParams ->
                val params = if (numOfParams > 0) (1..numOfParams).toList() else emptyList()
                Triple(
                    Regex(typeIdentifier.pattern + params.joinToString("") { paramNumber -> parameter(paramNumber).pattern } + returnType.pattern),
                    """fun ${"$"}{generics}${"$"}{type}\.${"$"}{funcName}\(""" +
                        params.joinToString(", ") { paramNumber -> """${"$"}{paramName$paramNumber}: ${"$"}{typeName$paramNumber}"""} +
                        """\)${"$"}{returnType} =""" + if(numOfParams > 1) "$ln    " else " ",
                    """\.${"$"}{funcName}\(this""" +
                        (if(numOfParams > 0) ", " else "") +
                        params.joinToString(", ") { paramNumber -> """${"$"}{paramName$paramNumber}""" } +
                        """\)"""
                )
            }

            interfacesWithPair.forEach { (interfacePath, pair) ->
                val type = getType(interfacePath)
                val (extensionTypeSignature, getImpl) = pair

                val decapitalized = type.replaceFirstChar { it.lowercase(Locale.getDefault()) }
                val output = File("$generatedPath/${decapitalized}.kt")
                val content = interfacePath.toFile().readText(StandardCharsets.UTF_8)
                val interfaceName = "${type}Assertions"
                val implValName = "impl"

                var tmp = content.replace(Regex("""(${newLine}/\*\*[\S\s]+?\*/)?${newLine}interface $interfaceName \{"""),
                    """
                    import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
                    import ${fullPackage}.impl.Default${type}Assertions

                    """.trimIndent().replace("\n", ln))
                patterns.forEach { (regex, prefix, suffix) ->
                    tmp = regex.replace(tmp, prefix + implValName + suffix)
                }

                val generatedContent = tmp.substring(0, tmp.lastIndexOf("}"))
                output.writeText(header,StandardCharsets.UTF_8)
                output.appendText(generatedContent,StandardCharsets.UTF_8)
                output.appendText(
                    """

                    @OptIn(ExperimentalNewExpectTypes::class)
                    private inline val ${extensionTypeSignature}.impl: ${type}Assertions
                        get() = $getImpl(${type}Assertions::class) { Default${type}Assertions() }

                    """.trimIndent().replace("\n", ln),
                    StandardCharsets.UTF_8
                )
            }
        }
    }
}
fun prefixPackagePath(relativePackagePath: String) = "ch/tutteli/atrium/logic$relativePackagePath"


fun getInterfaces(path: String): List<Path> =
    Files.walk(Paths.get(path), 1).use { stream ->
        stream.asSequence()
            .filter { file -> file.fileName.toString().endsWith("Assertions.kt") }
            .sortedWith { a, b -> a.fileName.toString().compareTo(b.fileName.toString()) }
            .toList()
    }

fun getType(input: Path): String {
    val fileName = input.fileName.toString()
    return fileName.substring(0, fileName.length - "Assertions.kt".length)
}
