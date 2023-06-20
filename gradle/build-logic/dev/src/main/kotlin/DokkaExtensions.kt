import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.AbstractDokkaTask

fun AbstractDokkaTask.configurePlugins() {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "Atrium &copy; Copyright Robert Stoll &lt;rstoll@tutteli.ch&gt;"
    }
}
