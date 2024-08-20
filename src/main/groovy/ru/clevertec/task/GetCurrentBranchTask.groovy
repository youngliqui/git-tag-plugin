package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import ru.clevertec.extension.GitExtension
import ru.clevertec.service.GitService

class GetCurrentBranchTask extends DefaultTask {
    @Input
    GitService gitService

    @TaskAction
    void getCurrentBranch() {
        def extension = project.extensions.getByType(GitExtension)
        extension.currentBranch = gitService.getCurrentBranch()
        if (!extension.currentBranch) {
            throw new GradleException("You are not in any branch")
        }
        println "Current branch: $extension.currentBranch"
    }
}
