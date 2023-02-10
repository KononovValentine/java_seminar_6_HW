import java.util.LinkedList;
import java.util.Scanner;

public class ListNoteBooks {
    Scanner scanner = new Scanner(System.in);
    private LinkedList<NoteBook> list;
    private int size;
    private int minOperativeMemory;
    private int maxOperativeMemory;
    private int minHardDisk;
    private int maxHardDisk;
    LinkedList<String> colors = new LinkedList<>();
    LinkedList<String> operationSystems = new LinkedList<>();

    ListNoteBooks() {
        list = new LinkedList<>();
    }

    int size() {
        return size;
    }

    void addNoteBook(NoteBook noteBook) {
        list.add(noteBook);
        size++;
        setMinAndMaxProperties();
    }

    // Удаляет из списка элемент по индексу
    void remove(int index) {
        list.remove(index);
        size--;
        setMinAndMaxProperties();
    }

    // Удаляет из списка элемент по самому элементу
    void remove(NoteBook noteBook) {
        list.remove(noteBook);
    }

    // возвращает минимальное значение оперативной памяти в списке
    int getMinOperativeMemory() {
        return this.minOperativeMemory;
    }

    // возвращает максимальное значение оперативной памяти в списке
    int getMaxOperativeMemory() {
        return this.maxOperativeMemory;
    }

    // возвращает минимальное значение жесткого диска в списке
    int getMinHardDisk() {
        return this.minHardDisk;
    }

    // возвращает максимальное значение жесткого диска в списке
    int getMaxHardDisk() {
        return this.maxHardDisk;
    }

    // возвращает список цветов ноутбуков в списке
    LinkedList<String> getColors() {
        return this.colors;
    }

    // возвращает список оперативных систем ноутбуков в списке
    LinkedList<String> getOperationSystems() {
        return this.operationSystems;
    }

    // Запрашивает из списка подходящие по введенным параметрам модели
    void getNotebooksByParams() {
        System.out.println("Выберите параметры по которым подобрать модели:");
        System.out.println("1: Оперативная память");
        System.out.println("2: Объем жесткого диска");
        System.out.println("3: Оперативная система");
        System.out.println("4: Цвет");
        System.out.println("При выборе нескольких параметров укажите цифры через пробел.");
        String listNumbers = scanner.nextLine();
        System.out.println("Введите Y, если нужно указать конкретные значения (минимальные значения), и N, если нет.");
        String additionalParams = scanner.nextLine();

        if (listNumbers.contains(" ") && listNumbers.replace(" ", "").chars()
                .allMatch(Character::isDigit)) {
            String[] listChoices = listNumbers.split(" ");
            if (additionalParams.equalsIgnoreCase("y")) {
                String[] listAdditionalParams = getAdditionalParams(listChoices);
                LinkedList<NoteBook> newList = getSortByParamsList(listAdditionalParams);
                sortNotebooks(listChoices, newList);
            } else {
                sortNotebooks(listChoices, this.list);
            }
        } else if (listNumbers.chars().allMatch(Character::isDigit)) {
            String[] listChoices = listNumbers.split("");
            if (additionalParams.equalsIgnoreCase("y")) {
                String[] listAdditionalParams = getAdditionalParams(listChoices);
                LinkedList<NoteBook> newList = getSortByParamsList(listAdditionalParams);
                sortNotebooks(listChoices, newList);
            } else {
                sortNotebooks(listChoices, this.list);
            }
        } else {
            System.out.println("Неверный ввод.");
        }
    }

    // Сортирует подходящие ноутбуки
    private void sortNotebooks(String[] listChoices, LinkedList<NoteBook> list) {
        LinkedList<String> result = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < listChoices.length; j++) {
                NoteBook noteBook = list.get(i);
                if (!stringBuilder.toString().contains("Model")) {
                    stringBuilder.append("Model: ").append(noteBook.getModel());
                }
                switch (listChoices[j]) {
                    case "1" -> {
                        stringBuilder.append(", OM: ").append(noteBook.getOperativeMemory());
                    }
                    case "2" -> {
                        stringBuilder.append(", HD: ").append(noteBook.getHardDisk());
                    }
                    case "3" -> {
                        stringBuilder.append(", OS: ").append(noteBook.getOperationSystem());
                    }
                    case "4" -> {
                        stringBuilder.append(", Color: ").append(noteBook.getColor());
                    }
                }
            }
            if (!stringBuilder.isEmpty()) {
                result.add(stringBuilder.toString());
            }
        }
        System.out.println("Вот список нужных моделей: ");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    // Запрашивает дополнительные параметры
    private String[] getAdditionalParams(String[] listChoices) {
        String[] result = new String[listChoices.length];
        for (int i = 0; i < listChoices.length; i++) {
            switch (listChoices[i]) {
                case "1" -> {
                    System.out.println("Введите минимальное значение оперативной памяти: ");
                    String resultString = "1:" + scanner.nextLine();
                    result[i] = resultString;
                }
                case "2" -> {
                    System.out.println("Введите минимальный размер жесткого диска: ");
                    String resultString = "2:" + scanner.nextLine();
                    result[i] = resultString;
                }
                case "3" -> {
                    System.out.println("Введите необходимую операционную систему из представленных: ");
                    for (int j = 0; j < operationSystems.size(); j++) {
                        System.out.println(operationSystems.get(j));
                    }
                    String resultString = "3:" + scanner.nextLine();
                    result[i] = resultString;
                }
                case "4" -> {
                    System.out.println("Введите желаемый цвет из представленных: ");
                    for (int j = 0; j < colors.size(); j++) {
                        System.out.println(colors.get(j));
                    }
                    String resultString = "4:" + scanner.nextLine();
                    result[i] = resultString;
                }
            }
        }
        return result;
    }

    // Создает новый список, подходящий по дополнительным параметрам
    private LinkedList<NoteBook> getSortByParamsList(String[] listAdditionalParams) {
        int OM = 0;
        int HD = 0;
        String OS = "";
        String color = "";
        for (int i = 0; i < listAdditionalParams.length; i++) {
            if (listAdditionalParams[i].split(":")[0].equals("1")) {
                OM = Integer.parseInt(listAdditionalParams[i].split(":")[1]);
            }
            if (listAdditionalParams[i].split(":")[0].equals("2")) {
                HD = Integer.parseInt(listAdditionalParams[i].split(":")[1]);
            }
            if (listAdditionalParams[i].split(":")[0].equals("3")) {
                OS = listAdditionalParams[i].split(":")[1];
            }
            if (listAdditionalParams[i].split(":")[0].equals("4")) {
                color = listAdditionalParams[i].split(":")[1];
            }
        }
        LinkedList<NoteBook> result = new LinkedList<>();
        for (int i = 0; i < this.list.size(); i++) {
            NoteBook noteBook = this.list.get(i);

            if (OM <= noteBook.getOperativeMemory()
                    && HD <= noteBook.getHardDisk()
                    && OS.equalsIgnoreCase(noteBook.getOperationSystem())
                    && color.equalsIgnoreCase(noteBook.getColor())) {
                result.add(noteBook);
            } else if (OM <= noteBook.getOperativeMemory()
                    && HD <= noteBook.getHardDisk()
                    && OS.equalsIgnoreCase(noteBook.getOperationSystem())
                    && color.equals("")) {
                result.add(noteBook);
            } else if (OM <= noteBook.getOperativeMemory()
                    && HD <= noteBook.getHardDisk()
                    && OS.equals("")
                    && color.equalsIgnoreCase(noteBook.getColor())) {
                result.add(noteBook);
            } else if (OM <= noteBook.getOperativeMemory()
                    && HD <= noteBook.getHardDisk()
                    && OS.equals("")
                    && color.equals("")) {
                result.add(noteBook);
            }
        }
        return result;
    }

    // устанавливает минимальные и максимальные значения в текущем листе
    void setMinAndMaxProperties() {
        if (colors != null && !colors.isEmpty()) {
            for (int i = 0; i < colors.size(); i++) {
                colors.remove(i);
            }
        }
        if (operationSystems != null && !operationSystems.isEmpty()) {
            for (int i = 0; i < operationSystems.size(); i++) {
                operationSystems.remove(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            NoteBook noteBook = this.list.get(i);
            if (minHardDisk > noteBook.getHardDisk()) {
                minHardDisk = noteBook.getHardDisk();
            }
            if (maxHardDisk < noteBook.getHardDisk()) {
                maxHardDisk = noteBook.getHardDisk();
            }
            if (minOperativeMemory > noteBook.getOperativeMemory()) {
                minOperativeMemory = noteBook.getOperativeMemory();
            }
            if (maxOperativeMemory < noteBook.getOperativeMemory()) {
                maxOperativeMemory = noteBook.getOperativeMemory();
            }
            if (operationSystems != null && !operationSystems.contains(noteBook.getOperationSystem())) {
                operationSystems.add(noteBook.getOperationSystem());
            }
            if (colors != null && !colors.contains(noteBook.getColor())) {
                colors.add(noteBook.getColor());
            }
        }
    }
}
