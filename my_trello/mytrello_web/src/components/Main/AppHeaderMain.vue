<template>
  <header class="main__header">
    <div class="main__header--right">
      <button class="main__header--block">
        <svg class="main__header--homeSymbol">
          <use xlink:href="#home"></use>
        </svg>
      </button>
      <button class="main__header--dashboard">
        <svg class="symbol__trello--dashboard">
          <use xlink:href="#trello-dashboard"></use>
        </svg>
        <span class="header__dashboard-text">Доски</span>
      </button>
    </div>
    <div class="main__header--middle">
      <img src="img/header-logo-spirit-loading.87e1af770a49ce8e84e3.gif" alt="" class="main__header--symbol">
    </div>
    <div class="main__header--left">
      <button class="main__header--create" v-on:click="clickCreateBoard">
        Создать доску
      </button>
    <button class="main__header--username" v-on:click="clickMenu">
      ДГ
    </button>
    </div>
  </header>
</template>

<script>
export default {
  name: "AppHeaderMain",
  data: () => ({
  }),
  methods: {
    clickMenu(){
      this.$store.commit('clickMenu')
    },
    clickCreateBoard(){
      this.$store.commit('activeModalBoard')
    }
  },
}
</script>

<style scoped>

</style>