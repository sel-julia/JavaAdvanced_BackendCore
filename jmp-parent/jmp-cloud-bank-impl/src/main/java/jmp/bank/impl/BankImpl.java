package jmp.bank.impl;

import jmp.dto.*;
import jmp.bank.api.Bank;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public class BankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        var cardNumber = CardNumberGenerator.generate();

        return switch (cardType) {
            case DEBIT -> createCard(DebitBankCard::new, cardNumber, user, 50000);
            case CREDIT -> createCard(CreditBankCard::new, cardNumber, user, 50000);
            default -> throw new IllegalArgumentException("Unknown card type");
        };
    }

    private BankCard createCard(BankCardFactory factory, String cardNumber, User user, double creditLimit) {
        return factory.create(cardNumber, user, creditLimit);
    }
}
