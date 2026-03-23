import java.time.LocalDateTime;
import java.util.Scanner;

public class practice_14 {

  static final int MAX = 50;

  static String[] notes = new String[MAX];
  static LocalDateTime[] dates = new LocalDateTime[MAX];
  static int count = 0;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("\n--- МІЙ ЩОДЕННИК ---");
      System.out.println("1. Додати запис");
      System.out.println("2. Видалити запис");
      System.out.println("3. Переглянути всі записи");
      System.out.println("4. Вихід");
      System.out.print("Оберіть: ");

      int choice;

      try {
        choice = Integer.parseInt(sc.nextLine());
      } catch (Exception e) {
        System.out.println("Помилка вводу!");
        continue;
      }

      switch (choice) {
        case 1:
          addNote(sc);
          break;
        case 2:
          deleteNote(sc);
          break;
        case 3:
          showNotes();
          break;
        case 4:
          System.out.println("Вихід...");
          return;
        default:
          System.out.println("Невірний вибір!");
      }
    }
  }

  static void addNote(Scanner sc) {

    if (count >= MAX) {
      System.out.println("Щоденник заповнений!");
      return;
    }

    try {
      System.out.print("Введіть рік: ");
      int year = Integer.parseInt(sc.nextLine());

      System.out.print("Введіть місяць: ");
      int month = Integer.parseInt(sc.nextLine());

      System.out.print("Введіть день: ");
      int day = Integer.parseInt(sc.nextLine());

      // час = 00:00 (як в умові)
      LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);

      System.out.println("Введіть текст запису:");
      String text = sc.nextLine();

      dates[count] = date;
      notes[count] = text;
      count++;

      System.out.println("Запис додано!");

    } catch (Exception e) {
      System.out.println("Невірна дата!");
    }
  }

  static void deleteNote(Scanner sc) {

    try {
      System.out.print("Введіть рік: ");
      int year = Integer.parseInt(sc.nextLine());

      System.out.print("Введіть місяць: ");
      int month = Integer.parseInt(sc.nextLine());

      System.out.print("Введіть день: ");
      int day = Integer.parseInt(sc.nextLine());

      LocalDateTime target = LocalDateTime.of(year, month, day, 0, 0);

      boolean found = false;

      for (int i = 0; i < count; i++) {
        if (dates[i].isEqual(target)) { // з лекції
          // зсув масиву
          for (int j = i; j < count - 1; j++) {
            dates[j] = dates[j + 1];
            notes[j] = notes[j + 1];
          }
          count--;
          found = true;
          System.out.println("Запис видалено!");
          break;
        }
      }

      if (!found) {
        System.out.println("Запис не знайдено!");
      }

    } catch (Exception e) {
      System.out.println("Помилка вводу!");
    }
  }

  static void showNotes() {

    if (count == 0) {
      System.out.println("Немає записів.");
      return;
    }

    for (int i = 0; i < count; i++) {
      System.out.println("----------------------");
      System.out.println("Дата: " +
              dates[i].getDayOfMonth() + "." +
              dates[i].getMonthValue() + "." +
              dates[i].getYear());

      System.out.println("Запис: " + notes[i]);
    }
  }
}