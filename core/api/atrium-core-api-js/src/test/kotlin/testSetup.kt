import ch.tutteli.atrium.core.polyfills.*
import ch.tutteli.atrium.core.robstoll.dependOn_atrium_core_robstoll
import ch.tutteli.atrium.domain.robstoll.dependOn_atrium_domain_robstoll

private val currentSetupWorkaround = dep()
private fun dep() {
    dependOn_atrium_domain_robstoll()
    dependOn_atrium_core_robstoll()
    registerService<InterfaceWithOneImplementation> { SingleService() }
    registerService<InterfaceWithTwoImplementation> { Service1() }
    registerService<InterfaceWithTwoImplementation> { Service2() }
}
