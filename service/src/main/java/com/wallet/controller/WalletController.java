package com.wallet.controller;

import com.wallet.dto.IncomingDataDto;
import com.wallet.dto.WalletDto;
import com.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService service;

    @PostMapping("/wallet")
    public WalletDto makeADeposit(@Valid @RequestBody IncomingDataDto incomingDataDto) {
        return service.makeADeposit(incomingDataDto);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public WalletDto getData(@PathVariable UUID WALLET_UUID) {
        return service.getData(WALLET_UUID);
    }
}
