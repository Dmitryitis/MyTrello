<template>
  <div class="create_team--window" v-bind:class="{'team__window--active':activeModal}">
    <div class="create_team--modal">
      <svg class="symbol__close--modal" v-on:click="closeModal">
        <use xlink:href="#close"></use>
      </svg>
      <div class="team__modal--info">
        <form class="team__modal--form">
          <span class="team__modal--title">Создайте доску</span>
          <span class="team__modal--text">Повысьте удобство: создайте доску для комфортной работы.</span>
          <div class="modal__group">
            <label for="name_board" class="modal__team--label">Название доски</label>
            <input type="text" id="name_board" class="modal__team--input" placeholder="Введите название доски"
                   v-model="board.nameBoard">
            <div class="modal__team-undertext">Укажите название доски.</div>
          </div>

          <div class="modal__group">
            <label for="name_team" class="modal__team--label">Выберите команду</label>
            <select name="name_team" class="modal__team--input" id="name_team" v-model="board.team_id">
              <option value="0">Выберите команду</option>
              <option :value="team.id" v-for="team in getTeams">{{ team.name }}</option>
            </select>
          </div>

          <button type="button" class="modal__team--btn" v-on:click="createBoard">Создать доску</button>
        </form>
      </div>
      <div class="modal__team--background">
        <div class="modal__team--photo">
          <img src="img/empty-board.d1f066971350650d3346.svg" class="modal__team--img" alt="">
          <img src="img/green-face.1a4590e4c12ebbbd161a.svg" alt="" class="modal__team--subimg">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from "@/store";

export default {
  name: "AppCreateBoard",
  data: () => ({
    selectTeam: 'Выберите команду',
    nameBoard: '',
    board: {
      team_id: 0,
      nameBoard: '',
      email: ''
    }
  }),
  computed: {
    activeModal() {
      return this.$store.state.activeBoardModal;
    },
    getTeams() {
      return this.$store.getters['main/teams']
    }
  },
  methods: {
    closeModal() {
      this.$store.commit('activeModalBoard')
    },
    createBoard() {
      if (this.board.nameBoard !== "" && this.board.team_id !== 0) {
        console.log(this.board)
        fetch("http://localhost:9000/api/v1/board/create", {
          method: 'post',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${store.getters['auth/token']}`
          },
          mode: "cors",
          body: JSON.stringify(this.board)
        }).then(response => response.json())
            .then(result => {
              if (result.status === 200) {
                this.board.team_id = 0
                this.board.nameBoard = ""
                this.$store.dispatch('main/mountBoards')
                this.$store.commit('activeModalBoard')
              }
            })
      }
    }
  },
  mounted() {
    this.board.email = this.$store.getters['auth/user'].email
  }
}
</script>

<style scoped>

</style>