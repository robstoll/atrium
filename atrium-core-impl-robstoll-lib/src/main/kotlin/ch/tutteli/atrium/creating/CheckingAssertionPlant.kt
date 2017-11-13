package ch.tutteli.atrium.creating

class CheckingAssertionPlant<out T : Any>(
    override val subject: T
) : BaseAssertionPlant<T, IAssertionPlant<T>>(), ICheckingAssertionPlant<T> {
    override val self = this

    override fun addAssertionsCreatedBy(createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
        this.createAssertions()
        return this
    }

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

