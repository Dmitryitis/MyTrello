<template>
  <div class="home__content">
    <div class="home__info">
      <h2 class="home__info--title">
        <b>Trello</b> помогает командам <br> эффективно решать <br> рабочие задачи.
      </h2>
      <p class="home__info--subtext">
        Работайте в команде, управляйте проектами и выводите продуктивность на новый уровень собственным уникальным
        способом вместе с Trello
      </p>
      <div>
        <router-link to="/login" v-slot="{navigate}" class="notext-decoration">
          <a href="#" class="home__lets--go">Давай начнем!</a>
        </router-link>
      </div>
    </div>
    <div class="home__picture">
      <img src="img/hero.png" width="931" height="1205" class="home__img" alt="">
    </div>
  </div>
</template>

<script>
export default {
  name: "AppBodyHome"
}
</script>

<style scoped>

</style>