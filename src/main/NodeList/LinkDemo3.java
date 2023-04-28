/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/3/11 11:00
 * @Description
 */


public class LinkDemo3 implements Cloneable{
//--------Link类中保存的成员-----------------------------------
    public Node<Integer> root,tail;
    int foot;//数组角标
    int count;//链表长度

    // --------Link类中保存的方法-----------------------------
    public void addNode(int e) {
        Node newNode=new Node<Integer>(e);
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
       Node myNode=root;
        while(myNode!=null){
            System.out.print(myNode.data+" ");
            myNode=myNode.next;//头不同步
        }
        System.out.println();
    }

    public int get(int i) {
        Node myNode=root;//头不同步，尾同步
        while(myNode!=null){
            myNode=myNode.next;
            foot++;
            if(foot>=i){
                break;
            }
        }
        return (Integer) myNode.data;
    }
    public void InsertNode(int i,int data) {
        Node node=new Node(data);
        if(i>this.count){
            return;//无返回值，方法结束
        }
        this.foot=0;//脚标清零
        Node cur=root;
        while(foot!=i-1){
            foot++;
            cur=cur.next;
        }
        //root 1->2->3->4
        //cur        3->4
        node.next=cur.next;
        //root 1-->2-->3-->4
        //cur        3-->4
        //node         5/
        cur.next=node;
        //root 1-->2-->3\   4
        //               5/
    }
    public void SetNode(int i,int data) {
        Node node=new Node(data);
        if(i>this.count){
            return;//无返回值，方法结束
        }
        this.foot=0;//脚标清零
        Node cur=root;
        while(foot!=i-1){
            foot++;
            cur=cur.next;
        }
        node.next=cur.next.next;
        cur.next=node;

    }

    public boolean contain(int data) {
        Node<Integer> myNode=root;//头不同步，尾同步
        while (myNode.next!=null){
            if(myNode.data==data){
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
        Node cur=root;
        while(foot!=i-1){
            foot++;
            cur=cur.next;
        }
        cur.next=cur.next.next;

    }

    public Object[] toArray() {
        Object[] result = new Object[count];
        int i = 0;
        for (Node<Integer> x = root; x != null; x = x.next)
            result[i++] = x.next;
        return result;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        LinkDemo3 node = new LinkDemo3();
        node.addNode(11);
        node.addNode(22);
        node.addNode(33);
        node.addNode(44);
        node.Print();
        System.out.println(node.get(2));
        node.SetNode(2,66);
        node.Print();
        node.InsertNode(3,55);
        node.Print();
        node.removeNode(3);

    }

}
