package com.example.simplesaga.user.mapper;

import com.example.simplesaga.user.dto.AccountDto;
import com.example.simplesaga.user.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    AccountDto toDto(Account account);

    List<AccountDto> toDtoList(List<Account> accounts);
}
