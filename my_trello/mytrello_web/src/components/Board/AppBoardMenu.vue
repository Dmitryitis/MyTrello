<template>
  <div class="menu__board" v-bind:class="{'active__menu':activeArchive}">
    <div class="menu__board--title">
      Архив
      <svg class="symbol__close--menu" v-on:click="closeArchive">
        <use xlink:href="#close"></use>
      </svg>
    </div>
    <div class="menu__board--content">
      <div class="menu__board--item" v-for="card in getCardsBoard" v-show="card.archive === true">
        <a href="#" class="menu__board--card notext-decoration">
          <span class="archive__name">{{ card.title }}</span>
        </a>
        <p class="menu__board--action">
          <a href="#" class="link__back" v-on:click.prevent="backCard(card)">Вернуть на доску</a>
          <span class="notext-decoration delitel"> - </span>
          <a href="#" class="link__delete" v-on:click.prevent="deleteCard(card)">Удалить</a>
        </p>
      </div>

    </div>
  </div>
</template>

<script>
import store from "@/store";

export default {
  name: "AppBoardMenu",
  computed: {
    activeArchive() {
      return this.$store.state.activeArchive
    },
    getCardsBoard() {
      return this.$store.getters['board/cards']
    }
  },
  methods: {
    closeArchive() {
      this.$store.commit('activateArchive')
    },
    backCard(card) {
      fetch(`http://localhost:9000/api/v1/board/back_card/${card.id}`, {
        method: 'put',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${store.getters['auth/token']}`
        },
        mode: "cors",
      }).then(response => response.json())
          .then(result => {
            console.log(result)
            if (result.status === 200) {
              this.$store.dispatch('board/mountCards', this.$store.state.boardId)
              this.$store.commit('activateArchive')
            }
          })
    },
    deleteCard(card) {
      fetch(`http://localhost:9000/api/v1/board/delete_card/${card.id}`, {
        method: 'delete',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${store.getters['auth/token']}`
        },
        mode: "cors",
      }).then(response => response.json())
          .then(result => {
            console.log(result)
            if (result.status === 203) {
              this.$store.dispatch('board/mountCards', this.$store.state.boardId)
              this.$store.commit('activateArchive')
            }
          })
    }
  }
}
</script>

<style scoped>

</style>