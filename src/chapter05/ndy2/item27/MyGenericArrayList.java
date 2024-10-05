package chapter05.ndy2.item27;

import java.util.AbstractList;
import java.util.List;

public class MyGenericArrayList<E> extends AbstractList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    // ERROR
    // 'chapter05.ndy2.item27.MyGenericArrayList.this' cannot be referenced from a static context
    private static final E[] EMPTY_ELEMENTDATA = {};

    private static final E[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient E[] elementData;

    public MyGenericArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            // ERROR
            // Type parameter 'E' cannot be instantiated directly
            this.elementData = (E[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
