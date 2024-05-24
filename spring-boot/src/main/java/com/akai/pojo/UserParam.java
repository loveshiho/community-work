package com.akai.pojo;

import com.akai.config.EditValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
@Builder
public class UserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "userId could not be empty")
    private String userId;

    @NotEmpty(message = "email could not be empty")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "cardNo could not be empty")
    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$", message = "invalid ID")
    private String cardNo;

    @NotEmpty(message = "nickName could not be empty")
    @Length(min = 1, max = 10, message = "nick name should be 1-10")
    private String nickName;

    @Range(min = 0, max = 1, message = "sex should be 0-1")
    private Integer sex;
}
