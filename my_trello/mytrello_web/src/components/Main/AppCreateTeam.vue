<template>
  <div class="create_team--window" v-bind:class="{'team__window--active':activeModal}">
    <div class="create_team--modal">
      <svg class="symbol__close--modal" v-on:click="closeModal">
        <use xlink:href="#close"></use>
      </svg>
      <div class="team__modal--info">
        <form class="team__modal--form">
          <span class="team__modal--title">Создайте команду</span>
          <span class="team__modal--text">Повысьте производительность: участники команды смогут получать удобный доступ ко всем доскам.</span>
          <div class="modal__group">
            <label for="name_team" class="modal__team--label">Имя команды</label>
            <input type="text" id="name_team" class="modal__team--input" placeholder="Введите название команды">
            <div class="modal__team-undertext">Укажите название вашей команды, компании или организации.</div>
          </div>

          <button class="modal__team--btn">Создать команду</button>
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
export default {
  name: "AppCreateTeam",
  computed: {
    activeModal() {
      return this.$store.state.activeTeamModal;
    }
  },
  methods: {
    closeModal() {
      this.$store.commit('activeModal')
    }
  }
}
</script>

<style scoped>

</style>