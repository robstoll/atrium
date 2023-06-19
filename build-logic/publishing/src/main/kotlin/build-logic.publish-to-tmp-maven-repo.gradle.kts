plugins {
    id("maven-publish")
}

val localRepoElements: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
    description =
        "Shares local maven repository directory that contains the artifacts produced by the current project"
    attributes {
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named("maven-repository"))
        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
    }
}

val localRepoDir: Provider<Directory> = layout.buildDirectory.dir("local-maven-repo")

publishing {
    repositories {
        maven {
            name = "tmp-maven"
            url = uri(localRepoDir)
        }
    }
}

//TODO this causes that publishAllPublicationsToTmp is included when running `./gradlew build`
// comment out in case you want to publish to tmp or even better, fix this configuration so that it does
// what it should (when required or find a way that it is not included when but we only want it when explicitly
// called
//localRepoElements.outgoing.artifact(localRepoDir) {
//    builtBy(tasks.named("publishAllPublicationsToTmp-mavenRepository"))
//}

val cleanLocalRepository by tasks.registering(Delete::class) {
    description = "Clears local-maven-repo so timestamp-based snapshot artifacts do not consume space"
    delete(localRepoDir)
}

tasks.withType<PublishToMavenRepository>()
    .matching { it.name.endsWith("PublicationToTmp-mavenRepository") }
    .configureEach {
        dependsOn(cleanLocalRepository)
    }
