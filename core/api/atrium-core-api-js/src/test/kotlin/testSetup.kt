import ch.tutteli.atrium.core.polyfills.*

private val currentSetupWorkaround = dep()
private fun dep() {
    js(
        """
        require('atrium-domain-robstoll-js');
        require('atrium-core-robstoll-js')
        """
    )
    registerService<InterfaceWithOneImplementation> { SingleService() }
    registerService<InterfaceWithTwoImplementation> { Service1() }
    registerService<InterfaceWithTwoImplementation> { Service2() }
}
