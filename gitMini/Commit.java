package gitlet;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/** The method for the committed command.
 * @author Frank Cuoco
 * */
public class Commit implements Serializable {

    /** Takes in a REPO and strings of ARGS and commits a file. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        if (args[0].equals("")) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(new
                File(".gitlet/stage/staging.ser"), StagingArea.class);
        String message = args[0];
        if (stage.getRemoved().isEmpty() && stage.getStaged().isEmpty()
                && !message.equals("initial commit")) {
            System.out.println("No changes added to the commit.");
            return;
        }
        for (String key: stage.getStaged().keySet()) {
            stage.trackFile(key, stage.getStaged().get(key));
        }
        for (String file: stage.getRemoved().keySet()) {
            stage.untrackFile(file, stage.getRemoved().get(file));
            stage.clearRemoved();
        }
        HashMap<String, String> blobs = new HashMap<String, String>();
        for (String name: stage.getStaged().keySet()) {
            blobs.put(name, stage.getStaged().get(name));
        }
        _commit = new CommittedFile(message, new SimpleDateFormat(
                        "EEE MMM dd hh:mm:ss YYYY Z").format(new Date()),
                stage.getCurrentBranch(), stage.getHead(), blobs);
        File f = Utils.join(".gitlet", "commits", _commit.getSha1());
        Utils.writeObject(f, _commit);
        stage.commitFile(_commit.getSha1(), _commit);
        stage.setHead(_commit.getSha1());
        stage.clearStage();
        Utils.writeObject(new File(".gitlet/stage/staging.ser"), stage);
    }

    /** Returns the committed file object. */
    public CommittedFile getCommit() {
        return _commit;
    }

    /** The current commit object. */
    private CommittedFile _commit;
}
