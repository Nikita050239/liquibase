package com.wallet.service;

import com.wallet.dto.IncomingDataDto;
import com.wallet.dto.WalletDto;
import com.wallet.exception.IncorrectDataException;
import com.wallet.exception.NotFoundException;
import com.wallet.mapper.WalletMapper;
import com.wallet.model.Wallet;
import com.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static com.wallet.enums.OperationType.DEPOSIT;
import static com.wallet.enums.OperationType.WITHDRAW;
import static java.math.BigDecimal.ZERO;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository repository;
    private final WalletMapper mapper;

    @Override
    @Transactional
    public WalletDto makeADeposit(IncomingDataDto incomingData) {
        log.info("Поступили данные: {}", incomingData);
        Wallet wallet = findWallet(incomingData.getWalletId());
        if (DEPOSIT.equals(incomingData.getOperationType())) {
            wallet.setAmount(incomingData.getAmount().add(new BigDecimal(wallet.getAmount())).toPlainString());
            log.info("Внесен депозит: {}", wallet);
        }
        if (WITHDRAW.equals(incomingData.getOperationType())) {
            if (new BigDecimal(wallet.getAmount()).subtract(incomingData.getAmount()).compareTo(ZERO) < 0) {
                throw new IncorrectDataException("Недостаточно средств для снятия со счета");
            } else {
                wallet.setAmount(incomingData.getAmount().subtract(new BigDecimal(wallet.getAmount())).toPlainString());
                log.info("Снятие со счета: {}", wallet);
            }
        }
        return mapper.toWalletDto(wallet);
    }

    @Override
    public WalletDto getData(UUID walletId) {
        Wallet wallet = findWallet(walletId);
        log.info("Получение данных: {}", wallet);
        return mapper.toWalletDto(wallet);
    }

    private Wallet findWallet(UUID walletId) {
        Optional<Wallet> wallet = repository.findByWalletId(walletId);
        if (wallet.isPresent()) {
            return wallet.get();
        } else {
            throw new NotFoundException("Кошелёк не найден");
        }
    }
}
