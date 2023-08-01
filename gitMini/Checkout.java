package gitlet;

import java.io.File;
import java.util.HashMap;

/** The method for the checkout command.
 * @author Frank Cuoco
 * */
public class Checkout {

    /** Takes in a REPO and a string of ARGS and changes branches. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length == 2) {
            case1(args);
        }
        if (args.length == 3) {
            case2(args);
        }
        if (args.length == 1) {
            case3(args);
        }
    }

    /** Case 1 given ARGS. */
    public void case1(String[] args) {
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        String fileName = args[1];
        if (!stage.getCommitted().get(stage.getHead()).getBlobs()
                .containsKey(fileName)) {
            System.out.println("File does not exist in that commit.");
            return;
        }
        String sha1 = stage.getCommitted().get(stage.getHead())
                .getBlobs().get(fileName);
        Blob blob = Utils.readObject(Utils.join(".gitlet",
                "blobs", sha1), Blob.class);
        byte[] contents = blob.getContents();
        Utils.writeContents(new File(fileName), contents);
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }

    /** Case 1 given ARGS. */
    public void case2(String[] args) {
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        if (!args[1].equals("--")) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        String commitID = args[0];
        String fileName = args[2];
        if (!stage.getCommitted().containsKey(commitID)) {
            System.out.println("No commit with that id exists."); return;
        }
        if (!stage.getCommitted().get(commitID).getBlobs()
                .containsKey(fileName)) {
            System.out.println("File does not exist in that commit.");
            return;
        }
        String sha1 = stage.getCommitted().get(commitID).getBlobs()
                .get(fileName);
        Blob blob = Utils.readObject(Utils.join(
                ".gitlet", "blobs", sha1), Blob.class);
        byte[] contents = blob.getContents();
        Utils.writeContents(new File(fileName), contents);
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }

    /** Case 1 given ARGS. */
    public void case3(String[] args) {
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        String newBranch = args[0];
        if (!stage.getBranches().containsKey(newBranch)) {
            System.out.println("No such branch exists.");
            return;
        }
        if (newBranch.equals(stage.getCurrentBranch())) {
            System.out.println("No need to checkout the current branch.");
            return;
        }
        HashMap<String, String> myBlobs = stage.getCommitted()
                .get(stage.getHead()).getBlobs();
        for (String name: stage.getUntracked().keySet()) {
            if (myBlobs.containsKey(name)) {
                System.out.println("There is an untracked file "
                        + "in the way; delete it or add it first.");
                return;
            }
        }
        for (String filename: myBlobs.keySet()) {
            String sha1 = myBlobs.get(filename);
            Blob blob = Utils.readObject(Utils.join(".gitlet",
                    "blobs", sha1), Blob.class);
            byte[] contents = blob.getContents();
            Utils.writeContents(new File(filename), contents);
        }
        stage.clearStage();
        stage.setCurrentBranch(newBranch);
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }
}
