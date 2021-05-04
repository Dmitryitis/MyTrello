<template>
  <form class="login__form">
    <div id="error" class="error__block" v-if="error.status">
      <p class="error__message">
        {{ error.message }}
      </p>
    </div>
    <h1 class="login__form--title">Вход в Trello</h1>
    <div class="form__group">
      <input type="text" name="email" v-model="user.email" class="login__form--field"
             placeholder="Укажите адрес электронной почты">
    </div>
    <div class="form__group">
      <input type="password" v-model="user.password" name="password" class="login__form--field"
             placeholder="Введите пароль">
    </div>
    <button class="login__btn--input" type="button" v-on:click="btnLogin">Войти</button>
    <p class="login__p--or">Или</p>

    <button type="button" class="login__btn--vk" v-on:click="btnGoogle">
      <svg class="symbol__vk">
        <use xlink:href="#google"></use>
      </svg>
      <span class="btn__vk--text"></span>Войти через Google
    </button>


    <div class="line__login"></div>

    <router-link to="/registration" class="button__form--link">
      <a href="#" class="login__link--form">Зарегистрировать аккаунт</a>
    </router-link>
  </form>
</template>

<script>
import router from "@/router";

export default {
  name: "AppLoginForm",
  data: () => ({
    user: {
      email: '',
      password: '',
    },
    errors: {
      error_empty: 'Заполните поля',
      error_login: 'Нет такого пользователя',
    },
    error: {
      status: false,
      message: ''
    },
    user_google: {
      email: '',
      name: '',
      surname: '',
    },
    isSignIn: false
  }),
  methods: {
    btnLogin() {
      if (this.user.email !== '' && this.user.password !== '') {
        fetch('http://localhost:9000/api/v1/auth/authenticate', {
          method: 'post',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.user)
        }).then(response => response.json()).then(result => {
          console.log(result)
          if (result.status === 200) {
            this.$store.dispatch('auth/login', result)
            router.push('/main')
          } else if (result.status === 404) {
            this.error.status = true
            this.error.message = this.errors.error_login
          }
        })
      } else {
        this.error.status = true
        this.error.message = this.errors.error_empty
      }
    },
    async btnContinue() {
      const authCode = await this.$gAuth.getAuthCode()
      console.log(authCode)
    },
    async btnGoogle() {
      try {
        const googleUser = await this.$gAuth.signIn()
        console.log('user', googleUser)
        this.user_google.email = googleUser.getBasicProfile().getEmail()
        this.user_google.name = googleUser.getBasicProfile().getGivenName()
        this.user_google.surname = googleUser.getBasicProfile().getFamilyName()

        await fetch('http://localhost:9000/api/v1/auth/auth-google', {
          method: 'post',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.user_google),
          mode: "cors"
        }).then(response => response.json())
            .then(result => {
              if (result.status === 200) {
                this.$store.dispatch('auth/login', result)
                router.push('/main')
              } else if (result.status === 404) {
                this.error.status = true
                this.error.message = this.errors.error_login
              }
            })
      } catch (e) {
        console.log(e)
      }
    }
  },
  mounted() {
    this.isSignIn = this.$gAuth.isAuthorized;
  }
}
</script>

<style scoped>

</style>