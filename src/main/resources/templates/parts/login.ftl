<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Имя пользователя :</label>
        <div class="col-sm-6">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="Введите имя пользователя" />
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль :</label>
        <div class="col-sm-6">
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Введите пароль" />
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль (проверка):</label>
            <div class="col-sm-6">
                <input type="password" name="passwordConfirm"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Введите пароль для проверки" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       placeholder="some@some.com" />
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Фамилия:</label>
            <div class="col-sm-6">
                <input type="text" name="firstname" value="<#if user??>${user.firstname}</#if>"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Имя Отчество:</label>
            <div class="col-sm-6">
                <input type="text" name="lastname" value="<#if user??>${user.lastname}</#if>"
                       class="form-control"/>
            </div>
        </div>

        <!-- div class="col-sm-6">
            <div class="g-recaptcha" data-sitekey="6Leuc7gZAAAAAEPwgx3MoSdENh4cEC5KtdAhT8G5"></div>
            <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>
            </#if>
        </div -->
    </#if>
    <button class="btn btn-primary" type="submit">
        <#if isRegisterForm>
            Создать
        <#else>
            Войти
        </#if>
    </button>
    <div class="form-group row">
        <#if !isRegisterForm><a href="${contextPath}/registration">Регистрация</a></#if>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
</#macro>