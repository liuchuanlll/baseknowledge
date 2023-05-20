package leetcode;


import java.util.LinkedList;
import java.util.List;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/11/9 9:42
 * @Description
 */
public class day102 {
    public static List<List<Integer>> levelOrder(TreeNode treeNode){
        LinkedList<TreeNode> treeNodeList =new LinkedList<TreeNode>();
        if(treeNode==null) return null;

        treeNodeList.offer(treeNode);
        while (!treeNodeList.isEmpty()){
            int n=treeNodeList.size();

            for(int i=0;i<n;i++){
                TreeNode node = treeNodeList.remove();
                System.out.println(node.val);
                if(node.left!=null) treeNodeList.add(node.left);
                if(node.right!=null) treeNodeList.add(node.right);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode treeNode=new TreeNode(3,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7)));
        levelOrder(treeNode);
    }
}


class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode (int val){this.val=val;}
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }
}