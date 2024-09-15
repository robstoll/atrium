package readme.examples.jupiter

object ReadmeState {
    // not thread safe, if we should execute them in parallel then we would need to adjust this
    val examples: MutableMap<String, String> = mutableMapOf()
    val code: MutableSet<String> = HashSet()
    val testClasses: MutableSet<Class<*>> = HashSet()
    val snippets: MutableMap<String, String> = mutableMapOf()
}
