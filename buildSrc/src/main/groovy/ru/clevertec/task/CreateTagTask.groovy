package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import ru.clevertec.task.util.GitHelper
import ru.clevertec.task.util.Tag

class CreateTagTask extends DefaultTask {
    @TaskAction
    void createTag() {
        def currentBranch = GitHelper.getCurrentBranch()
        println "CurrentBranch: $currentBranch"

        def lastTagString = GitHelper.getLastTag()
        println "LastTag: $lastTagString"

        if (GitHelper.hasCurrentStateTag()) {
            println "The tag has already been assigned to the current state: $lastTagString"
            return
        }

        Tag newTag = calculateTag(currentBranch, lastTagString)
        println "New Tag: $newTag"

        if (GitHelper.hasUncommittedChanges()) {
            logger.lifecycle("${newTag}.uncommited")
        } else {
            println "Created tag: $newTag, and pushed"
            GitHelper.createTag(newTag.toString())
            GitHelper.pushTag(newTag.toString())
        }
    }

    static Tag calculateTag(String currentBranch, String lastTagString) {
        Tag currentTag =
                lastTagString ? Tag.fromString(lastTagString) : new Tag(0, 0, 0, "")

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
                currentTag.incrementMajor()
                break
            default:
                currentTag.incrementMinor()
                currentTag.setLabel("-SNAPSHOT")
        }

        return currentTag
    }
}
