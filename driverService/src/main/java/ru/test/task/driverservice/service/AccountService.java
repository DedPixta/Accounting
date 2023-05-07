package ru.test.task.driverservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.task.driverservice.entity.Driver;
import ru.test.task.driverservice.exception.NotEnoughMoney;
import ru.test.task.driverservice.exception.NotFoundException;
import ru.test.task.driverservice.repository.DriverRepository;
import ru.test.task.driverservice.util.BalanceOperationRequest;
import ru.test.task.driverservice.util.CurrencyType;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class AccountService {
    private final DriverRepository driverRepository;

    @Transactional
    public void withdraw(BalanceOperationRequest request) {
        Driver dbDriver = getDriverFromDB(request.getDriverId());

        BigDecimal convertedAmount = request.getAmount().multiply(request.getCurrencyType().getRate());
        BigDecimal balance = dbDriver.getAccount().getBalance();
        if (balance.compareTo(convertedAmount) < 0) {
            throw new NotEnoughMoney(String.format("Not enough money at balance to withdraw. Driver id: %d, amount: %.2f, currency type: %s",
                    request.getDriverId(),
                    request.getAmount(),
                    request.getCurrencyType()));
        }
        balance = balance.subtract(convertedAmount);
        dbDriver.getAccount().setBalance(balance);
        driverRepository.save(dbDriver);
    }

    @Transactional
    public void deposit(BalanceOperationRequest request) {
        Driver dbDriver = getDriverFromDB(request.getDriverId());

        BigDecimal balance = dbDriver.getAccount().getBalance();
        BigDecimal convertedAmount = request.getAmount().multiply(request.getCurrencyType().getRate());
        balance = balance.add(convertedAmount);
        dbDriver.getAccount().setBalance(balance);
        driverRepository.save(dbDriver);
    }

    private Driver getDriverFromDB(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Driver with id %s not found", id
                )));
    }

    public BigDecimal getBalance(Long id, CurrencyType currencyType) {
        Driver dbDriver = getDriverFromDB(id);
        if (currencyType == null) {
            return dbDriver.getAccount().getBalance();
        }
        if (currencyType == CurrencyType.RED) {
            return dbDriver.getAccount().getBalance().multiply(CurrencyType.RED.getRate());
        } else if (currencyType == CurrencyType.BLUE) {
            return dbDriver.getAccount().getBalance().multiply(CurrencyType.BLUE.getRate());
        } else {
            return dbDriver.getAccount().getBalance().multiply(CurrencyType.GREEN.getRate());
        }
    }
}
