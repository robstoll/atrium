private val currentWorkaroundToLoadDomainRobstollAndCoreRobstoll = dep()
private fun dep() {
    js(
        """
        require('atrium-domain-robstoll-js');
        require('atrium-core-robstoll-js')
        """
    )
}
