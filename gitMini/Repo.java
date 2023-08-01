package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

/** The repository.
 * @author Frank Cuoco
 * */
public class Repo implements Serializable {

    /** Setting the current WORKINGDIRECTORY. */
    public Repo(String workingdirectory) {
        workingDirectory = workingdirectory;
    }

    /** The job of this method is to initialize all of the pathways
     * needed inside the .gitlet folder (including .gitlet itself),
     * create and serialize the staging area, and call an initial commit. */
    public void init() {
        if (new File(homePath).exists()) {
            System.out.println("A Gitlet version-control system "
                    + "already exists in the current directory.");
            System.exit(0);
        }
        folders.put("gitlet", new File(homePath));
        folders.put("commits", Utils.join(".gitlet", "commits"));
        folders.put("blobs", Utils.join(".gitlet", "blobs"));
        folders.put("branches", Utils.join(".gitlet", "branches"));
        folders.put("stage", Utils.join(".gitlet", "stage"));
        for (File folder: folders.values()) {
            folder.mkdirs();
        }
        stage = new StagingArea();
        stage.setHead(null);
        Utils.writeObject(Utils.join(".gitlet", "branches",
                "master"), "master");
        stage.setCurrentBranch("master");
        stage.addBranch(stage.getCurrentBranch(), stage.getHead());

        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);

        Commit initCommit = new Commit();
        initCommit.doCommand(this, new String[]{"initial commit"});
    }

    /** Returns the working directory. */
    public String getWorkingDirectory() {
        return workingDirectory;
    }

    /** Returns the home path. */
    public String getHomePath() {
        return homePath;
    }

    /** Returns a slash. */
    public String getSlash() {
        return slash;
    }

    /** Returns the stage. */
    public StagingArea getStage() {
        return stage;
    }

    /** Returns the folders in .gitlet. */
    public HashMap<String, File> getFolders() {
        return folders;
    }

    /** Returns the head. */
    public String getHead() {
        return stage.getHead();
    }

    /** Returns the committed files. */
    public HashMap<String, CommittedFile> getCommitted() {
        System.out.println(stage);
        return stage.getCommitted();
    }

    /** The current working directory. */
    private String workingDirectory;
    /** The current stage. */
    private StagingArea stage;
    /** A HashMap of folders in .gitlet. */
    private HashMap<String, File> folders = new HashMap<String, File>();
    /** Home, or .gitlet. */
    private String homePath = ".gitlet";
    /** A slash sign. */
    private String slash = "/";
}
