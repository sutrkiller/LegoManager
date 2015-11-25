package cz.muni.fi.pa165.lego;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public class Utils {

    public static <T extends Object> boolean equalsUnordered(Collection<T> c1, Collection<T> c2) {
        Set<T> set1 = new HashSet<>();
        set1.addAll(c1);
        Set<T> set2 = new HashSet<>();
        set2.addAll(c2);
        return set1.equals(set2);
    }

}
