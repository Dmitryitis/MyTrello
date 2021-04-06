<template>
  <section class="menu__section" v-bind:class="{'menu__active': activeMenu}">
    <header class="menu__header">
      Учетная запись
      <svg class="symbol__close" v-on:click="closeMenu">
        <use xlink:href="#close"></use>
      </svg>
    </header>
    <div class="menu__user">
      <button class="main__header--username menu__user--avatar">
        ДГ
      </button>
      <div class="menu__user--info">
        <div class="menu__user-name">Дмитрий Герасимов</div>
        <div class="menu__user-email">dmitry.itis.gg@gmail.com</div>
      </div>
    </div>
    <ul class="menu__user--ul">
      <router-link to="/" class="notext-decoration">
        <li class="menu__user--li">Выйти</li>
      </router-link>
    </ul>
  </section>
</template>

<script>
export default {
  name: "AppMemberMenu",
  data: () => ({}),
  computed: {
    activeMenu() {
      return this.$store.state.activeMenu;
    }
  },
  methods: {
    closeMenu() {
      this.$store.commit('clickMenu')
    },
  }
}
</script>

<style scoped>

</style>