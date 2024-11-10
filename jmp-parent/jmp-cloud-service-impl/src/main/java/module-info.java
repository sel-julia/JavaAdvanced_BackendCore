module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;

    exports jmp.service.impl;
    exports jmp.service.exception;
}
