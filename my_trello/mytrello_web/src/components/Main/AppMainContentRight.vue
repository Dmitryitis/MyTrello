<template>
  <div class="main__content--left">
    <button class="main__content--item">
      <svg class="symbol__trello--content">
        <use xlink:href="#trello"></use>
      </svg>
      <span class="main__content--text">Доски</span>
    </button>
    <div class="main__content--block">
      <div class="main__content--title">
        <span class="main__content--titleText">Команды</span>
        <button class="main__content--add" v-on:click="clickPlus">
          <svg class="symbol__plus">
            <use xlink:href="#plus"></use>
          </svg>
        </button>
      </div>
      <button class="main__content--item" v-for="team in getTeams">
        <svg class="symbol__group--content">
          <use xlink:href="#group"></use>
        </svg>
        <span class="main__content--text">{{ team.name }}</span>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppMainContentRight",
  data: () => ({
    teams: []
  }),
  computed: {
    getTeams() {
      return this.$store.getters['main/teams']
    }
  },
  methods: {
    clickPlus() {
      this.$store.commit('activeModal');
    }
  },
  mounted() {
    this.$store.dispatch('main/mountBoards')
    this.$store.dispatch('main/mountTeams')
  }
}
</script>

<style scoped>

</style>