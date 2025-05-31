package com.axionlabs.todowoo.dto.user;

import com.axionlabs.todowoo.dto.token.TokenDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter @Setter
public class TokenizedUserDto extends UserDto{
    TokenDto tokens;
}
