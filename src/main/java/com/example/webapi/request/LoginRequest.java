//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.webapi.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(
        message = "Tên đăng nhập không được trống"
) String userName, @NotBlank(
        message = "Vui lòng nhậu mật khẩu"
) String password) {
    public LoginRequest(@NotBlank(
            message = "Tên đăng nhập không được trống"
    ) String userName, @NotBlank(
            message = "Vui lòng nhậu mật khẩu"
    ) String password) {
        this.userName = userName;
        this.password = password;
    }

    public @NotBlank(
            message = "Tên đăng nhập không được trống"
    ) String userName() {
        return this.userName;
    }

    public @NotBlank(
            message = "Vui lòng nhậu mật khẩu"
    ) String password() {
        return this.password;
    }
}
