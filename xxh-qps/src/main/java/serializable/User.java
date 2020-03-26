package serializable;

import java.io.*;

public class User implements Externalizable {

    private String name;
    private String mobile;

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(name);
        objectOutput.writeObject(mobile);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        name =(String) objectInput.readObject();

    }
}
