private val currentSetupWorkaround = dep()
private fun dep() {
    js(
        """
        require('atrium-cc-de_CH-robstoll-js');
        """
    )
}
