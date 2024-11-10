package jmp.service.impl;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ServiceImpl implements Service {

    List<Subscription> subscriptions = new ArrayList<>();
    List<User> users = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
        users.add(bankCard.getUser());
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.stream()
                .filter(s -> cardNumber.equals(s.getBankcard()))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableList());
    }

}
