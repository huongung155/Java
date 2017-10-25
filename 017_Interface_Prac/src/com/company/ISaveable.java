package com.company;

import java.util.List;

/**
 * Created by M4800 on 20-Nov-16.
 */
public interface ISaveable {
    List<String> write();
    void read(List<String> savedValues);
}
