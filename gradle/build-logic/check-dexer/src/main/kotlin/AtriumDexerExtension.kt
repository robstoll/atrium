import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.kotlin.dsl.listProperty
import javax.inject.Inject


abstract class AtriumDexerExtension @Inject constructor(objects: ObjectFactory) {
    /**
     * List of project modules to check for dexing compatibility.
     * These are the module names without the root project prefix.
     */
    val subprojects: ListProperty<String> = objects.listProperty<String>().convention(
        listOf(
            "core",
            "logic",
            "translations-en_GB",
            "api-fluent",
            "api-infix",
            "fluent",
            "infix",
            "verbs",
            "verbs-internal"
        )
    )

    // Allows configuring the subprojects from the build.gradle.kts file
    fun subprojects(vararg modules: String) {
        subprojects.set(modules.toList())
    }
}
