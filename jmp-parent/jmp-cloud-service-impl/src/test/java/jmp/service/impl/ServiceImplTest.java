package jmp.service.impl;

import jmp.dto.BankCard;
import jmp.dto.User;
import jmp.service.api.Service;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ServiceImplTest {

    private static final String EXISTING_CARD_NUMBER = "1111222233334444";
    private static final String NON_EXISTING_CARD_NUMBER = "5555555555555555";

    private static final User IVAN = new User("Ivan", "Ivanov", LocalDate.of(2010, 1, 25));
    private static final User PETR = new User("Pert", "Petrov", LocalDate.of(2004, 6, 25));

    Service service = new ServiceImpl();

    @Before
    public void setup() {
        var ivanBankcard = new BankCard(EXISTING_CARD_NUMBER, IVAN);
        var petrBankcard = new BankCard("5555666677778888", PETR);
        service.subscribe(ivanBankcard);
        service.subscribe(petrBankcard);
    }

    @Test
    public void isPayableUser_shouldReturnFalse() {
        // Act
        var isPayableUser = Service.isPayableUser(IVAN);

        // Assert
        assertFalse(isPayableUser);
    }

    @Test
    public void isPayableUser_shouldReturnTrue() {
        // Act
        var isPayableUser = Service.isPayableUser(PETR);

        // Assert
        assertTrue(isPayableUser);
    }

    @Test
    public void getAverageUsersAgeTest() {
        // Act
        var result = service.getAverageUsersAge();

        // Assert
        assertEquals(17.0, result, 0);
    }

    @Test
    public void getSubscriptionByBankCardNumberTest_successful() {
        // Act
        var subscription = service.getSubscriptionByBankCardNumber(EXISTING_CARD_NUMBER);

        // Assert
        assertTrue(subscription.isPresent());
    }

    @Test
    public void getSubscriptionByBankCardNumberTest_failed() {
        // Act
        var subscription = service.getSubscriptionByBankCardNumber(NON_EXISTING_CARD_NUMBER);

        // Assert
        assertFalse(subscription.isPresent());
    }

    @Test
    public void getAllSubscriptionsByConditionTest() {
        // Act
        var subscriptions = service.getAllSubscriptionsByCondition(s -> s.getBankcard().equals(EXISTING_CARD_NUMBER));

        // Assert
        assertEquals(1, subscriptions.size());
        var subscription = subscriptions.get(0);
        assertEquals(EXISTING_CARD_NUMBER, subscription.getBankcard());
    }
}
