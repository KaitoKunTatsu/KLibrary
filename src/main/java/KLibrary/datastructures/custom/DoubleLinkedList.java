package KLibrary.datastructures.custom;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <br>
 * Part of the <a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>
 *
 * @version 1.2.0 | last edit: 29.10.2022
 * @author Joshua Hartjes | KaitoKunTatsu#3656
 */
public class DoubleLinkedList <T> {

    private DLLNode cursor;
    private DLLNode head;
    private DLLNode tail;

    private int size;

    public DoubleLinkedList() {
        this.cursor = null;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Creates a list
     *
     * @param pListToCopy   List whose elements are taken over
     * */
    public DoubleLinkedList(List<T> pListToCopy) {
        this();
        for (T element : pListToCopy)
            appendLast(element);
    }

    public void addBeforeCursor(T pContent) {
        if (head == null)
            addFirstElement(pContent);
        else if (cursor != null) {
            DLLNode lNextNode = new DLLNode(pContent, cursor.getPreviousNode(), cursor);
            if (cursor.getPreviousNode() != null)
                cursor.getPreviousNode().setNextNode(lNextNode);
            cursor.setPreviousNode(lNextNode);
        }
        ++size;
    }

    public void addAfterCursor(T pContent) {
        if (head == null)
            addFirstElement(pContent);
        else if (cursor != null) {
            DLLNode lNextNode = new DLLNode(pContent, cursor, cursor.getNextNode());
            if (cursor.getNextNode() != null)
                cursor.getNextNode().setPreviousNode(lNextNode);
            cursor.setNextNode(lNextNode);
        }
        ++size;
    }

    public void appendFirst(T pContent) {
        if (size == 0)
            addFirstElement(pContent);
        else {
            DLLNode lNewNode = new DLLNode(pContent, null, head);
            if (cursor.equals(head))
                cursor = lNewNode;
            head.setPreviousNode(lNewNode);
            head = lNewNode;
        }
        ++size;
    }

    public void appendLast(T pContent) {
        if (size == 0)
            addFirstElement(pContent);
        else {
            DLLNode lNewNode = new DLLNode(pContent, tail, null);
            if (cursor.equals(tail) && size > 1)
                cursor = lNewNode;
            tail.setNextNode(lNewNode);
            tail = lNewNode;
        }
        ++size;
    }

    private void addFirstElement(T pContent) {
        head = new DLLNode(pContent, null, null);
        tail = head;
        cursor = head;
    }

    public void moveCursorForward(int pAmount) {
        if (pAmount <= 0) return;
        if (cursor == null) cursor = head;

        for (int i = 0; i < pAmount && cursor != null; ++i) {
            moveCursorForward();
        }
    }

    public void moveCursorForward() {
        if (cursor != null)
            cursor = cursor.getNextNode();
        else
            cursor = head;
    }

    public void moveCursorBackward(int pAmount) {
        if (pAmount <= 0) return;
        if (cursor == null) cursor = tail;

        for (int i = 0; i < pAmount && cursor != null; ++i) {
            moveCursorBackward();
        }
    }

    public void moveCursorBackward() {
        if (cursor != null)
            cursor = cursor.getPreviousNode();
        else
            cursor = tail;
    }

    public void removeElementAtCursor() {
        if (cursor == null) return;

        if (cursor.getNextNode() == null && cursor.getPreviousNode() == null) {
            cursor = null;
            head = null;
            tail = null;
        }
        else if (cursor.getNextNode() != null && cursor.getPreviousNode() != null) {
            cursor.getPreviousNode().setNextNode(cursor.getNextNode());
            cursor.getNextNode().setPreviousNode(cursor.getPreviousNode());
            cursor = cursor.getPreviousNode();
        }
        else if (cursor.getNextNode() == null) {
            cursor.getPreviousNode().setNextNode(null);
            cursor = cursor.getPreviousNode();
            tail = cursor;
        }
        else {
            cursor.getNextNode().setPreviousNode(null);
            cursor = cursor.getNextNode();
            head = cursor;
        }
        --size;
    }

    public void toFirst() {
        cursor = head;
    }

    public void toLast() {
        cursor = tail;
    }

    public void setContentOfCursor(T pNewContent) {
        if (cursor != null)
            cursor.setContent(pNewContent);
    }

    public T getContent() {
        if (cursor == null) return null;
        return cursor.getContent();
    }

    public boolean hasNext() {
        return cursor != null && cursor.getNextNode() != null;
    }

    public boolean hasCurrentElement() {
        return cursor != null;
    }

    public int size() {
        return size;
    }

    private class DLLNode {

        private DLLNode previousNode;
        private DLLNode nextNode;

        private T content;

        public DLLNode(T pContent, DLLNode pPreviousNode, DLLNode pNextNode) {
            this.content = pContent;
            this.previousNode = pPreviousNode;
            this.nextNode = pNextNode;
        }

        public void setNextNode(DLLNode pNextNode) {
            this.nextNode = pNextNode;
        }

        public void setPreviousNode(DLLNode pPreviousNode) {
            this.previousNode = pPreviousNode;
        }

        public void setContent(T pContent) {
            this.content = pContent;
        }

        public T getContent() {
            return this.content;
        }

        public DLLNode getPreviousNode() {
            return this.previousNode;
        }

        public DLLNode getNextNode() {
            return this.nextNode;
        }
    }
}
