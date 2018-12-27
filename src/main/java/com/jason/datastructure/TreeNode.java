package com.jason.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *          1
         2.      3
     4.   5.   6.   7
             8
 */
class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private int val;

    public TreeNode(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode seleft = root.left = new TreeNode(2);
        TreeNode seright = root.right = new TreeNode(3);
        seleft.left = new TreeNode(4);
        seleft.right = new TreeNode(5);
        TreeNode six=seright.left = new TreeNode(6);
        seright.right = new TreeNode(7);
        six.left=new TreeNode(8);
        // 非递归实现
        postOrder(root);
        System.out.println("-----------分割线------------");
        // 递归实现
        postOrderNonRecursive(root);
        System.out.println("--------先序遍历--------------");
        preOrder(root);
        System.out.println("------------两种遍历优先----------");
        printfromtoptobottom(root);
    }

    /**
     * 广度优先遍历
     * @Param root
     */
    public static void printfromlefttoright(TreeNode root){
        Queue<TreeNode> queue =new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(temp.left!=null){
                queue.add(temp.left);
            }

            if(temp.right!=null){
                queue.add(temp.right);
            }
            System.out.println(temp.val);
        }
    }

    public static  void printfromtoptobottom(TreeNode root){
        Stack<TreeNode> stack =new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            TreeNode temp =stack.pop();

            if(temp.right!=null){
                stack.push(temp.right);
            }

            if(temp.left!=null){
                stack.push(temp.left);
            }
            System.out.println(temp.val);
        }
    }

    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }

    public static void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.val);
        }

    }

    public static void preOrderNonRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp;
        stack.push(root);
        //栈里除了root节点外，存放的永远是右节点
        while (!stack.empty()) {
            temp = stack.pop();
            while (temp != null) {
                System.out.println(temp.val);
                stack.push(temp.right);
                temp = temp.left;
            }
        }

    }

    public static void inOrderNonRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        // 存放的都是根节点，每次输出根节点的同时，确保会一直遍历完左子树。
        while (temp != null || !stack.empty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            System.out.println(temp.val);
            temp = temp.right;

        }
    }


    public static  void postOrderNonRecursive(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();

        TreeNode temp = root;
        // 存放的都是根节点，每次输出根节点的同时，确保会一直遍历完左子树。
        while (temp != null || !stack.empty()) {
            // 将子树全部放入，并对应一个0位表示
            while (temp != null) {
                stack.push(temp);
                stack1.push(0);
                temp = temp.left;
            }

            while(!stack.empty() && stack1.peek().equals(1)){
                stack1.pop();
                System.out.println(stack.pop().val);
            }


            if(!stack.empty()) {
                stack1.pop();
                stack1.push(1);
                temp = stack.peek();
                temp = temp.right;
            }

        }


    }


}
