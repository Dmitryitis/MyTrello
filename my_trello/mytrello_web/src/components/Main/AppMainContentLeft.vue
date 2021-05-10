<template>
  <div class="main__content--right">
    <div class="main__content--group" v-for="team in getTeams">
      <div class="content__group--title">
        <svg class="symbol__personal">
          <use xlink:href="#personal"></use>
        </svg>
        <span class="content__group--text">{{ team.name }}</span>
      </div>
      <div class="content__group--cards">
        <a href="#" class="content__group--card notext-decoration" v-show="board.team.name === team.name"
           v-for="board in getBoards">
          {{ board.name }}
        </a>
        <a href="#" class="content__group--card notext-decoration content__group_create__card"
           v-on:click="createBoardModal">
          Создать доску
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppMainContentLeft",
  computed: {
    getTeams() {
      return this.$store.getters['main/teams']
    },
    getBoards() {
      return this.$store.getters['main/boards']
    }
  },
  methods: {
    createBoardModal() {
      this.$store.commit('activeModalBoard')
    }
  },
  mounted() {

  }
}
</script>

<style scoped>

</style>