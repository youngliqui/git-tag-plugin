package ru.clevertec.task.util

class Tag {
    private int major
    private int minor
    private int patch
    private String label

    Tag(int major, int minor, int patch, String label) {
        this.major = major
        this.minor = minor
        this.patch = patch
        this.label = label
    }

    @Override
    String toString() {
        patch == 0 ? "v$major.$minor$label" : "v$major.$minor.$patch$label"
    }

    void incrementMajor() {
        major++
        minor = 0
        patch = 0
        label = ""
    }

    void incrementMinor() {
        minor++
        patch = 0
        label = ""
    }

    void setLabel(String label) {
        this.label = label
    }

    static Tag fromString(String tagString) {
        def matcher = tagString =~ /^v(\d+)\.(\d+)(?:\.(\d+))?(-\w+)?$/
        if (matcher) {
            int major = matcher[0][1] as Integer
            int minor = matcher[0][2] as Integer
            int patch = matcher[0][3] ? (matcher[0][3] as Integer) : 0
            String label = matcher[0][4] ?: ""
            return new Tag(major, minor, patch, label)
        }
        throw new IllegalArgumentException("Invalid tag format: $tagString")
    }
}
