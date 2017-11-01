package ch.tutteli.atrium.creating

abstract class BaseReportingAssertionPlant<out T : Any>(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>
) : BaseAssertionPlant<T>(), IReportingAssertionPlant<T> {

    override fun checkAssertions(): IAssertionPlant<T> {
        try {
            commonFields.check(getAssertions())
        } finally {
            clearAssertions()
        }
        return this
    }
}
