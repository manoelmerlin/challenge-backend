package com.backendchallenge.challenge.services;

import com.backendchallenge.challenge.dto.WithdrawDto;
import com.backendchallenge.challenge.model.User;
import com.backendchallenge.challenge.model.Withdraw;
import com.backendchallenge.challenge.repository.WithdrawRepository;
import com.backendchallenge.challenge.services.exceptions.InvalidWithdraw;
import com.backendchallenge.challenge.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawService {

    @Autowired
    private UserService userService;

    @Autowired
    private WithdrawRepository withdrawRepository;

    public Withdraw save(WithdrawDto withdrawDto) {
        User user = userService.getUserByEmail(withdrawDto.getEmail());

        if (withdrawDto.getWithdrawValue() == null) {
            throw new InvalidWithdraw("Field withdrawValue is required");
        }

        checkIfFirstWithdrawValueIsBiggerThanMaximumValue(withdrawDto.getWithdrawValue(), user);
        checkIfTheUserReachTheMaximumOfWithdrawsOnDay(user);

        FeeService feeService = new FeeService(withdrawDto.getWithdrawValue());
        double withdrawValue = feeService.calculateValueWithFee();
        checkIfUserHasAccountBalance(withdrawValue, user);

        Withdraw withdraw = new Withdraw();
        withdraw.setUser(user);
        withdraw.setWithdrawValue(withdrawValue);
        return withdrawRepository.save(withdraw);
    }

    public List<Withdraw> getAll() {
        List<Withdraw> withdraws = withdrawRepository.findAll();

        return withdraws;
    }

    public long countWithdrawalsById(int id) {
        try {
            long totalWithdrawals = withdrawRepository.countByUserId(id);
            return totalWithdrawals;
        } catch (Exception e) {
            return 0;
        }
    }

    public void checkIfFirstWithdrawValueIsBiggerThanMaximumValue(double withdrawValue, User user) {
        long totalOfWithdrawals = countWithdrawalsById(user.getId());

        if (totalOfWithdrawals == 0 && withdrawValue > 50.0) {
            throw new InvalidWithdraw("First withdraw value must be lower than 50$");
        }
    }

    public void checkIfUserHasAccountBalance(double withdrawValue, User user) {
        if (user.getAccountBalance() - withdrawValue < 0) {
            throw new InvalidWithdraw("You dont have money bro");
        }
    }

    public void checkIfTheUserReachTheMaximumOfWithdrawsOnDay(User user) {
        List<Withdraw> withdrawals = withdrawRepository.getWithdrawsByIdInLastDay(
                user.getId(),
                DateUtils.getYesterday());
        if (withdrawals.size() > 4) {
            throw new InvalidWithdraw("You execute maximum withdrawals in 24 hours");
        }
    }
}
