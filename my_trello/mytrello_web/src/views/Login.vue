<template>
  <app-sprite></app-sprite>
  <section class="login__section">
    <header class="login__header">
      <div class="logo__login">
        <svg class="login__symbol--trello">
          <use xlink:href="#trello"></use>
        </svg>
        <h2 class="logo__header__text">Trello</h2>
      </div>
    </header>
    <div class="login__content">
      <app-login-form></app-login-form>
    </div>
    <div class="login__background">
      <img src="img/largeBlock.svg" alt="" class="leftLargeSvg">
      <img src="img/rightBlock.svg" alt="" class="rightLargeSvg">
    </div>
  </section>
</template>

<script>
import AppSprite from "@/sprite/AppSprite";
import AppLoginForm from "@/components/Login/AppLoginForm";

export default {
  name: "Login",
  components: {AppSprite, AppLoginForm}
}
</script>

<style>

</style>