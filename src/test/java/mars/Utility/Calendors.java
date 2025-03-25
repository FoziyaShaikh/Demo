package mars.Utility;

import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.Map;

public class Calendors {
    private static int targetDay = 0, targetMonth = 0, targetYear = 0;
    private static int displayedMonth = 0, displayedYear = 0;
    private static boolean increment = true;

    private Page page;

    // Constructor
    public Calendors(Page page) {
        this.page = page;
    }

    public void selectDate(String dateToSet) {
        // Step 1: Extract the displayed month and year
        getDisplayedMonthAndYear();
        System.out.println("Displayed Calendar: " + displayedMonth + "/" + displayedYear);

        // Step 2: Get the target date details
        getTargetDateMonthAndYear(dateToSet);
        System.out.println("Target Date: " + targetDay + "/" + targetMonth + "/" + targetYear);

        // Step 3: Calculate month jumps
        int jumpToMonth = calculateHowManyMonthsToJump();
        System.out.println("Months to Jump: " + jumpToMonth);

        // Step 4: Navigate to the correct month
        for (int i = 0; i < jumpToMonth; i++) {
            if (increment) {
                page.locator("//i[@class='icon-rc-datepicker icon-rc-datepicker_next']").click(); // Click 'Next' button
            } else {
                page.locator("//i[@class='icon-rc-datepicker icon-rc-datepicker_prev']").click(); // Click 'Previous' button
            }
            page.waitForTimeout(500);
        }

        // Step 5: Click on the correct date
        page.locator("//div[@class='react-datepicker-picker day current']//span[text()='" + targetDay + "']").click();
    }

    // 🔹 Extract month and year from the calendar header
    private void getDisplayedMonthAndYear() {
        String headerText = page.locator("//strong").textContent().trim(); // Example: "February 2025"

        String[] parts = headerText.split(" ");
        displayedMonth = convertMonthNameToNumber(parts[0]); // Convert "February" to 2
        displayedYear = Integer.parseInt(parts[1]); // Convert "2025" to 2025
    }

    // 🔹 Convert month name (January, February, etc.) to a numeric value
    private int convertMonthNameToNumber(String monthName) {
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("January", 1);
        monthMap.put("February", 2);
        monthMap.put("March", 3);
        monthMap.put("April", 4);
        monthMap.put("May", 5);
        monthMap.put("June", 6);
        monthMap.put("July", 7);
        monthMap.put("August", 8);
        monthMap.put("September", 9);
        monthMap.put("October", 10);
        monthMap.put("November", 11);
        monthMap.put("December", 12);

        return monthMap.getOrDefault(monthName, 0); // Default to 0 if not found
    }

    // 🔹 Calculate how many months to jump (Fix: Includes year difference)
    private int calculateHowManyMonthsToJump() {
        int yearDiff = targetYear - displayedYear;
        int monthDiff = targetMonth - displayedMonth;
        int totalMonths = (yearDiff * 12) + monthDiff;

        if (totalMonths > 0) {
            increment = true; // Move forward
        } else {
            increment = false; // Move backward
            totalMonths = Math.abs(totalMonths); // Convert to positive value
        }

        return totalMonths;
    }

    // 🔹 Extract target day, month, and year from a date string
    private void getTargetDateMonthAndYear(String dateToSet) {
        String[] dateParts = dateToSet.split("/");
        targetDay = Integer.parseInt(dateParts[0]);
        targetMonth = Integer.parseInt(dateParts[1]);
        targetYear = Integer.parseInt(dateParts[2]);
    }
}
