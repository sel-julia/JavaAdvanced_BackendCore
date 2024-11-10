package jmp.bank.impl;

import jmp.dto.BankCard;
import jmp.dto.User;

@FunctionalInterface
interface BankCardFactory {

    BankCard create(String cardNumber, User user, double creditLimit);

}
