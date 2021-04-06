<template>
  <form class="login__form" method="post">
    <div id="error" class="error__block">
      <p class="error__message">
        Аккаунт с таким адресом электронной почты не существует
      </p>
    </div>
    <h1 class="login__form--title">Вход в Trello</h1>
    <div class="form__group">
      <input type="text" name="email" class="login__form--field" placeholder="Укажите адрес электронной почты">
    </div>
    <div class="form__group">
      <input type="text" name="email" class="login__form--field" placeholder="Введите имя">
    </div>
    <div class="form__group">
      <input type="text" name="email" class="login__form--field" placeholder="Введите пароль">
    </div>
    <router-link to="/login">
      <button class="login__btn--input">Зарегистрироваться</button>
    </router-link>
  </form>
</template>

<script>
export default {
  name: "AppRegistration"
}
</script>

<style scoped>

</style>