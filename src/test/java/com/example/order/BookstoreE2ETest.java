package com.example.order;


import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.*;

public class BookstoreE2ETest {
    public static void main(String[] args) {
        // Playwright initialisieren
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Browser öffnen unter http://localhost:8081
            page.navigate("http://localhost:8081");

            // Einen Wert im Suchfeld eingeben
            String searchQuery = "Simon Sinek";
            page.fill("#keywords", searchQuery);

            // Den Such-Button betätigen
            page.click("#search_btn"); // Annahme: Die Such-Button-ID ist 'search-button'

            // Überprüfen, ob die erwarteten Bücher angezeigt werden
            page.waitForSelector(".books");
            Locator books = page.locator(".books");
            if (books.count() > 0) {
                System.out.println("Books found for search query: " + searchQuery);
            } else {
                System.out.println("No books found for search query: " + searchQuery);
            }

            // Ein Buch in den Warenkorb legen
            books.first().click(); // Klicke auf das erste Buch in der Liste
            page.click("#add2cart_link");

            // Überprüfen, ob der Warenkorb das ausgewählte Buch enthält
            page.click("#go2cart_link"); // Annahme: Der Warenkorb-Button hat die ID 'cart-button'
            page.waitForSelector(".books"); // Annahme: Jedes Item im Warenkorb hat die Klasse 'cart-item'
            Locator cartItems = page.locator(".books");
            if (cartItems.count() > 0) {
                System.out.println("Book successfully added to the cart.");
            } else {
                System.out.println("Failed to add the book to the cart.");
            }

            // Browser schließen
            browser.close();
        }
    }
}
