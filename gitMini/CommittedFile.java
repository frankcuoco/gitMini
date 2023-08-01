package gitlet;

import java.io.Serializable;
import java.util.HashMap;

/** The method for a committed file object.
 * @author Frank Cuoco
 * */
public class CommittedFile implements Serializable {

    /** Initializes the object with a MESSAGE, TIMESTAMP, BRANCH,
     * PARENT, and BLOBS. */
    CommittedFile(String message, String timestamp,
                  String branch, String parent, HashMap<String, String> blobs) {
        _message = message;
        _timestamp = timestamp;
        _branch = branch;
        _parent = parent;
        _blobs = blobs;
        _sha1 = Utils.sha1(_timestamp + _message);
    }

    /** Returns the commit's message. */
    String getMessage() {
        return _message;
    }

    /** Returns the commit's timestamp. */
    public String getTimestamp() {
        return _timestamp;
    }

    /** Returns the commit's branch. */
    public String getBranch() {
        return _branch;
    }

    /** Returns the commit's parent. */
    public String getParent() {
        return _parent;
    }

    /** Returns the commit's sha1. */
    public String getSha1() {
        return _sha1;
    }

    /** Returns the commit's blobs <NAME, SHA1>. */
    public HashMap<String, String> getBlobs() {
        return _blobs;
    }

    /** Prints out the commit object in log form. */
    public void print() {
        System.out.println("===");
        System.out.println("commit " + _sha1);
        System.out.println("Date: " + _timestamp);
        System.out.println(_message);
        System.out.println("");
    }

    /** The message attached to the commit. */
    private String _message;
    /** The exact time at which the commit was made. */
    private String _timestamp;
    /** The branch the commit was made in. */
    private String _branch;
    /** The parent commit of this commit. */
    private String _parent;
    /** The unique sha1 id of this commit. */
    private String _sha1;
    /** The associated files with this commit. */
    private HashMap<String, String> _blobs;
}
