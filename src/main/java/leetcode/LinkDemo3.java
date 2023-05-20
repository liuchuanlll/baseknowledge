package leetcode;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/3/11 11:00
 * @Description
 */


public class LinkDemo3 implements Cloneable{
//--------Link类中保存的成员-----------------------------------
    public ListNode<Integer> root,tail;
    int foot;//数组角标
    int count;//链表长度

    // --------Link类中保存的方法-----------------------------
    public void addNode(int e) {
        ListNode newNode=new ListNode(e);
        if(this.root==null){
            this.root=this.tail=newNode;
            count=1;
        } else {
            tail.next=newNode;//尾同步
            tail=tail.next;
            this.count++;
        }
    }
    public void Print() {
        ListNode myNode=root;
        while(myNode!=null){
            System.out.print(myNode.val+" ");
            myNode=myNode.next;//头不同步
        }
        System.out.println();
    }

    public int get(int i) {
        ListNode myNode=root;//头不同步，尾同步
        while(myNode!=null){
            myNode=myNode.next;
            foot++;
            if(foot>=i){
                break;
            }
        }
        return (Integer) myNode.val;
    }
    public void InsertNode(int i,int data) {
        ListNode ListNode=new ListNode(data);
        if(i>this.count){
            return;//无返回值，方法结束
        }
        this.foot=0;//脚标清零
        ListNode cur=root;
        while(foot!=i-1){
            foot++;
            cur=cur.next;
        }
        //root 1->2->3->4
        //cur        3->4
        ListNode.next=cur.next;
        //root 1-->2-->3-->4
        //cur        3-->4
        //ListNode         5/
        cur.next=ListNode;
        //root 1-->2-->3\   4
        //               5/
    }
    public void SetNode(int i,int data) {
        ListNode ListNode=new ListNode(data);
        if(i>this.count){
            return;//无返回值，方法结束
        }
        this.foot=0;//脚标清零
        ListNode cur=root;
        while(foot!=i-1){
            foot++;
            cur=cur.next;
        }
        ListNode.next=cur.next.next;
        cur.next=ListNode;

    }

    public boolean contain(int data) {
        ListNode<Integer> myNode=root;//头不同步，尾同步
        while (myNode.next!=null){
            if(myNode.val==data){
                return true;
            }
            myNode=myNode.next;
        }
        return false;
    }

    public void removeNode(int i) {
        if(i>this.count){
            return;//无返回值，方法结束
        }
        this.foot=0;//脚标清零
        ListNode cur=root;
        while(foot!=i-1){
            foot++;
            cur=cur.next;
        }
        cur.next=cur.next.next;

    }

    public Object[] toArray() {
        Object[] result = new Object[count];
        int i = 0;
        for (ListNode<Integer> x = root; x != null; x = x.next)
            result[i++] = x.next;
        return result;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        LinkDemo3 ListNode = new LinkDemo3();
        ListNode.addNode(11);
        ListNode.addNode(22);
        ListNode.addNode(33);
        ListNode.addNode(44);
        ListNode.Print();
        System.out.println(ListNode.get(2));
        ListNode.SetNode(2,66);
        ListNode.Print();
        ListNode.InsertNode(3,55);
        ListNode.Print();
        ListNode.removeNode(3);

    }

}
