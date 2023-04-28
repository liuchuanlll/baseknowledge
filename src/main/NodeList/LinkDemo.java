/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/3/11 10:14
 * @Description
 */
class Node<E>{
    public E data;
    public Node next;
    public Node(E data){
        this.data = data;
    }
    public Node(){
    }
    public E getData(){
       return this.data;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public Node getNext(){
        return this.next;
    }

    public void setNode(int i, int data) {

    }
}
public class LinkDemo {
    public static void main(String[] args) {
        Node<String> node1=new Node<String>("火车头");
        Node<String> node2=new Node<String>("车厢1");
        Node<String> node3=new Node<String>("车厢2");
        node1.setNext(node2);
        node2.setNext(node3);
        Print(node1);
    }
    public static void Print(Node node){
       if(node !=null){
           System.out.println(node.getData());
           Print(node.getNext());
       }
    }

}
