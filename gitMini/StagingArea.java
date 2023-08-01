package gitlet;

import java.io.Serializable;
import java.util.HashMap;

/** The staging area.
 * @author Frank Cuoco
 * */
public class StagingArea implements Serializable {

    /** Initializes the HashMaps in the staging area. */
    public StagingArea() {
        tracked = new HashMap<String, String>();
        staged = new HashMap<String, String>();
        untracked = new HashMap<String, String>();
        removed = new HashMap<String, String>();
        committed = new HashMap<String, CommittedFile>();
        branches = new HashMap<String, String>();
    }

    /** Tracks a file given a NAME and a SHA1. */
    public void trackFile(String name, String sha1) {
        tracked.put(name, sha1);
    }

    /** Untracks a file given a NAME and a SHA1. */
    public void untrackFile(String name, String sha1) {
        untracked.put(name, sha1);
        if (tracked.containsKey(name)) {
            tracked.remove(name, sha1);
        }
    }

    /** Stages a file given a NAME and a SHA1. */
    public void stageFile(String name, String sha1) {
        staged.put(name, sha1);
    }

    /** Unstages a file given a NAME and a SHA1. */
    public void unstageFile(String name) {
        if (staged.containsKey(name)) {
            staged.remove(name);
        }
    }

    /** Removes a file given a NAME and a SHA1. */
    public void removeFile(String name, String sha1) {
        removed.put(name, sha1);
    }

    /** Removes a file from the removed HashMap given a NAME. */
    public void unRemoveFile(String name) {
        removed.remove(name);
    }

    /** Commits a file given a SHA1 and a COMMIT file. */
    public void commitFile(String sha1, CommittedFile commit) {
        committed.put(sha1, commit);
    }

    /** Adds a branch given a NAME and a HEADCOMMIT. */
    public void addBranch(String name, String headCommit) {
        branches.put(name, headCommit);
    }

    /** Removes a branch given a NAME and a HEAD commit. */
    public void removeBranch(String name) {
        branches.remove(name);
    }

    /** Returns the staged files. */
    public HashMap<String, String> getStaged() {
        return staged;
    }

    /** Returns the tracked files. */
    public HashMap<String, String> getTracked() {
        return tracked;
    }

    /** Returns the untracked files. */
    public HashMap<String, String> getUntracked() {
        return untracked;
    }

    /** Returns the removed files. */
    public HashMap<String, String> getRemoved() {
        return removed;
    }

    /** Returns the branches. */
    public HashMap<String, String> getBranches() {
        return branches;
    }

    /** Returns the most recent commit. */
    public String getHead() {
        return head;
    }

    /** Returns the committed files. */
    public HashMap<String, CommittedFile> getCommitted() {
        return committed;
    }

    /** Sets the most recent commit given a NEWHEAD. */
    public void setHead(String newHead) {
        head = newHead;
    }

    /** Returns the current branch. */
    public String getCurrentBranch() {
        return currentBranch;
    }

    /** Sets the BRANCH. */
    public void setCurrentBranch(String branch) {
        currentBranch = branch;
    }

    /** Clears the stage. */
    public void clearStage() {
        staged.clear();
    }

    /** Clears the removed files. */
    public void clearRemoved() {
        removed.clear();
    }

    /** HashMap of tracked files. */
    private HashMap<String, String> tracked;
    /** HashMap of staged files. */
    private HashMap<String, String> staged;
    /** HashMap of untracked files. */
    private HashMap<String, String> untracked;
    /** HashMap of removed files. */
    private HashMap<String, String> removed;
    /** HashMap of committed files. */
    private HashMap<String, CommittedFile> committed;
    /** HashMap of all branches and their head. */
    private HashMap<String, String> branches;
    /** The most recent commit. */
    private String head;
    /** The current branch. */
    private String currentBranch;
}
