<template>
  <div class="card-container">
    <div class="card-body">
      <p>
        {{text}}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppCard",
  props: {
    id: Number,
    text: String,
  }
}
</script>

<style scoped>

</style>