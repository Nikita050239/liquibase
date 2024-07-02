package com.wallet.service;

import com.wallet.dto.IncomingDataDto;
import com.wallet.dto.WalletDto;

import java.util.UUID;

public interface WalletService {
    WalletDto makeADeposit(IncomingDataDto incomingData);

    WalletDto getData(UUID walletId);
}
