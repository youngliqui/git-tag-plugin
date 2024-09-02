package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import ru.clevertec.extension.GitExtension
import ru.clevertec.model.Tag
import ru.clevertec.service.GitService

class CreateTagTask extends DefaultTask {
    @Input
    GitService gitService

    @TaskAction
    void createTag() {
        def extension = project.extensions.getByType(GitExtension)
        def currentBranch = extension.currentBranch
        def lastTagString = extension.lastTagString

        Tag newTag = calculateTag(currentBranch, lastTagString)
        println "New Tag: $newTag"

        if (extension.hasUncommittedChanges) {
            logger.lifecycle("${newTag}.uncommited")
        } else {
            println "Created tag: $newTag, and pushed"
            gitService.createTag(newTag.toString())
            gitService.pushTag(newTag.toString())
        }
    }

    static Tag calculateTag(String currentBranch, String lastTagString) {
        Tag currentTag =
                lastTagString ? Tag.fromString(lastTagString) : new Tag()

        switch (currentBranch) {
            case "dev":
            case "qa":
                currentTag.incrementMinor()
                break
            case "stage":
                currentTag.incrementMinor()
                currentTag.setLabel("-rc")
                break
            case "master":
            case "main":
                currentTag.incrementMajor()
                break
            default:
                currentTag.incrementMinor()
                currentTag.setLabel("-SNAPSHOT")
        }

        return currentTag
    }
}
