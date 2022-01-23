package humphries.seminar;

import org.w3c.dom.Node;

import java.util.LinkedList;

public class ListWork {

    public void remove(Node head, int index){
        Node prev = null;
        if (head != null && head.getNodeValue() == Integer.toString(index)) {
            head = head.getNextSibling();
        }
        while (head != null && head.getNodeValue() != Integer.toString(index)) {
            prev = head;
            head = head.getNextSibling();
        }
        if (head == null)
            return;

    }
}
