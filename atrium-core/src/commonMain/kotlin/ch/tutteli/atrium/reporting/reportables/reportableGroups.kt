package ch.tutteli.atrium.reporting.reportables

//TODO 1.3.0 check KDOC (including @since) of all types in this file

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

//TODO 1.3.0 add KDOC and move to own file
interface ReportableGroupWithDesignation : ReportableGroup {

    /**
     * The description of the group.
     */
    val description: InlineElement

    /**
     * A complementing representation to the [description] -- typically the subject for which the [children]
     * are defined.
     *
     * For instance, if the description is `index 0` then the representation shows what is at index 0.
     */
    val representation: Any
}
