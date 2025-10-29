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
include(":core:system")

include(":features:agents:api")
include(":features:agents:data")
include(":features:agents:domain")
include(":features:agents:ui")

include(":features:maps:api")
include(":features:maps:data")
include(":features:maps:domain")
include(":features:maps:ui")