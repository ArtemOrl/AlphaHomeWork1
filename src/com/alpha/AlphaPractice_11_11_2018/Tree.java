package com.alpha.AlphaPractice_11_11_2018;

public class Tree<T> {
    T value;
    Tree<T> left;
    Tree<T> right;

    public Tree(T value, Tree<T> left, Tree<T> right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

}
