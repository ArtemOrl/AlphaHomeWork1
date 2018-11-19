package com.alpha.AlphaPractice_11_11_2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
№6. Создайте структуру с именем student, содержащую поля: фамилия и инициалы,
номер группы, успеваемость (список). Создать структуру all students, хранящую
в себе список студентов, добавить возможность вывода фамилий и номеров групп студентов,
имеющих оценки, равные только 4 или 5, добавить возможность вывода фамилий и номеров групп
студентов, имеющих средний бал 4.5. , вывод всех студентов в отсортированном по фамилии и
инициалам и номеру группы порядке, и вывод студентов по номеру группы.
*/

// 1. Сортировка студентов по фамилиям с учетом сортировки по группе
// 2. Сортировка студентов по группам + фамилия
// 3. Сортировка по среднему баллу (по убыванию)
// 4. Сортировка по среднему балллу (такой же или больше)
// 5. Сортировка студенты с оценками только 4 или 5

public class StudentSort {
    static ArrayList<Student> students = new ArrayList<>();

    static Comparator<Student> compareNamesGroups = new Comparator<Student>(){
        @Override
        public int compare(Student o1, Student o2) {
            byte[] o_1 = o1.lastName.getBytes();
            byte[] o_2 = o2.lastName.getBytes();
            if (compareName(o_1, o_2)>0) return 1;
            else if (compareName(o_1, o_2)<0) return -1;
            else if (compareName(o_1, o_2)==0) return compareString(o1.studGroup, o2.studGroup);
            return 0;
        }

        public int compareString(String o1, String o2) {
            byte[] o_1 = o1.getBytes();
            byte[] o_2 = o2.getBytes();
            if (compareName(o_1, o_2)>0) return 1;
            else if (compareName(o_1, o_2)<0) return -1;
            else return 0;

        }
        public int compareName(byte[] value, byte[] other) {
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
        public char getChar(byte[] val, int index) {
            return (char)(val[index] & 0xff);
        }
    };

    static Comparator<Student> compareAverageRating = new Comparator<Student>(){

        // сортировка идет от большего к меньшему (если что меняем местами -1 и 1)
        @Override
        public int compare(Student o1, Student o2) {
            if (getAvergaeRating(o1)>getAvergaeRating(o2)) return -1;
            if (getAvergaeRating(o1)<getAvergaeRating(o2)) return 1;
            else return 0;
        }

        public float getAvergaeRating (Student o){
            byte[] scores = o.grades;
            float summ = 0;
            float count = 0;
            for (int i = 0; i < scores.length; i++) {
                summ += scores[i];
                count++;
            }
            return summ/count;
        }

    };

    static Comparator<Student> compareAverageRating1 = new Comparator<Student>(){

        // сортировка идет от меньшего к большему
        @Override
        public int compare(Student o1, Student o2) {
            if (getAvergaeRating(o1)>getAvergaeRating(o2)) return 1;
            if (getAvergaeRating(o1)<getAvergaeRating(o2)) return -1;
            else return 0;
        }

        public float getAvergaeRating (Student o){
            byte[] scores = o.grades;
            float summ = 0;
            float count = 0;
            for (int i = 0; i < scores.length; i++) {
                summ += scores[i];
                count++;
            }
            return summ/count;
        }

    };

    static boolean checkRatings (Student student){
        boolean isGoodRating = true;
        byte[] ratings = student.grades;
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i]<4) isGoodRating = false;
        }
        return isGoodRating;
    }

    // Сортировка студентов по фамилиям с учетом сортировки по группе
    static ArrayList<String> namesOfStudents (ArrayList<Student> students){
        Collections.sort(students, compareNamesGroups);
        ArrayList<String> names = new ArrayList<>();
        for (Student student : students) {
            names.add(student.lastName + " group[" + student.studGroup + "]");
        }
        return names;
    }

    // Сортировка студентов по группам + фамилия
    static ArrayList<String> groupAndLastName (ArrayList<Student> students, String groupName){
        ArrayList<String> names = new ArrayList<>();
        for (Student student : students) {
            if (student.studGroup.equals(groupName))
            names.add( "group[" + student.studGroup + "] student - " + student.lastName);
        }
        Collections.sort(names);
        return names;
    }

    static float getAvergaeRating (Student o){
        byte[] scores = o.grades;
        float summ = 0;
        float count = 0;
        for (int i = 0; i < scores.length; i++) {
            summ += scores[i];
            count++;
        }
        return summ/count;
    }

    // Сортировка по среднему баллу (по убыванию)
    static ArrayList<String> averageRating (ArrayList<Student> students){
        Collections.sort(students, compareAverageRating);
        ArrayList<String> names = new ArrayList<>();
        for (Student student : students) {
            names.add(student.lastName + " Average_Rating[" + getAvergaeRating(student) + "]");
        }
        return names;
    }

    // Сортировка по среднему балллу (такой же или больше)
    static ArrayList<String> averageRatingThis (ArrayList<Student> students, Float averageRating){
        Collections.sort(students, compareAverageRating1);
        ArrayList<String> names = new ArrayList<>();
        for (Student student : students) {
            if (getAvergaeRating(student)>=averageRating)
            names.add(student.lastName + " Average_Rating[" + getAvergaeRating(student) + "]");
        }
        return names;
    }

    // Студенты с оценками только 4 или 5
    static ArrayList<String> studentsWithGoodRatings (ArrayList<Student> students){
        ArrayList<String> names = new ArrayList<>();
        for (Student student : students) {
            if (checkRatings(student))
                names.add(student.lastName + " Ratings" + Arrays.toString(student.grades));
        }
        return names;
    }

    public static void main(String[] args) {
        byte[] a = {5,4,3,4,5};
        byte[] b = {5,4,5,4,5};
        byte[] c = {5,4,5,5,5};
        students.add(new Student("Ivanov", a, "psihi"));
        students.add(new Student("Petrov", a, "psihi"));
        students.add(new Student("Artamonov", b, "psihi"));
        students.add(new Student("Sidorov", c, "psihi"));
        students.add(new Student("Sidorov", a, "lalki"));
        students.add(new Student("Vasiliev", b, "lalki"));
        students.add(new Student("Shpinatov", c, "lalki"));
        students.add(new Student("Kakon", c, "lalki"));

        ArrayList<String> sortedNames = namesOfStudents(students);
        System.out.println(sortedNames.toString());

        ArrayList<String> sortedByGroup = groupAndLastName(students, "lalki");
        System.out.println(sortedByGroup.toString());

        ArrayList<String> averageRating = averageRating(students);
        System.out.println(averageRating.toString());

        ArrayList<String> averageRatingThis = averageRatingThis(students, 4.6f);
        System.out.println("Average rating is 4.6");
        System.out.println(averageRatingThis.toString());

        ArrayList<String> studentsWithGoodRaings = studentsWithGoodRatings(students);
        System.out.println(studentsWithGoodRaings.toString());
    }
}
