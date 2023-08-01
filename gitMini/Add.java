package gitlet;

import java.io.File;
import java.io.Serializable;

/** The method for the add command.
 * @author Frank Cuoco
 * */
public class Add implements Serializable {

    /** Initializes the add instance. */
    public Add() {
    }

    /** Adds a file to the staging area given a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        String name = args[0];
        if (stage.getRemoved().containsKey(name)) {
            stage.unRemoveFile(name);
            Utils.writeObject(Utils.join(".gitlet",
                    "stage", "staging.ser"), stage);
            return;
        }
        File toAdd = new File(name);
        if (!toAdd.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        Blob blob = new Blob(Utils.readContents(toAdd), name);
        Utils.writeObject(Utils.join(".gitlet", "blobs", blob.getSha1()), blob);
        if (stage.getTracked().containsKey(blob.getName())) {
            String otherBlobSha1 = stage.getTracked().get(blob.getName());
            Blob otherBlob = Utils.readObject(Utils.join(".gitlet",
                    "blobs", otherBlobSha1), Blob.class);
            if (blob.isEqual(otherBlob)) {
                if (stage.getStaged().containsKey(blob.getName())) {
                    stage.unstageFile(blob.getName());
                }
            } else {
                stage.unstageFile(blob.getName());
                stage.stageFile(blob.getName(), blob.getSha1());
            }
        } else {
            stage.stageFile(blob.getName(), blob.getSha1());
        }
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }
}
