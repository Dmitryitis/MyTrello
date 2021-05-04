<template>
  <form class="login__form" method="post">
    <div id="error" class="error__block" v-if="error.status">
      <p class="error__message">
        {{ error.message }}
      </p>
    </div>
    <h1 class="login__form--title">Вход в Trello</h1>
    <div class="form__group">
      <input type="text" name="email" class="login__form--field" v-model="user.email"
             placeholder="Укажите адрес электронной почты">
    </div>
    <div class="form__group">
      <input type="text" name="username" class="login__form--field" v-model="user.username" placeholder="Введите имя">
    </div>
    <div class="form__group">
      <input type="text" name="surname" class="login__form--field" v-model="user.surname" placeholder="Введите фамилию">
    </div>
    <div class="form__group">
      <input type="password" name="password" class="login__form--field" v-model="user.password"
             placeholder="Введите пароль">
    </div>
    <div class="form__group">
      <input type="password" name="rep_password" class="login__form--field" v-model="rep_password"
             placeholder="Повторите пароль">
    </div>
    <button type="button" class="login__btn--input" v-on:click="btnRegistration">Зарегистрироваться</button>
  </form>
</template>

<script>
import router from "@/router";

export default {
  name: "AppRegistration",
  data: () => ({
    user: {
      'email': '',
      'username': '',
      'surname': '',
      'password': '',
    },
    errors: {
      error_rep: 'Пароли не совпадают',
      error_email: 'Аккаунт с таким адресом электронной почты существует',
      error_empty: 'Заполните все поля',
      error: 'Ошибка:('
    },
    rep_password: '',
    error: {
      status: false,
      message: ''
    },
  }),
  methods: {
    btnRegistration() {
      if (this.user.password !== '' && this.user.email !== '' && this.user.surname !== '' && this.user.username !== '') {
        this.error.status = false
        this.error.message = ''

        fetch('http://localhost:9000/api/v1/register', {
          method: 'post',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.user),
          mode: "cors"
        })
            .then(response => response.json())
            .then(result => {
              console.log(result)
              if (result.status === 200) {
                router.push('/login')
              } else {
                this.error.status = true
                this.error.message = this.errors.error
              }
            })
      } else if (this.rep_password !== this.user.password) {
        this.error.status = true
        this.error.message = this.errors.error_rep
      } else {
        this.error.status = true
        this.error.message = this.errors.error_empty
      }

    }
  }
}
</script>

<style scoped>

</style>