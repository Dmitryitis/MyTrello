<template>
  <header class="header__board">
    <div class="header__board--right">
      <div class="header__board-item">
        {{ getBoardAttr.name }}
      </div>
      <div class="vertical__line"></div>
      <div class="header__board-team">{{ getBoardAttr.team.name }}</div>
      <div class="vertical__line"></div>
      <div class="header__board--members">
        <button class="main__header--username board__avatar" v-on:click="openPopup(member.user)" v-for="member in getBoardMembersAttr">
          {{member.user.email[0]}}
        </button>
      </div>

      <div class="vertical__line"></div>
      <div class="header__board-team" v-on:click="clickInvitation">Пригласить</div>
    </div>
    <div class="header__board--left">
      <button class="header__board-team header__board--menu" v-on:click="clickArchive">Архив</button>
    </div>
  </header>
</template>

<script>
export default {
  name: "AppHeaderBoard",
  data: () => ({
    board: '',
    boardMembers: []
  }),
  computed: {
    getBoardAttr() {
      return this.$store.getters['board/board']
    },
    getBoardMembersAttr(){
      return this.$store.getters['board/boardMembers']
    }
  },
  methods: {
    clickInvitation() {
      this.$store.commit('activateInvitation')
    },
    clickArchive() {
      this.$store.commit('activateArchive')
    },
    openPopup(user) {
      this.$store.commit('activePopup',user)
    },
  },
  mounted() {
    this.$store.dispatch('board/mountBoard', this.$route.params.id)
    this.board = this.$store.getters['board/board']
    // console.log(this.board.team.name)
    this.$store.dispatch('board/mountBoardMembers', this.$route.params.id)
  }
}
</script>

<style scoped>

</style>