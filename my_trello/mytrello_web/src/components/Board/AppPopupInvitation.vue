<template>
  <div class="popup__section--invitation" v-bind:class="{'active__invitation':activeInvitation}">
    <div class="popup__invitation">
      <svg class="symbol__close--invitation" v-on:click="closeInvitation">
        <use xlink:href="#close"></use>
      </svg>
      <div class="popup__invitation--title">
        Пригласить на доску
      </div>
      <form class="popup__invitation--form">
        <div class="popup__invitation--group">
          <input type="text" placeholder="Адрес электронной почты" v-model="addUser" class="popup__invitation--input">
          <div class="popup__invitation--searched">
            <a href="#" class="popup__invitation--item notext-decoration" v-on:click="clickUserEmail(user)"
               v-for="user in getAllUsers">
              <div class="main__header--username board__avatar">
                {{ user.email[0] }}
              </div>
              <div class="popup__invitation--email">{{ user.email }}</div>
            </a>

          </div>
        </div>
        <button class="popup__invitation--btn" type="button" v-on:click="addMember">Добавить</button>
      </form>
    </div>
  </div>
</template>

<script>
import store from "@/store";

export default {
  name: "AppPopupInvitation",
  data: () => ({
    addUser: '',
    curUser: '',
    member: {
      board: -1,
      user: -1,
    }
  }),
  computed: {
    activeInvitation() {
      return this.$store.state.activeInvitation;
    },
    closeInvitation() {
      this.$store.commit('activateInvitation')
    },
    getAllUsers() {
      return this.$store.getters['board/allUsers']
    }
  },
  methods: {
    clickUserEmail(user) {
      this.addUser = user.email
      this.curUser = user
    },
    addMember() {
      this.member.user = this.curUser.id
      this.member.board = this.$route.params.id

      fetch('http://localhost:9000/api/v1/board/add_member', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${store.getters['auth/token']}`
        },
        mode: "cors",
        body: JSON.stringify(this.member)
      }).then(response => response.json())
          .then(result => {
            console.log(result)
            if (result.status === 203){
              this.$store.dispatch('board/mountBoardMembers', this.$route.params.id)
              this.$store.commit('activateInvitation')
            }
          })
    }
  },
  mounted() {
    this.$store.commit('board/getAllUser')
  }
}
</script>

<style scoped>

</style>