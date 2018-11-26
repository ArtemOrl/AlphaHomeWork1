package com.alpha.AlphaPractice_11_11_2018;

// К односвязному списку MyList

public class MyNode<T> {
    T value;
    MyNode<T> next = null;

    public MyNode(T value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "MyNode{" + value + "}";
    }
}
