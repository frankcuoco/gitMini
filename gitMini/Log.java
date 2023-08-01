package gitlet;

import java.io.Serializable;

/** The log method.
 * @author Frank Cuoco
 * */
public class Log implements Serializable {

    /** Returns the log and takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 0) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        CommittedFile currentCommit = stage.getCommitted().get(stage.getHead());
        while (currentCommit.getParent() != null) {
            currentCommit.print();
            currentCommit = stage.getCommitted().get(currentCommit.getParent());
        }
        currentCommit.print();
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }
}
