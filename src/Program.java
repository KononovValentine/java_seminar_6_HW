/*
Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки,
отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например: “Введите цифру, соответствующую необходимому
критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
 */

import java.util.Random;

public class Program {
    public static void main(String[] args) {

        String[] models = {"Asus", "Acer", "Sony", "Apple", "Toshiba", "Samsung", "LG"};
        int[] operativeMemories = {2, 4, 6, 8, 12, 16, 32};
        int[] hardDiskSizes = {256, 512, 1024, 2048, 3072, 5120};
        String[] operationSystems = {"Windows", "Linux", "MacOS"};
        String[] colors = {"Черный", "Белый", "Красный", "Синий", "Зеленый", "Желтый"};

        ListNoteBooks listNoteBooks = new ListNoteBooks();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            NoteBook noteBook = new NoteBook(
                    models[random.nextInt(0, models.length)],
                    operativeMemories[random.nextInt(0, operativeMemories.length)],
                    hardDiskSizes[random.nextInt(0, hardDiskSizes.length)],
                    operationSystems[random.nextInt(0, operationSystems.length)],
                    colors[random.nextInt(0, colors.length)]
            );
            listNoteBooks.addNoteBook(noteBook);
        }

        listNoteBooks.setMinAndMaxProperties();
        listNoteBooks.getNotebooksByParams();
    }
}
