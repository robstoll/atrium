private val currentSetupWorkaround = dep()
private fun dep() {
    js(
        """
        require('atrium-cc-en_GB-robstoll-js');
        """
    )
}
