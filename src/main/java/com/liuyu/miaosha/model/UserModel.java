package com.liuyu.miaosha.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModel {
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    @NotNull(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "电话不能为空")
    @NotNull(message = "电话不能为空")
    private String telphone;


    @NotNull(message = "性别不能不填")
    private Byte gender;

    @Max(value = 150,message = "年龄不能超过150")
    @Min(value = 0,message = "年龄不能小于0")
    private Integer age;

    private String registerMode;

    private String thirdPartyId;

    @NotBlank(message = "邮箱不能为空")
    @NotNull(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "密码不能为空")
    @NotNull(message = "密码不能为空")
    private String encrptPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserModel{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", telphone='").append(telphone).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", age=").append(age);
        sb.append(", registerMode='").append(registerMode).append('\'');
        sb.append(", thirdPartyId='").append(thirdPartyId).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", encrptPassword='").append(encrptPassword).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
