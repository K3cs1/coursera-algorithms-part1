package org.coursera.module5.permutation;

import org.coursera.module5.shell_short.ShellShort;

public class ArrayPermutation {

    public boolean arePermutations(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }

        ShellShort shellShort = new ShellShort(a);
        a = shellShort.sort();
        shellShort = new ShellShort(b);
        b = shellShort.sort();

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

}
