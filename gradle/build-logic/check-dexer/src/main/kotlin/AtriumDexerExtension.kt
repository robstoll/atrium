import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.kotlin.dsl.listProperty
import javax.inject.Inject

/**
 * Extension for configuring the Android DEX compatibility check plugin.
 */
abstract class AtriumDexerExtension @Inject constructor(objects: ObjectFactory) {

    val subprojects: ListProperty<String> = objects.listProperty<String>().convention(DEFAULT_MODULES)

    /**
     * Allows configuring the subprojects from the build.gradle.kts file.
     *
     * Example usage in build.gradle.kts:
     * ```
     * dexer {
     *     subprojects("core", "logic", "api-fluent")
     * }
     * ```
     *
     * @param modules The module names to include in the dexer check
     */
    fun subprojects(vararg modules: String) {
        subprojects.set(modules.toList())
    }

    companion object {
        const val TASK_NAME="checkDexer"
        const val ATRIUM_ANDROID_JAR = "ATRIUM_ANDROID_JAR"

        private val DEFAULT_MODULES = listOf(
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
    }
}
