package ru.clevertec.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.clevertec.extension.GitExtension
import ru.clevertec.service.GitService
import ru.clevertec.task.*

class GitTagPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.create("gitExtension", GitExtension)
        def service = new GitService()

        project.tasks.register("createTag", CreateTagTask) {
            group = "git utils"
            description = "Creates a git tag depending on the current branch"
            dependsOn "checkGitInstalled", "getCurrentBranch", "getLastTag", "hasCurrentStateTag",
                    "hasUncommittedChanges", "checkRemoteRepository"
            gitService = service
        }

        project.tasks.register("checkGitInstalled", CheckGitInstalledTask) {
            group = "git utils"
            description = "Checks whether the git is connected to the project"
            gitService = service
        }

        project.tasks.register("getCurrentBranch", GetCurrentBranchTask) {
            group = "git utils"
            description = "Gets the name of the current branch"
            dependsOn "checkGitInstalled"
            gitService = service
        }

        project.tasks.register("getLastTag", GetLastTagTask) {
            group = "git utils"
            description = "Gets the last tag in the current repository"
            dependsOn "getCurrentBranch"
            gitService = service
        }

        project.tasks.register("hasCurrentStateTag", HasCurrentStateTagTask) {
            group = "git utils"
            description = "Checks if the last tag matches this commit"
            dependsOn "getLastTag"
            gitService = service
        }

        project.tasks.register("hasUncommittedChanges", HasUncommittedChangesTask) {
            group = "git utils"
            description = "Checks if there are uncommitted changes"
            dependsOn "getCurrentBranch"
            gitService = service
        }

        project.tasks.register("checkRemoteRepository", CheckRemoteRepositoryTask) {
            group = "git utils"
            description = "Checks if a remote repository is configured"
            dependsOn "checkGitInstalled"
            gitService = service
        }
    }
}
