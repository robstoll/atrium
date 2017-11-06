package ch.tutteli.atrium.creating

abstract class BaseReportingAssertionPlant<out T : Any?, out A : IBaseAssertionPlant<T, A>>(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>
) : BaseAssertionPlant<T, A>(), IBaseReportingAssertionPlant<T, A> {

    override fun checkAssertions(): A {
        try {
            commonFields.check(getAssertions())
        } finally {
            clearAssertions()
        }
        return self
    }
}
