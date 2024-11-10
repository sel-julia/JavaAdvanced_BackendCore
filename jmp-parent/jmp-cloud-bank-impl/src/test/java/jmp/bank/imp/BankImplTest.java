package jmp.bank.imp;

import jmp.bank.impl.BankImpl;
import jmp.dto.*;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class BankImplTest {

    @Test
    public void createCreditCardTest() {
        // Arrange
        var alice = new User("Alice", "Wonderland", LocalDate.now());
        var bank = new BankImpl();

        // Act
        var creditCard = bank.createBankCard(alice, BankCardType.CREDIT);

        // Assert
        assertTrue(creditCard instanceof CreditBankCard);
        assertEquals(50000d, ((CreditBankCard) creditCard).getCreditLimit());
    }

    @Test
    public void createDebitCardTest() {
        // Arrange
        var bob = new User("Bob", "Stamp", LocalDate.now());
        var bank = new BankImpl();

        // Act
        var debitCard = bank.createBankCard(bob, BankCardType.DEBIT);

        // Assert
        assertTrue(debitCard instanceof DebitBankCard);
        assertEquals(50000d, ((DebitBankCard) debitCard).getBalance());
    }

}
