package com.alpha.MyLinkedList;

// Реализация двусвязного списка
// Не закончена
public class LinkedListElement<T> {
    T data;
    LinkedListElement<T> next;
    LinkedListElement<T> previous;

    public static void main(String[] args) {
        LinkedListElement<Integer> tail = null; //хвост (самый последний элемент) списка

        for(int i=0;i<10;i++)
        {
            LinkedListElement<Integer> element = new LinkedListElement<Integer>();
            element.data = i;

            if (tail == null) //если в хвосте нет элементов, сделать наш элемент последним
            {
                tail = element;
            }
            else //если хвост есть, добавить элемент
            {
                tail.next = element; //добавляем хвосту ссылку на следующий элемент
                element.previous = tail; //добавляем новому элементу ссылку на хвост
                tail = element; //объявляем новый элемент хвостом.
            }
        }
    }

//    public static LinkedListElement<T> addElement(LinkedListElement<T> element){
//        //тут содержится элемент – голова списка
//        LinkedListElement<Integer> head = …
//
//        //получаем 4-й элемент (нумерация с нуля)
//        LinkedListElement<Integer> element4 = head.next.next.next.next;
//        //получаем 5-й элемент
//        LinkedListElement<Integer> element5 = element4.next;
//
//        //Создаем новый элемент, который будем вставлять
//        LinkedListElement<Integer> newElement = new LinkedListElement<Integer>();
//        newElement.data = -18;
//
//        //обмениваемся ссылками с элементом слева
//        newElement.previous = element4;
//        element4.next = newElement;
//
//        //обмениваемся ссылками с элементом справа
//        newElement.next = element5;
//        element5.previous = newElement;
//    }
}
