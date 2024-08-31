import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRulesException;
import java.util.Scanner;

public class DateTimeUtils {

    // 1. Display Today's Date
    public static void displayTodaysDate() {
        LocalDate today = LocalDate.now();
        System.out.println("Today's date: " + today);
    }

    // 2. Date Decomposition
    public static void displayDateComponents(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            System.out.println("Year: " + localDate.getYear());
            System.out.println("Month: " + localDate.getMonthValue());
            System.out.println("Day: " + localDate.getDayOfMonth());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    // 3. Create a Specific Date
    public static LocalDate createSpecificDate() {
        return LocalDate.of(2025, 8, 19);
    }

    // 4. Comparing User-Entered Dates
    public static boolean areDatesEqual() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first date (YYYY-MM-DD):");
        String date1 = scanner.nextLine();
        System.out.println("Enter the second date (YYYY-MM-DD):");
        String date2 = scanner.nextLine();
        return LocalDate.parse(date1).equals(LocalDate.parse(date2));
    }

    // 5. Is Today a Specific Date?
    public static boolean isTodaySpecificDate() {
        LocalDate today = LocalDate.now();
        LocalDate specificDate = LocalDate.of(2019, 12, 10);
        return today.equals(specificDate);
    }

    // 6. Getting Current Time
    public static void displayCurrentTime() {
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    // 7. Date Arithmetic
    public static LocalDate addWeeksToToday(int weeks) {
        return LocalDate.now().plusWeeks(weeks);
    }

    // 8. Weekday Identifier
    public static String findDayOfWeek(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.getDayOfWeek().toString();
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }
    }

    // 9. Interval Between Dates
    public static long daysBetween() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first date (YYYY-MM-DD):");
        String date1 = scanner.nextLine();
        System.out.println("Enter the second date (YYYY-MM-DD):");
        String date2 = scanner.nextLine();
        LocalDate start = LocalDate.parse(date1);
        LocalDate end = LocalDate.parse(date2);
        return ChronoUnit.DAYS.between(start, end);
    }

    // 10. Date Authenticator
    public static boolean isValidDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // 11. Duration Since
    public static void elapsedTimeSince(String time) {
        try {
            LocalTime inputTime = LocalTime.parse(time);
            LocalTime currentTime = LocalTime.now();
            Duration duration = Duration.between(inputTime, currentTime);
            System.out.println("Elapsed time: " + duration.toHours() + " hours, " +
                    duration.toMinutesPart() + " minutes, " + duration.toSecondsPart() + " seconds.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:MM:SS.");
        }
    }

    // 12. Time Conversion Across Zones
    public static void convertToTimeZone() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date and time (YYYY-MM-DD HH:MM:SS):");
        String dateTime = scanner.nextLine();
        System.out.println("Enter timezone (e.g., 'America/New_York'):");
        String timeZone = scanner.nextLine();
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
            ZonedDateTime converted = zonedDateTime.withZoneSameInstant(ZoneId.of(timeZone));
            System.out.println("Converted time: " + converted.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format.");
        } catch (ZoneRulesException e) {
            System.out.println("Invalid timezone format.");
        }
    }

    public static void main(String[] args) {
        displayTodaysDate();
        displayDateComponents("2024-08-31"); // Example input
        System.out.println("Specific Date: " + createSpecificDate());
        System.out.println("Are dates equal? " + areDatesEqual());
        System.out.println("Is today a specific date? " + isTodaySpecificDate());
        displayCurrentTime();
        System.out.println("Date after adding weeks: " + addWeeksToToday(4));
        System.out.println("Day of the week: " + findDayOfWeek("2024-08-31"));
        System.out.println("Days between: " + daysBetween());
        System.out.println("Is the date valid? " + isValidDate());
        elapsedTimeSince("12:00:00"); // Example input
        convertToTimeZone();
    }
}