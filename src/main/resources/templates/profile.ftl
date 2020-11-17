<#import "parts/common.ftl" as c>

<@c.page>
<h5>${username}</h5>
<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Фамилия:</label>
        <div class="col-sm-6">
            <input type="text" name="firstname" class="form-control" placeholder="Фамилия" value="${firstname!''}" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Имя Отчество:</label>
        <div class="col-sm-6">
            <input type="text" name="lastname" class="form-control" placeholder="Имя Отчество" value="${lastname!''}" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Сохранить</button>
    <button class="btn btn-secondary"><a href="/">Отмена</a></button>
</form>
</@c.page>
