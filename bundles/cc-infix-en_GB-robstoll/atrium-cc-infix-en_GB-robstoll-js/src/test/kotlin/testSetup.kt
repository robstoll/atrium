private val currentSetupWorkaround = dep()
private fun dep() {
    js(
        """
        require('atrium-cc-infix-en_GB-robstoll-js');
        """
    )
}
