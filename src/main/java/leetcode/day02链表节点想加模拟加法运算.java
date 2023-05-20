package leetcode;
/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/4/1 10:04
 * @Description
 */

public class day02链表节点想加模拟加法运算 {
    ListNode head=null,tail=null;

    public ListNode SumNodea(ListNode<Integer> l1, ListNode<Integer> l2){
    int carry=0;
    while (l1!=null||l2!=null){
        int n1=l1==null?0:l1.val;
        int n2=l2==null?0:l1.val;
        int sum=n1+n2+carry;
        if(head==null){
            head=tail=new ListNode(sum%10);
        }else{
            tail.next=new ListNode(sum%10);
            tail=tail.next;
        }
        carry=sum/10;
        if(l1!=null){l1=l1.next;}
        if(l2!=null){l2=l2.next;}
    }
    if(carry>0){
        tail.next=new ListNode(carry);
    }
        return head;
    }

    public static ListNode SumNodeRecur(ListNode<Integer> l1, ListNode<Integer> l2,int bit){
        if(l1==null&&l2==null&&bit==0){
            return null;
        }
        int val=bit;
        if(l1!=null){
            val=val+l1.val;
            l1=l1.next;
        }
        if(l2!=null){
            val=val+l2.val;
            l2=l2.next;
        }
        ListNode node =new ListNode(val%10);
        node.next=SumNodeRecur(l1,l2,val/10);
        return node;
    }

}