package com.epam.main;

import jmp.bank.impl.BankImpl;
import jmp.bank.impl.CardNumberGenerator;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;
import jmp.service.exception.SubscriptionNotFoundException;
import jmp.service.impl.ServiceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App
{

    public static final String EXISTING_CARD_NUMBER = "5236698523659865";
    public static final String NON_EXISTING_CARD_NUMBER = "1136338523677000";

    public static final String DELIMETER = "--------------------------------------";
    public static void main( String[] args ) {

        var service = new ServiceImpl();

        generateUsersAndSubscriptions(service);

        // Print all users
        System.out.println(DELIMETER);
        System.out.println("All users:");
        service.getAllUsers()
                        .forEach(System.out::println);

        // Find and print existing subscription
        System.out.println(DELIMETER);
        System.out.println("Find subscription by existing card number: " + EXISTING_CARD_NUMBER);
        findAndPrintSubscriptionByCardNumber(service, EXISTING_CARD_NUMBER);

        // Find and print non-existing subscription
        System.out.println(DELIMETER);
        System.out.println("Find subscription by non-existing card number: " + NON_EXISTING_CARD_NUMBER);
        findAndPrintSubscriptionByCardNumber(service, NON_EXISTING_CARD_NUMBER);

        // Create bank card and print it
        System.out.println(DELIMETER);
        var bank = new BankImpl();
        var newUser = new User("Kate", "Sparr", LocalDate.of(2009, 12, 6));
        var creditBankCard = bank.createBankCard(newUser, BankCardType.CREDIT);
        System.out.println("New credit bank card:");
        System.out.println(creditBankCard);
        var debitBankCard = bank.createBankCard(newUser, BankCardType.DEBIT);
        System.out.println("New debit bank card:");
        System.out.println(debitBankCard);

        // Print average users age
        System.out.println(DELIMETER);
        System.out.println("Average users age: " + service.getAverageUsersAge());

        // Print if new user has more that 18 years
        System.out.println(DELIMETER);
        System.out.println("If new user is payable: ");
        System.out.println("User: " + newUser);
        System.out.println("Result: " + Service.isPayableUser(newUser));

        // Print all subscription that was created today
        System.out.println(DELIMETER);
        System.out.println("Print all subscription that was created today:");
        service
                .getAllSubscriptionsByCondition(s -> s.getStartDate().equals(LocalDate.now()))
                .forEach(System.out::println);
    }

    public static void findAndPrintSubscriptionByCardNumber(Service service, String cardNumber) {
        Subscription subscription = null;
        try {
            subscription = service
                    .getSubscriptionByBankCardNumber(cardNumber)
                    .orElseThrow(() -> new SubscriptionNotFoundException("There is no subscription for card number " + cardNumber));
        } catch (SubscriptionNotFoundException e) {
            System.out.println("SubscriptionNotFoundException is thrown for card number " + cardNumber);
        }
        System.out.println(subscription);
    }

    public static void generateUsersAndSubscriptions(Service service) {
        var alice = new User("Alice", "Carter", LocalDate.of(1989, 2, 15));
        var gregor = new User("Gregor", "Filderman", LocalDate.of(1973, 6, 29));
        var artur = new User("Artur", "McCarty", LocalDate.of(1965, 9, 3));

        var aliceCard = new BankCard(EXISTING_CARD_NUMBER, alice);
        var gregorCard = new BankCard(CardNumberGenerator.generate(), gregor);
        var arturCard = new BankCard(CardNumberGenerator.generate(), artur);

        service.subscribe(aliceCard);
        service.subscribe(gregorCard);
        service.subscribe(arturCard);
    }
}
