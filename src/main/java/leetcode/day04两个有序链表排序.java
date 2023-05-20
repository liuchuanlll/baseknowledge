package leetcode;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/5/5 17:41
 * @Description
 */
public class day04两个有序链表排序 {
    ListNode<Integer> root,tail;
    int foot;

    public static ListNode<Integer> paixulianbiao(ListNode<Integer> myNode1,ListNode<Integer> myNode2) {
        ListNode<Integer> nodeList = new ListNode<Integer>(-1);
        ListNode<Integer> nodeList2 = nodeList;
        if (myNode1 == null || myNode2 == null) {
            throw new RuntimeException();
        }
        while (myNode1.next != null && myNode2.next != null) {
            if (myNode1.val <= myNode2.val) {
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
        ListNode<Integer> node1=new ListNode<Integer>(1);
        ListNode<Integer> node2=new ListNode<Integer>(3);
        ListNode<Integer> node3=new ListNode<Integer>(5);
        node1.setNext(node2);
        node2.setNext(node3);

        ListNode<Integer> node4=new ListNode(1);
        ListNode<Integer> node5=new ListNode(2);
        ListNode<Integer> node6=new ListNode(4);
        ListNode<Integer> node7=new ListNode(6);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        ListNode paixulianbiao = paixulianbiao(node1, node4);
        Print(paixulianbiao);
    }
    public static void Print(ListNode node){
        if(node !=null){
            System.out.println(node.getVal());
            Print(node.getNext());
        }
    }
}
