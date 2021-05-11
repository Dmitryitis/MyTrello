<template>
  <div class="card-container" v-on:click="clickCard(id)">
    <div class="card-body">
      <p>
        {{ text }}
      </p>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "AppCard",
  props: {
    id: Number,
    text: String,
  },
  methods: {
    clickCard() {
      router.push({name: "Card",params: {board_id: this.$store.state.boardId , id:this.id}})
      this.$store.commit('activateCard')
    }
  }
}
</script>

<style scoped>

</style>