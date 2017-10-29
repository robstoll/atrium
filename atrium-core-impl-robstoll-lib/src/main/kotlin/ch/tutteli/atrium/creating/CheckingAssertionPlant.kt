package ch.tutteli.atrium.creating

class CheckingAssertionPlant<out T : Any>(
    override val subject: T
) : BaseAssertionPlant<T>(), ICheckingAssertionPlant<T> {

    override fun allAssertionsHold(): Boolean {
        val assertions = getAssertions()
        if (assertions.isEmpty()) throw IllegalStateException("You need to create assertions first before checking whether they all hold.")

        try {
            return assertions.all { it.holds() }
        } finally {
            clearAssertions()
        }
    }

}

