package leetcode;




/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/12/29 9:45
 * @Description -0->1->2->3->4    -0->2->1->3->4    2->1->4->3
 */
public class day24两两交换链表节点 {

    public ListNode<Integer> swapPairs(ListNode head){
        ListNode pre=new ListNode(-1);
        ListNode temp=pre;
        pre.next=head;
        while(head!=null&&head.next!=null){
            ListNode next=head.next;//next=2
            pre.next=next;//0->2
            head.next=next.next;//1->3
            next.next=head;//2->1
            pre=head;//pre=1
            head=head.next;//head=3 -0->2->1->3->4
        }
        return temp.next;
    }


    public static void main(String[] args) {
        ListNode integerList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<>(3, new ListNode<Integer>(4,new ListNode<Integer>(5)))));
        ListNode<Integer> integerListNode1 = new day24两两交换链表节点().swapPairs(integerList);
        Print(integerListNode1);
    }
    public static void Print(ListNode listNode){
        if(listNode !=null){
            System.out.println(listNode.getVal());
            Print(listNode.getNext());
        }
    }

}
class ListNode<E>{
    public E val;
    public ListNode next;
    public ListNode(E val){
        this.val = val;
    }
    public ListNode(E val, ListNode<E> next){
        this.val = val;
        this.next=next;
    }
    public ListNode(){
    }
    public E getVal(){
        return this.val;
    }
    public void setNext(ListNode next) {
        this.next = next;
    }
    public ListNode getNext(){
        return this.next;
    }

    public void setNode(int i, int data) {

    }
}