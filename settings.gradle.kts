pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EvaluationP1"
include(":app")

include(":core:data")
include(":core:domain")
include(":core:ui")

include(":features:exemple:api")
include(":features:exemple:data")
include(":features:exemple:domain")
include(":features:exemple:ui")
 