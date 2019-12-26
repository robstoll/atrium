import ch.tutteli.atrium.core.robstoll.dependOn_atrium_core_robstoll
import ch.tutteli.atrium.domain.robstoll.dependOn_atrium_domain_robstoll

@Suppress("unused")
private val currentSetupWorkaround = dep()
private fun dep() {
    dependOn_atrium_domain_robstoll()
    dependOn_atrium_core_robstoll()
}
