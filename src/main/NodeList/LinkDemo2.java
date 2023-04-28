/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/3/11 11:00
 * @Description
 */

class Link<E> {
    private class Node{
        E data;
        Node next;
        public Node(E data){
            this.data=data;
        }
        public void addNode(Node newNode) {
             if(this.next==null){
                 this.next=newNode;
             } else {
                 this.next.addNode(newNode);
             }
        }
        public void Print() {
            System.out.println(data);
            if(this.next!=null){
                this.next.Print();
            }
        }
        public void toArraryNode() {
            Link.this.returnData[Link.this.foot++]=data;
            if(this.next!=null){
                this.next.toArraryNode();
            }
        }

        public E getNode(int i) {
            if(Link.this.foot++==i){
                return this.data;
            } else {
               return this.next.getNode(i);
            }
        }
        public void setNode(int i,E data) {
            if(Link.this.foot++==i){
                 this.data=data;
            } else{
                 this.next.setNode(i,data);
            }
        }

        public boolean contain(E data) {
            if(data.equals(this.data)){
                return true;
            }else{
                if(this.next==null){
                    return false;
                }else{
                    return this.next.contain(data);
                }
            }
        }

        public void removeNode(Node pre, E data) {
            if(this.data.equals(data)){
                pre.next=this.next;
                if(this.next!=null){
                    this.next.removeNode(pre,data);
                }
            } else{
                if(this.next!=null){
                    this.next.removeNode(this,data);
                }
            }
        }
    }

//--------Link类中保存的成员-----------------------------------
    private Node root;
    private int count;
    int foot;//数组角标
    Object[] returnData;
// --------Link类中保存的方法-----------------------------
    public void add(E e) {
        if(e==null){
            return;
        }
        Node newNode=new Node(e);
        if(this.root==null){
            this.root=newNode;
        } else {
            this.root.addNode(newNode);
        }
        this.count++;
    }
    public int Size() {
        return this.count;
    }

    public void Print() {
        if(this.root!=null){
            this.root.Print();
        }
    }
    public Object[] toArrary() {
        if(this.count==0){
            return null;
        }
        this.foot=0;//脚标清零
        this.returnData=new Object[this.count];
        this.root.toArraryNode();
        return returnData;
    }

    public E get(int i) {
        if(i>this.count){
            return null;
        }
        this.foot=0;//脚标清零
        return this.root.getNode(i);
    }
    public void set(int i,E data) {
        if(i>this.count){
            return;//无返回值，方法结束
        }
        this.foot=0;//脚标清零
        this.root.setNode(i,data);
    }
    public boolean contains(E data) {
        if(data==null){
            return false;
        }
        return this.root.contain(data);
    }
    public void remove(E data) {
        if(this.contains(data)){
           if(data.equals(root.data)){
               this.root=this.root.next;//此处不完整
           }else{
               this.root.next.removeNode(this.root,data);
           }
           this.count--;
        }
    }
}
public class LinkDemo2 {
    public static void main(String[] args) {
        Link<String> stringLink = new Link<String>();
        stringLink.add("11");
        stringLink.add("11");
        stringLink.add("11");
        stringLink.add("22");
        stringLink.add("22");
        stringLink.add("22");
//        stringLink.Print();
//        stringLink.Print();
//        System.out.println(stringLink.Size());
//        Object[] arraryList= stringLink.toArrary();
//       for(Object obj:arraryList){
//           System.out.println(obj);
//       }
//        ArrayList
        stringLink.remove("11");
        stringLink.Print();

    }

}
