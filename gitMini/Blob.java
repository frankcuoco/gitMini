package gitlet;

import java.io.Serializable;

/** The method for a blob object.
 * @author Frank Cuoco
 * */
public class Blob implements Serializable {

    /** Initializes a blob based on its inputted CONTENTS and NAME. */
    public Blob(byte[] contents, String name) {
        _contents = contents;
        _name = name;
        _sha1 = Utils.sha1(_contents, _name);
    }

    /** Returns the contents of the blob. */
    public byte[] getContents() {
        return _contents;
    }

    /** Returns the name of the blob. */
    public String getName() {
        return _name;
    }

    /** Returns the sha1 of the blob. */
    public String getSha1() {
        return _sha1;
    }

    /** RETURNS true if T is equal to the current blob B. */
    public boolean isEqual(Blob b) {
        return this.getSha1().equals(b.getSha1());
    }

    /** The contents of this blob. */
    private byte[] _contents;
    /** The name of this blob. */
    private String _name;
    /** The unique sha1 id of this blob. */
    private String _sha1;
}
