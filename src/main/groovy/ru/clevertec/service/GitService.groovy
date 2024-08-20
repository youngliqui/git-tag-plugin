package ru.clevertec.service

class GitService {
    String getCurrentBranch() {
        executeGitCommand("rev-parse --abbrev-ref HEAD").trim()
    }

    String getLastTag() {
        executeGitCommand("describe --abbrev=0 --tags").trim()
    }

    boolean hasCurrentStateTag() {
        executeGitCommand("describe --exact-match --tags").trim()
    }

    boolean hasUncommittedChanges() {
        executeGitCommand("status --porcelain").trim()
    }

    void createTag(String tagName) {
        executeGitCommand("tag $tagName")
    }

    void pushTag(String tagName) {
        executeGitCommand("push origin $tagName")
    }

    boolean isGitInstalled() {
        executeGitCommand("status").trim()
    }

    boolean hasRemoteRepository() {
        executeGitCommand("remote -v").trim()
    }


    private String executeGitCommand(String command) {
        def process = "git $command".execute()
        process.waitFor()

        process.text
    }
}
