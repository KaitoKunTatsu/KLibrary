package KLibrary.datastructures.custom;

import java.util.List;

/**
 * (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 *
 * @version stable-1.2.0 | last edit: 29.10.2022
 * @author Joshua H. | KaitoKunTatsu#3656
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

    public DoubleLinkedList(List<T> pListToCopy) {
        this();
        for (T element : pListToCopy)
            appendLast(element);
    }

    public void addBeforeCursor(T pContent) {
        if (head == null)
            addAsHead(pContent);
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
            addAsHead(pContent);
        else if (cursor != null) {
            DLLNode lNextNode = new DLLNode(pContent, cursor, cursor.getNextNode());
            if (cursor.getNextNode() != null)
                cursor.getNextNode().setPreviousNode(lNextNode);
            cursor.setNextNode(lNextNode);
        }
        ++size;
    }

    public void appendFirst(T pContent) {
        if (head == null)
            addAsHead(pContent);
        else {
            DLLNode lNewNode = new DLLNode(pContent, null, head);
            reasignCursorIfHeadOrTail(lNewNode);
            head.setPreviousNode(lNewNode);
            head = lNewNode;
        }
        ++size;
    }

    public void appendLast(T pContent) {
        if (head == null)
            addAsHead(pContent);
        else {
            DLLNode lNewNode = new DLLNode(pContent, tail, null);
            reasignCursorIfHeadOrTail(lNewNode);
            tail.setNextNode(lNewNode);
            tail = lNewNode;
        }
        ++size;
    }

    private void reasignCursorIfHeadOrTail(DLLNode pNewCursorNode) {
        if (cursor.equals(head))
            cursor = pNewCursorNode;
        else if (cursor.equals(tail))
            cursor = pNewCursorNode;
    }

    private void addAsHead(T pContent) {
        head = new DLLNode(pContent, null, null);
        tail = head;
        cursor = head;
    }

    public void moveCursorForward(int pAmount) {
        if (pAmount <= 0) return;
        if (cursor == null) cursor = head;

        for (int i = 1; i < pAmount && cursor != null; ++i) {
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
