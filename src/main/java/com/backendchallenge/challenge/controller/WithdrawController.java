package com.backendchallenge.challenge.controller;

import com.backendchallenge.challenge.dto.WithdrawDto;
import com.backendchallenge.challenge.model.Withdraw;
import com.backendchallenge.challenge.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody WithdrawDto withdrawDto) throws ParseException {
        Withdraw withdraw = withdrawService.save(withdrawDto);
        return ResponseEntity.ok().body(withdraw);
    }

    @GetMapping
    public ResponseEntity<List<Withdraw>> all() {
        List<Withdraw> withdraws = withdrawService.getAll();

        return ResponseEntity.ok().body(withdraws);
    }
}
