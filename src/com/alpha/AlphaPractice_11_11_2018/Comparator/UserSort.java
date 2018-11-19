package com.alpha.AlphaPractice_11_11_2018.Comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/*
#10 Написать метод который возвращает повторяющихся в двух списках пользователей.
Вносить изменения в класс User нельзя
 */
public class UserSort {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<User> users1 = new ArrayList<>();


    public static int compare(User o1, User o2) {
        byte[] o_1 = o1.getUsername().getBytes();
        byte[] o_2 = o2.getUsername().getBytes();
        if (compareName(o_1, o_2)>0) return 1;
        else if (compareName(o_1, o_2)<0) return -1;
        else if (compareName(o_1, o_2)==0) {
            if (compareString(o1.getEmail(), o2.getEmail())>0) return 1;
            else if (compareString(o1.getEmail(), o2.getEmail())<0) return -1;
            else if (compareString(o1.getEmail(), o2.getEmail())==0)
                return compareName(o1.getPasswordHash(), o2.getPasswordHash());
        }
        return 0;
    }

    public static int compareString(String o1, String o2) {
        byte[] o_1 = o1.getBytes();
        byte[] o_2 = o2.getBytes();
        if (compareName(o_1, o_2)>0) return 1;
        else if (compareName(o_1, o_2)<0) return -1;
        else return 0;

    }
    public static int compareName(byte[] value, byte[] other) {
        int len1 = value.length;
        int len2 = other.length;
        int lim = Math.min(len1, len2);
        for (int k = 0; k < lim; k++) {
            if (value[k] != other[k]) {
                return getChar(value, k) - getChar(other, k);
            }
        }
        return len1 - len2;
    }
    public static char getChar(byte[] val, int index) {
        return (char)(val[index] & 0xff);
    }

    public static HashSet<String> searchForEqualsUsers(ArrayList<User> arrayList, ArrayList<User> arrayList1){
        HashSet<String> result = new HashSet<>();
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList1.size(); j++) {
                if (compare(arrayList.get(i), arrayList1.get(j))==0) result.add(arrayList.get(i).getUsername()
                + " " + arrayList.get(i).getEmail() + " "
                        + Arrays.toString(arrayList.get(i).getPasswordHash()));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        byte[] passHash = {1, 2, 4, 0};
        byte[] passHash1 = {1, 2, 4, 2};
        users.add(new User("Vasya", "vasya@mail.com", passHash));
        users.add(new User("Petya", "petya@mail.com", passHash));
        users.add(new User("Olya", "vasya@mail.com", passHash));
        users.add(new User("Gaya", "vasya@mail.com", passHash));
        users1.add(new User("Vasya", "vasya@mail.com", passHash));
        users1.add(new User("Petya", "petya@mail.com", passHash));
        users1.add(new User("Olya", "vasya@mail.com", passHash1));
        users1.add(new User("Gaya", "vasya@mail.com", passHash1));

        ArrayList<String> dUsers = new ArrayList<>();
        HashSet<String> doubleUsers = searchForEqualsUsers(users, users1);
        for (String doubleUser : doubleUsers) {
            dUsers.add(doubleUser);
        }
        System.out.println(dUsers.toString());
    }

}
