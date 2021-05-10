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
      <a href="#" class="header__popup--a" v-on:click="clickDeleteMember">Удалить с доски...</a>
    </div>
  </div>
</template>

<script>
import store from "@/store";

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
  methods: {
    clickDeleteMember() {
      fetch(`http://localhost:9000/api/v1/board/member/${this.$store.state.popupUser.id}`, {
        method: 'delete',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${store.getters['auth/token']}`
        },
        mode: "cors"
      }).then(response => response.json())
          .then(result => {
            console.log(result)
            if (result.status === 204) {
              this.$store.dispatch('board/mountBoardMembers', this.$route.params.id)
              this.$store.commit('activePopup', this.$store.state.popupUser)
            }
          })
    }
  }
}
</script>

<style scoped>

</style>