package ch.tutteli.atrium.reporting.reportables

//TODO 1.3.0 check KDOC (including @since) of all types in this file

/**
 * Marker interface for everything which can be reported.
 *
 * @since 1.3.0
 */
interface Reportable


//TODO 1.3.0 add KDOC and move to own file
interface ReportableWithDesignation : Reportable {

    /**
     * The description of the [Reportable].
     *
     * @since 1.3.0
     */
    val description: Diagnostic

    /**
     * A complementing representation to the [description].
     *
     * In the context of a [ReportableGroup] it is typically the subject for which the [ReportableGroup.children]
     * are defined. For instance, if the description is `index 0` then the representation shows what is at index 0.
     *
     * @since 1.3.0
     */
    val representation: Any
}

/**
 * The base interface for [Reportable] groups.
 */
interface ReportableGroup : Reportable {
    /**
     * The reportable elements of this group, which are defined for the subject.
     */
    //TODO 1.3.0 consider to use a NoneEmptyList instead, in the end there won't be any group without a child
    // if no child is defined then at list a child proof will be there explaining that the user forgot to define
    // children
    val children: List<Reportable>
}


interface ReportableGroupWithDesignation : ReportableGroup, ReportableWithDesignation

//TODO 1.3.0 remove if this is only used by SimpleProof
interface ReportableWithInlineDesignation : ReportableWithDesignation {
    override val description: InlineElement
}

fun ReportableGroup.requireOneChild() {
    require(children.isNotEmpty()) {
        "a group requires at least one child"
    }
}
