package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import ru.clevertec.extension.GitExtension
import ru.clevertec.service.GitService

class CheckGitInstalledTask extends DefaultTask {
    @Input
    GitService gitService

    @TaskAction
    void checkGitInstalled() {
        def extension = project.extensions.getByType(GitExtension)
        extension.isGitInstalled = gitService.isGitInstalled()
        if (!extension.isGitInstalled) {
            throw new GradleException("Git is not installed")
        } else {
            println "Git is installed"
        }
    }
}
