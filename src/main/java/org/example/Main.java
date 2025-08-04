package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Interactive Order Processor!\n");

        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Is customer a member (true/false)?: ");
        boolean isMember = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = scanner.nextLine();

        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = scanner.nextLine();

        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = scanner.nextLine();

        double subTotal = unitPrice * quantity;
        double afterTierDiscount = subTotal;
        double afterQuantityDiscount;
        double afterPromoCode;
        double surcharge;
        double shippingCost = 0.00;
        boolean freeShippingApplied = false;

        if (customerTier.equals("Gold")) {
            afterTierDiscount *= 0.85;
        } else if (customerTier.equals("Silver")) {
            afterTierDiscount *= 0.90;
        }

        afterQuantityDiscount = (quantity >= 5) ? afterTierDiscount * 0.95 : afterTierDiscount;

        if (discountCode.equals("SAVE10") && afterQuantityDiscount > 75.00) {
            afterPromoCode = afterQuantityDiscount - 10.00;
        } else {
            afterPromoCode = afterQuantityDiscount;
            if (discountCode.equalsIgnoreCase("FREESHIP")) {
                freeShippingApplied = true;
            }
        }

        surcharge = (afterPromoCode < 25.00) ? 3.00 : 0.00;
        double totalAfterSurcharge = afterPromoCode + surcharge;

        if (!freeShippingApplied) {
            switch (shippingZone) {
                case "ZoneA":
                    shippingCost = 5.00;
                    break;
                case "ZoneB":
                    shippingCost = 12.50;
                    break;
                case "ZoneC":
                    shippingCost = 20.00;
                    break;
                default:
                    shippingCost = 25.00;
                    break;
            }
        }

        double finalOrderTotal = totalAfterSurcharge + shippingCost;

        System.out.println("\n--- Order Details ---");
        System.out.printf("Unit Price: $%.2f\n", unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        System.out.println("\n--- Calculation Steps ---");
        System.out.printf("Initial Subtotal: $%.2f\n", subTotal);

        if (customerTier.equals("Gold")) {
            System.out.printf("After Tier Discount (Gold - 15%%): $%.2f\n", afterTierDiscount);
        } else if (customerTier.equals("Silver")) {
            System.out.printf("After Tier Discount (Silver - 10%%): $%.2f\n", afterTierDiscount);
        } else {
            System.out.printf("After Tier Discount (None): $%.2f\n", afterTierDiscount);
        }

        if (quantity >= 5) {
            System.out.printf("After Quantity Discount (5%% for >=5 items): $%.2f\n", afterQuantityDiscount);
        } else {
            System.out.printf("After Quantity Discount (None): $%.2f\n", afterQuantityDiscount);
        }

        if (discountCode.equals("SAVE10") && afterQuantityDiscount > 75.00) {
            System.out.printf("After Promotional Code (SAVE10 for >$75): $%.2f\n", afterPromoCode);
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            System.out.printf("After Promotional Code (FREESHIP): $%.2f\n", afterPromoCode);
        } else {
            System.out.printf("After Promotional Code (None): $%.2f\n", afterPromoCode);
        }

        if (surcharge > 0) {
            System.out.printf("After Small Order Surcharge (if applicable): $%.2f (+$%.2f surcharge)\n", totalAfterSurcharge, surcharge);
        } else {
            System.out.printf("After Small Order Surcharge (if applicable): $%.2f (No surcharge)\n", totalAfterSurcharge);
        }

        if (freeShippingApplied) {
            System.out.println("\nShipping Cost: $0.00 (Free shipping applied)");
        } else {
            System.out.printf("\nShipping Cost: $%.2f (%s)\n", shippingCost, shippingZone);
        }

        System.out.printf("\nFinal Order Total: $%.2f\n", finalOrderTotal);

        System.out.println("\n--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        String firstStr = scanner.nextLine();

        System.out.print("Enter second string for comparison: ");
        String secondStr = scanner.nextLine();

        System.out.println("\nString 1: \"" + firstStr + "\"");
        System.out.println("String 2: \"" + secondStr + "\"");

        System.out.println("\nString 1 == String 2: " + (firstStr == secondStr) + " (Compares references, which are different for user input strings)");
        System.out.println("String 1 .equals() String 2: " + firstStr.equals(secondStr) + " (Content is " + (firstStr.equals(secondStr) ? "identical" : "different") + ")");
        System.out.println("String 1 .equalsIgnoreCase() String 2: " + firstStr.equalsIgnoreCase(secondStr) + " (Content is " + (firstStr.equalsIgnoreCase(secondStr) ? "identical, ignoring case" : "different, even ignoring case") + ")");

        scanner.close();
    }
}
