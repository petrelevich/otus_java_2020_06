package ru.otus;

import java.util.*;

public class DIYarrayList<T> implements List<T> {
    public static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData;
    private int size;

    public DIYarrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            try {
                this.elementData = new Object[initialCapacity];
            }catch (ClassCastException e){
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public DIYarrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new DIYarrayList.Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (size == elementData.length) {
            Object[] bufList = elementData;
            if(size >= MAX_ARRAY_LENGTH){
                throw new IllegalStateException("Not enough memory for adding new element.");
            }
            int newSize = Math.min(size * 2, MAX_ARRAY_LENGTH);
            elementData = new Object[newSize];
            System.arraycopy(bufList, 0, elementData, 0, size);
        }
        elementData[size] = t;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        if((index < 0)||(index >= size)){
            throw new IndexOutOfBoundsException();
        }

        T res = null;
        try {
            res = (T) elementData[index];
        }catch (ClassCastException e){
            throw new ClassCastException();
        }

        return res;
    }

    @Override
    public T set(int index, T element) {
        T oldValue = null;
        try {
            oldValue = (T) elementData[index];
        }catch (ClassCastException e){
            throw new ClassCastException();
        }

        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }


    private class Itr implements Iterator<T> {
        int cursor = 0;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size();
        }

        public T next() {
            try {
                int i = cursor;
                T next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                DIYarrayList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<T>{
        ListItr(int index) {
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();

            T res = null;
            try {
                res = (T) elementData[lastRet = cursor];
            }catch (ClassCastException e){
                throw new ClassCastException();
            }

            cursor = i;
            return res;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(T e) {
            if (lastRet < 0)
                throw new IllegalStateException();

            DIYarrayList.this.set(lastRet, e);
        }

        @Override
        public void add(T e) {
            DIYarrayList.this.add(cursor++, e);
            lastRet = -1;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYarrayList.ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        Iterator<T> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            T e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}