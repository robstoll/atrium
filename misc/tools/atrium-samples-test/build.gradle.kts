//TODO 1.1.0 include kotlin-test again once we use IR for JS
val samples = listOf("junit5", "spek") //, "kotlin-test")

tasks.register("build") {
    samples.forEach { projectName ->
        dependsOn(gradle.includedBuild(projectName).task(":build"))
    }
}

tasks.register("clean") {
    samples.forEach { projectName ->
        dependsOn(gradle.includedBuild(projectName).task(":clean"))
    }
}

tasks.register("cleanAtrium") {
    dependsOn(gradle.includedBuild("atrium").task(":clean"))
}
