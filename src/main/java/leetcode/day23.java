package leetcode;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/11/10 12:31
 * @Description �ϲ�K������
 */

public class day23 {
    public ListNode listNode(ListNode[] listNodes){
        ListNode res=new ListNode(-1);
        int length=listNodes.length;
        if(listNodes==null||length==0) return null;
        if(length==1) return listNodes[0];
        while(length>1){
            for(int i=0;i<listNodes.length/2;i++){
                listNodes[i]=listNode2(listNodes[i],listNodes[length-i+1]);
            }
            length=length/2+length%2;
        }
        return listNodes[0];
    }
    public ListNode listNode2(ListNode<Integer> listNode1, ListNode<Integer> listNode2){
        ListNode res=new ListNode(-1);
        ListNode temp=res;
        if(listNode1==null) return listNode2;
        if(listNode2==null) return listNode1;
        while (listNode1!=null&&listNode2!=null){
            if(listNode1.val<listNode2.val){
                temp=temp.next;
                temp.val=listNode1.val;
                listNode1=listNode1.next;
            }else{
                temp=temp.next;
                temp.val=listNode2.val;
                listNode2=listNode2.next;
            }
        }
        if(listNode1!=null) temp.next=listNode1;
        if(listNode2!=null) temp.next=listNode2;
        return res.next;
    }
}
