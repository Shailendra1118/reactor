package com.example.design;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InterleavingItr {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(10,20,30);
        List<Integer> list2 = Arrays.asList(11,22,33);

        Iterator<Integer> [] iterators = new Iterator[]{list1.iterator(), list2.iterator()};

        InterLeavingIterator interLeavingIterator = new InterLeavingIterator(iterators);
        while(interLeavingIterator.hasNext()){
            System.out.println(interLeavingIterator.next());
        }

    }

}


class InterLeavingIterator<T> implements Iterator<T>{

    Iterator<T>[] iterators;
    public InterLeavingIterator(Iterator<T> [] iterators){
        this.iterators = iterators;
    }

    int cursor;

    @Override
    public boolean hasNext() {
        //if(cursor != iterators.)
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}

