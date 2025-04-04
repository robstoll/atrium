package ch.tutteli.atrium.tools.generators.java.gradle

import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class JavaApiGeneratorSubplugin : KotlinCompilerPluginSupportPlugin {

    override fun getCompilerPluginId(): String = "$GROUP_ID.tools.generator.java"

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        groupId = GROUP_ID,
        artifactId = ARTIFACT_NAME,
        version = VERSION_NUMBER
    )

    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> =
        kotlinCompilation.target.project.provider {
            listOf(
                SubpluginOption(
                    key = "targetDir",
                    value = kotlinCompilation.target.project.project(":atrium-api-fluent-java").layout.projectDirectory.dir(
                        "src/main/generated/java/ch/tutteli/atrium/api/fluent/java"
                    ).asFile.absolutePath
                ),
            )
        }

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true

    companion object {
        const val GROUP_ID = "ch.tutteli.atrium"
        const val ARTIFACT_NAME = "atrium-java-api-generator"
        const val VERSION_NUMBER = "0.1.0"
    }
}
