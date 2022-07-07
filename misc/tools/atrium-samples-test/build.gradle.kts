val samples = listOf("junit5", "spek", "multiplatform")

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
