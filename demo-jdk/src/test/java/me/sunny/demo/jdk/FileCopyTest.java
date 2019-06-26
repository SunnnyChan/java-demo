package me.sunny.demo.jdk;

import me.sunny.demo.jdk.nio.file.FileCopy;

public class FileCopyTest {

    public static void main(String[] args) {
        // FileCopy
        String workPath = System.getProperty("user.dir");
        FileCopy fileCopy = new FileCopy();
        fileCopy.copy(workPath + "/demo-jdk/test/resources/_data/data_from.t",
                workPath + "/demo-nio/_data/data_to.t");
    }
}
