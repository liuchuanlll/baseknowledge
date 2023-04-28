/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/5/5 17:41
 * @Description
 */
public class day04两个有序链表排序 {
    Node<Integer> root,tail;
    int foot;

    public static Node paixulianbiao(Node<Integer> myNode1,Node<Integer> myNode2) {
        Node nodeList = new Node<Integer>(-1);
        Node nodeList2 = nodeList;
        if (myNode1 == null || myNode2 == null) {
            throw new RuntimeException();
        }
        while (myNode1.next != null && myNode2.next != null) {
            if (myNode1.data <= myNode2.data) {
                nodeList.next = myNode1;
                myNode1=myNode1.next;
            } else {
                nodeList.next = myNode2;
                myNode2=myNode2.next;
            }
            nodeList = nodeList.next;
        }
        return nodeList2.next;
    }
    public static void main(String[] args) {
        Node<Integer> node1=new Node<Integer>(1);
        Node<Integer> node2=new Node<Integer>(3);
        Node<Integer> node3=new Node<Integer>(5);
        node1.setNext(node2);
        node2.setNext(node3);

        Node<Integer> node4=new Node<Integer>(1);
        Node<Integer> node5=new Node<Integer>(2);
        Node<Integer> node6=new Node<Integer>(4);
        Node<Integer> node7=new Node<Integer>(6);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        Node paixulianbiao = paixulianbiao(node1, node4);
        Print(paixulianbiao);
    }
    public static void Print(Node node){
        if(node !=null){
            System.out.println(node.getData());
            Print(node.getNext());
        }
    }
}
