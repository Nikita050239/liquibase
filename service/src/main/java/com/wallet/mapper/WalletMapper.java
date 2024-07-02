package com.wallet.mapper;

import com.wallet.dto.WalletDto;
import com.wallet.model.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletMapper {

    public WalletDto toWalletDto(Wallet wallet) {
        return new WalletDto(wallet.getWalletId(), new BigDecimal(wallet.getAmount()));
    }
}
