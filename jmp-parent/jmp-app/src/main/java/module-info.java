import jmp.bank.api.Bank;
import jmp.service.api.Service;

module jmp.app {

    uses Bank;
    uses Service;

    requires jmp.dto;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.bank.api;
    requires jmp.service.api;
}