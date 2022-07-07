val samples = listOf("js")

tasks.register("build") {
    samples.forEach {  projectName ->
        dependsOn(gradle.includedBuild(projectName).task(":build"))
    }
}

tasks.register("clean") {
    samples.forEach {  projectName ->
        dependsOn(gradle.includedBuild(projectName).task(":clean"))
    }
}

tasks.register("cleanAtrium") {
    samples.forEach {  projectName ->
        dependsOn(gradle.includedBuild("atrium").task(":clean"))
    }
}
