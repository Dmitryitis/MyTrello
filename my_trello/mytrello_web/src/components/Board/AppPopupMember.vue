<template>
  <div class="header__board--popup" v-bind:class="{'popup__active':activePopup}">
    <div class="header__board--title">
      <button class="board__avatar--popup">
        {{ $store.state.popupUser.email[0] }}
      </button>
      <div class="header__popup--info">
        <span class="header__popup--username">
          {{ $store.state.popupUser.username }}
          {{ $store.state.popupUser.surname }}
        </span>
        <span class="header__popup--email">{{ $store.state.popupUser.email }}</span>
      </div>

      <svg class="symbol__close--popup" v-on:click="closePopup">
        <use xlink:href="#close"></use>
      </svg>
    </div>
    <div class="header__popup--content">
      <a href="#" class="header__popup--a">Удалить с доски...</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppPopupMember",
  computed: {
    activePopup() {
      return this.$store.state.activePopup
    },
    closePopup() {
      console.log(this.$store.state.popupUser)
      return this.$store.commit('activePopup', this.$store.state.popupUser)
    },
  },
}
</script>

<style scoped>

</style>